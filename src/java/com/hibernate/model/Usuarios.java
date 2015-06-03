package com.hibernate.model;
// Generated 2/06/2015 02:02:20 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private String nombreUsuario;
     private String tipoUsuario;
     private String clave;
     private Date fechaRegistro;
     private Set pacienteses = new HashSet(0);
     private Set administradoreses = new HashSet(0);
     private Set hospitaleses = new HashSet(0);

    public Usuarios() {
    }

	
    public Usuarios(String nombreUsuario, String tipoUsuario, String clave, Date fechaRegistro) {
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
        this.clave = clave;
        this.fechaRegistro = fechaRegistro;
    }
    public Usuarios(String nombreUsuario, String tipoUsuario, String clave, Date fechaRegistro, Set pacienteses, Set administradoreses, Set hospitaleses) {
       this.nombreUsuario = nombreUsuario;
       this.tipoUsuario = tipoUsuario;
       this.clave = clave;
       this.fechaRegistro = fechaRegistro;
       this.pacienteses = pacienteses;
       this.administradoreses = administradoreses;
       this.hospitaleses = hospitaleses;
    }
   
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getTipoUsuario() {
        return this.tipoUsuario;
    }
    
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Set getPacienteses() {
        return this.pacienteses;
    }
    
    public void setPacienteses(Set pacienteses) {
        this.pacienteses = pacienteses;
    }
    public Set getAdministradoreses() {
        return this.administradoreses;
    }
    
    public void setAdministradoreses(Set administradoreses) {
        this.administradoreses = administradoreses;
    }
    public Set getHospitaleses() {
        return this.hospitaleses;
    }
    
    public void setHospitaleses(Set hospitaleses) {
        this.hospitaleses = hospitaleses;
    }




}


