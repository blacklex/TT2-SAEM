package com.hibernate.model;
// Generated 28/05/2015 03:29:27 PM by Hibernate Tools 4.3.1




/**
 * Contactos generated by hbm2java
 */
public class Contactos  implements java.io.Serializable {


     private Long id;
     private Pacientes pacientes;
     private String nombre;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String parentesco;
     private String celular;
     private String facebook;
     private String correo;

    public Contactos() {
    }

	
    public Contactos(Pacientes pacientes, String nombre, String apellidoPaterno, String apellidoMaterno, String parentesco, String celular) {
        this.pacientes = pacientes;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.parentesco = parentesco;
        this.celular = celular;
    }
    public Contactos(Pacientes pacientes, String nombre, String apellidoPaterno, String apellidoMaterno, String parentesco, String celular, String facebook, String correo) {
       this.pacientes = pacientes;
       this.nombre = nombre;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.parentesco = parentesco;
       this.celular = celular;
       this.facebook = facebook;
       this.correo = correo;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Pacientes getPacientes() {
        return this.pacientes;
    }
    
    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getParentesco() {
        return this.parentesco;
    }
    
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getFacebook() {
        return this.facebook;
    }
    
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }




}


