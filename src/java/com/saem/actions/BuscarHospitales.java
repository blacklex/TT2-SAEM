/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.EnfermedadesCronicas;
import com.hibernate.model.Hospitales;
import com.hibernate.model.Pacientes;
import com.hibernate.model.Usuarios;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.persistencia.owl.OWLConsultas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONValue;

/**
 *
 * @author sergio
 */
public class BuscarHospitales implements SessionAware {
    
    private Map<String, Object> session = null;
    HttpServletRequest request = ServletActionContext.getRequest();
    
    String nombreUsuario;
    String latitudUsuario;
    String longitudUsuario;
    String distancia;
    String hospitalesCercanos;
    
    Double latUsuario;
    Double longUsuario;
    Double distanciaRango;
    Double distanciaLatitud;
    Double distanciaLongitud;
    Double a;
    Double c;
    Double distanciaFinal;
    
    //campos json retorno
    String tituloAlert;
    String textoAlert;
    String estatusMensaje;
    String mensajeError = "";
    
    List listaHospitalesCercanos = new LinkedList();
    
    
    public String execute() {
        ArrayList<Hospitales> listaTemp = new ArrayList<Hospitales>();
        ArrayList<EnfermedadesCronicas> listaPacienteEnfermedades = new ArrayList<EnfermedadesCronicas>();
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
        
        //Boolean hayHospitales = false;
        
        HospitalDAO hospitalDAO = new HospitalDAO();
        listaTemp = (ArrayList<Hospitales>) hospitalDAO.findAll();        
        
        latUsuario = Double.parseDouble(latitudUsuario);
        longUsuario = Double.parseDouble(longitudUsuario);
        distanciaRango = Double.parseDouble(distancia);
        
        //-----------RECUPERAR ENFERMEDADES DEL PACIENTE --------------------
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuarios pacienteUsuario = usuarioDAO.findById(nombreUsuario);
        
        Pacientes paciente = (Pacientes) pacienteUsuario.getPacienteses().iterator().next();
        
        DatosClinicos pacienteDatosClinicos  = (DatosClinicos) paciente.getDatosClinicoses().iterator().next();
        Iterator pacienteEnfermedades = pacienteDatosClinicos.getEnfermedadesCronicases().iterator();
        
        while(pacienteEnfermedades.hasNext()){
            EnfermedadesCronicas enfermedad = (EnfermedadesCronicas) pacienteEnfermedades.next();
            listaPacienteEnfermedades.add(enfermedad);
        }
        
        if(listaPacienteEnfermedades==null)
            listaPacienteEnfermedades = new ArrayList<EnfermedadesCronicas>();
        else if(listaPacienteEnfermedades.isEmpty())
            listaPacienteEnfermedades = new ArrayList<EnfermedadesCronicas>();
        
        //-----------FIN RECUPERAR ENFERMEDADES DEL PACIENTE ----------------
        
        
        
        for(Hospitales hospTem : listaTemp) {
            String nombreHospital = hospTem.getNombre();
            String nombreHospitalTemp = nombreHospital;
            nombreHospitalTemp = nombreHospitalTemp.replaceAll("\\s+", "");
            
            System.out.println("Hospital nombre normal--->" + nombreHospital);
            System.out.println("Hospital nombre sin espacios--->" + nombreHospitalTemp);
            
            OWLConsultas consultor = new OWLConsultas(ONTOLOGIA, BASE_URI);
            consultor.hospitalseUbicaEnDireccion(nombreHospitalTemp);
            consultor.getCoordenadaYDireccion("Direccion" + nombreHospitalTemp);
            
            String latitudY = consultor.getCoordenadaYDireccion("Direccion" + nombreHospitalTemp).get(0);
            String longitudX = consultor.getCoordenadaXDireccion("Direccion" + nombreHospitalTemp).get(0);
            
            distanciaLatitud = (Double.parseDouble(latitudY) - latUsuario) * Math.PI / 180;
            distanciaLongitud = (Double.parseDouble(longitudX) - longUsuario) * Math.PI / 180;
            a = Math.sin(distanciaLatitud / 2) * Math.sin(distanciaLatitud / 2) + Math.cos(latUsuario * Math.PI / 180) * Math.cos(Double.parseDouble(latitudY) * Math.PI / 180) * Math.sin(distanciaLongitud / 2) * Math.sin(distanciaLongitud / 2);
            c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            distanciaFinal = 6371.0 * c;
            
            Map hospitales = new LinkedHashMap();
            
            if(distanciaFinal >= 0 && distanciaFinal <= distanciaRango) {
                hospitales.put("lt", latitudY);
                hospitales.put("ln", longitudX);
                hospitales.put("titulo", nombreHospital);
                hospitales.put("codigo", hospTem.getCodigoHospital());
                hospitales.put("tel", hospTem.getLada()+"-"+hospTem.getTelefono());
                hospitales.put("atiendeEnfermedad", "0");
                ArrayList<String> listaEnfermedadesHospital = (ArrayList<String>) consultor.hospitalAtiendeEnfermedad(nombreHospitalTemp);
                if(listaEnfermedadesHospital==null)
                    listaEnfermedadesHospital = new ArrayList<String>();
                
                for(String enfermedadHospTemp : listaEnfermedadesHospital){
                    for(EnfermedadesCronicas enfermedadPaacienteTemp : listaPacienteEnfermedades){
                        if(enfermedadHospTemp.equals(enfermedadPaacienteTemp.getNombre())){
                            hospitales.remove("atiendeEnfermedad");
                            hospitales.put("atiendeEnfermedad", "1");
                            break;
                        }
                    }
                }

                listaHospitalesCercanos.add(hospitales);

                System.out.println("El hospital " + nombreHospital + " esta dentro de la zona");
            }
            else
                System.out.println("El hospital " + nombreHospital + " no esta dentro de la zona");
            
            System.out.println("Distancia desde el usuario hasta el hospital----->" + (Math.round(distanciaFinal))+"km");
            
            System.out.println("********************************************************************************");

        }
//        if(!listaHospitalesCercanos.isEmpty())
//            hayHospitales = true;
//        else{
//            hayHospitales = false ;
//            mensajeError = "No hay Hospitales dentro de esta Zona!!!";
//        }
       
        if ( listaHospitalesCercanos.isEmpty()) {
            estatusMensaje = "vacio";
            return SUCCESS;
        }
        else {
            hospitalesCercanos = JSONValue.toJSONString(listaHospitalesCercanos);
            System.out.println(hospitalesCercanos);
        }
        
        System.out.println("Latitud: " + Double.parseDouble(latitudUsuario) + "\n" +
                           "Longitud: " + Double.parseDouble(longitudUsuario) + "\n" + 
                           "Distancia: " + Double.parseDouble(distancia));
        System.out.println("********************************************************************************");
        
//        String filePath = request.getSession().getServletContext().getRealPath("/");
//        FileWriter file = new FileWriter(filePath+"data/test.json");
//        file.write(hospitalesCercanos);
//        file.flush();
//        file.close();
        return SUCCESS;
    }

    public String recuperarEstatusAdmin() {

        tituloAlert = "";
        textoAlert = "";
        estatusMensaje = "";

        if (session.get("estatusMensaje") != null) {
            tituloAlert = session.get("tituloAlert").toString();
            textoAlert = session.get("textoAlert").toString();
            estatusMensaje = session.get("estatusMensaje").toString();
        }

        session.remove("estatusMensaje");
        session.remove("tituloAlert");
        session.remove("estatusMensaje");
        session.put("estatusMensaje", null);

        return "success";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    

    public String getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(String latitudUsuario) {
        this.latitudUsuario = latitudUsuario;
    }

    public String getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(String longitudUsuario) {
        this.longitudUsuario = longitudUsuario;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    } 

    public String getHospitalesCercanos() {
        return hospitalesCercanos;
    }

    public void setHospitalesCercanos(String hospitalesCercanos) {
        this.hospitalesCercanos = hospitalesCercanos;
    }

    public String getEstatusMensaje() {
        return estatusMensaje;
    }

    public void setEstatusMensaje(String estatusMensaje) {
        this.estatusMensaje = estatusMensaje;
    }
    
}
