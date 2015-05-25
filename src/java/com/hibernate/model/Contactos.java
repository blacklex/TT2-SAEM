package com.hibernate.model;
// Generated 22-may-2015 20:27:16 by Hibernate Tools 4.3.1



/**
 * Contactos generated by hbm2java
 */
public class Contactos  implements java.io.Serializable {


     private Long id;
     private Pacientes pacientes;
     private String nombreC;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String parentesco;
     private String celular;
     private String facebook;
     private String correo;

    public Contactos() {
    }

	
    public Contactos(Pacientes pacientes, String nombreC, String apellidoPaterno, String apellidoMaterno, String parentesco, String celular) {
        this.pacientes = pacientes;
        this.nombreC = nombreC;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.parentesco = parentesco;
        this.celular = celular;
    }
    public Contactos(Pacientes pacientes, String nombreC, String apellidoPaterno, String apellidoMaterno, String parentesco, String celular, String facebook, String correo) {
       this.pacientes = pacientes;
       this.nombreC = nombreC;
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
  
    public String getApellidoPaternoC() {
        return this.getApellidoPaterno();
    }
    
    public void setApellidoPaternoC(String apellidoPaterno) {
        this.setApellidoPaterno(apellidoPaterno);
    }
    public String getApellidoMaternoC() {
        return this.getApellidoMaterno();
    }
    
    public void setApellidoMaternoC(String apellidoMaterno) {
        this.setApellidoMaterno(apellidoMaterno);
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
    public String getFacebookC() {
        return this.getFacebook();
    }
    
    public void setFacebookC(String facebook) {
        this.setFacebook(facebook);
    }
    public String getCorreoC() {
        return this.getCorreo();
    }
    
    public void setCorreoC(String correo) {
        this.setCorreo(correo);
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
    
    public String getNombre() {
        return nombreC;
    }

    public void setNombre(String nombreC) {
        this.nombreC = nombreC;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }




}


