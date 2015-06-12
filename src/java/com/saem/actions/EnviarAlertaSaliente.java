/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.PacienteDAO;
import com.hibernate.dao.PeticionesSalientesDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Contactos;
import com.hibernate.model.Hospitales;
import com.hibernate.model.Pacientes;
import com.hibernate.model.PeticionesEntrantes;
import com.hibernate.model.PeticionesSalientes;
import com.hibernate.model.Usuarios;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.saem.notificadores.NotificadorSMS;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public class EnviarAlertaSaliente implements SessionAware {
    
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final PeticionesSalientesDAO peticionSalienteDAO = new PeticionesSalientesDAO();
    private final HospitalDAO hospitalDAO = new HospitalDAO();
    private Map<String, Object> session = null;
    HttpServletRequest request = ServletActionContext.getRequest();

    private List<Usuarios> listUsuarios;
    private List<PeticionesSalientes> listPeticiones;
    
    Usuarios userPaciente = new Usuarios();
    Pacientes paciente = new Pacientes();
    Contactos contacto = new Contactos();
    PeticionesSalientes peticionSaliente = new PeticionesSalientes();
    Hospitales hospital = new Hospitales();
    String codigoHospital;
    String latitudUsuario;
    String longitudUsuario;
    String nombreUsuario;
    String nss;
    
    String mensajeError = "";
    
    String idPeticionSaliente;
    String prioridadAlta = "1";
    String prioridadMedia = "2";
    String prioridadBaja = "3";
    String statusPP = "PP";
    String estatus;
    String recuperarEstatus;
    
    //campos json retorno
    private String tituloAlert;
    private String textoAlert;
    private String estatusMensaje;
    
    public String execute() throws ParseException, UnsupportedEncodingException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a datos pacientes");
        Boolean envioPeticion = false;
        String contactosPaciente = "";
        listUsuarios = usuarioDAO.listarById(nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                nss = paciente.getNss();
                nombreUsuario = userPaciente.getNombreUsuario();
                Set contactos = paciente.getContactoses();
                int i = 0;
                for (Iterator iterator3 = contactos.iterator(); iterator3.hasNext();) {
                    contacto = (Contactos) iterator3.next();
                    if(contactos.size() == 1)
                        contactosPaciente += contacto.getCelular();
                    else if(i==0) {
                        contactosPaciente += contacto.getCelular();
                        i++;
                    } 
                    else {
                        contactosPaciente += ","+contacto.getCelular();
                    }
                }
            }
        }
//        System.out.println(contactosPaciente);
//        NotificadorSMS sms = new NotificadorSMS("Estoy en este este lugar", contactosPaciente);
//        sms.enviarSMS();
        //Generamos el codigo de Historial Clinico
        Calendar cal = Calendar.getInstance();
        idPeticionSaliente = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DAY_OF_MONTH) + "" + cal.get(Calendar.HOUR) + "" + cal.get(Calendar.MINUTE) + "" + cal.get(Calendar.SECOND) + "" + cal.get(Calendar.MILLISECOND);
        
        //Buscamos el hospital que se encargara del paciente
        hospital = hospitalDAO.findById(codigoHospital);
    
        //Fecha de Registro
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);
        
        peticionSaliente.setIdPeticionesSalientes(idPeticionSaliente);
        peticionSaliente.setFechaRegistro(date);
        peticionSaliente.setEstatus(statusPP);
        peticionSaliente.setLatitudPaciente(latitudUsuario);
        peticionSaliente.setLongitudPaciente(longitudUsuario);
        peticionSaliente.setPrioridad(prioridadAlta);
        
        peticionSaliente.setHospitales(hospital);
        peticionSaliente.setPacientes(paciente);
        
        listPeticiones = peticionSalienteDAO.finByHospitalNss(s, nss);
        
        if(listPeticiones.isEmpty()) {
            if(peticionSalienteDAO.save(peticionSaliente)) {
                estatusMensaje = "exito";
            }
            else {
                estatusMensaje = "error";
                peticionSalienteDAO.delete(peticionSaliente);
            }
        }
        else {
            System.out.println("Hay peticiones echas por el usuario: " + nombreUsuario);
            estatusMensaje = "peticionEnviada";
        }

        return SUCCESS;
    }
    
    public String recuperarTotalPeticionesPacientes() {
        listUsuarios = usuarioDAO.listarById(nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set peticionesSalientes = paciente.getPeticionesSalienteses();
                for(Iterator iterato3 = peticionesSalientes.iterator(); iterato3.hasNext();) {
                    peticionSaliente = (PeticionesSalientes) iterato3.next();
                    estatus = peticionSaliente.getEstatus();
                }
                
            }
        }
        
        if(estatus == null) {
            System.out.println("no hay peticiones");
        }
        
        if(estatus.equals("PA")) {
            System.out.println("Peticion atendida");
            recuperarEstatus = estatus;
        }
        
        if(estatus.equals("PR")) {
            System.out.println("Peticion Rechazada");
            recuperarEstatus = estatus;
        }
        
        if(estatus.equals("PNA")) {
            System.out.println("Peticion no atendida");
            recuperarEstatus = estatus;
        }
        
        if(estatus.equals("PP")) {
            System.out.println("Peticion pediente");
            recuperarEstatus = estatus;
        }
        return SUCCESS;
    }

    /**
     *
     * @param session
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
   
    public String getCodigoHospital() {
        return codigoHospital;
    }

    public void setCodigoHospital(String codigoHospital) {
        this.codigoHospital = codigoHospital;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloAlert() {
        return tituloAlert;
    }

    public void setTituloAlert(String tituloAlert) {
        this.tituloAlert = tituloAlert;
    }

    public String getTextoAlert() {
        return textoAlert;
    }

    public void setTextoAlert(String textoAlert) {
        this.textoAlert = textoAlert;
    }

    public String getEstatusMensaje() {
        return estatusMensaje;
    }

    public void setEstatusMensaje(String estatusMensaje) {
        this.estatusMensaje = estatusMensaje;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getRecuperarEstatus() {
        return recuperarEstatus;
    }

    public void setRecuperarEstatus(String recuperarEstatus) {
        this.recuperarEstatus = recuperarEstatus;
    }
}