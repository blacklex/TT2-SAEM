/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.PacienteDAO;
import com.hibernate.dao.PeticionesEntrantesDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Hospitales;
import com.hibernate.model.Pacientes;
import com.hibernate.model.PeticionesEntrantes;
import com.hibernate.model.Usuarios;
import static com.opensymphony.xwork2.Action.SUCCESS;
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

/**
 *
 * @author sergio
 */
public class AcudirHospital implements SessionAware {
    
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final PeticionesEntrantesDAO peticionEntranteDAO = new PeticionesEntrantesDAO();
    private final HospitalDAO hospitalDAO = new HospitalDAO();
    private Map<String, Object> session = null;
    HttpServletRequest request = ServletActionContext.getRequest();
    
    private List<Usuarios> listUsuarios;    
    
    Usuarios userPaciente = new Usuarios();
    Pacientes paciente = new Pacientes();
    PeticionesEntrantes peticionEntrante = new PeticionesEntrantes();
    Hospitales hospital = new Hospitales();
    String codigoHospital;
    String latitudUsuario;
    String longitudUsuario;
    String nombreUsuario;
    String nss;

    String mensajeError = "";
    
    String idPeticionEntrante;
    String prioridadAlta = "1";
    String prioridadMedia = "2";
    String prioridadBaja = "3";
    String statusPP = "PP";
    
    //campos json retorno
    private String tituloAlert;
    private String textoAlert;
    private String estatusMensaje;
    public String execute() throws ParseException {
        
        System.out.println("--->Entro a datos pacientes");
        Boolean envioPeticion = false;
        listUsuarios = usuarioDAO.listarById(nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                nss = paciente.getNss();
                nombreUsuario = userPaciente.getNombreUsuario();                
            }
        }
        //Generamos el codigo de Historial Clinico
        Calendar cal = Calendar.getInstance();
        idPeticionEntrante = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DAY_OF_MONTH) + "" + cal.get(Calendar.HOUR) + "" + cal.get(Calendar.MINUTE) + "" + cal.get(Calendar.SECOND) + "" + cal.get(Calendar.MILLISECOND);
        
        //Buscamos el hospital que se encargara del paciente
        hospital = hospitalDAO.findById(codigoHospital);
    
        //Fecha de Registro
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);
        
        peticionEntrante.setIdPeticionesEntrantes(idPeticionEntrante);
        peticionEntrante.setFechaRegistro(date);
        peticionEntrante.setEstatus(statusPP);
        peticionEntrante.setLatitudPaciente(latitudUsuario);
        peticionEntrante.setLongitudPaciente(longitudUsuario);
        peticionEntrante.setPrioridad(prioridadBaja);
        
        peticionEntrante.setHospitales(hospital);
        peticionEntrante.setPacientes(paciente);
        
        if(peticionEntranteDAO.save(peticionEntrante)) {
            estatusMensaje = "exito";
        }
        else {
            estatusMensaje = "error";
            peticionEntranteDAO.delete(peticionEntrante);
        }
        
        return SUCCESS;
    }
      
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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
    
}