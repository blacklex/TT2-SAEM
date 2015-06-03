package com.hibernate.model;
// Generated 2/06/2015 02:02:20 AM by Hibernate Tools 4.3.1




/**
 * Directivo generated by hbm2java
 */
public class Directivo  implements java.io.Serializable {


     private Long id;
     private Hospitales hospitales;
     private String nombre;
     private String telParticular;
     private String correo;
     private byte[] imagen;

    public Directivo() {
    }

	
    public Directivo(Hospitales hospitales, String nombre, String telParticular) {
        this.hospitales = hospitales;
        this.nombre = nombre;
        this.telParticular = telParticular;
    }
    public Directivo(Hospitales hospitales, String nombre, String telParticular, String correo, byte[] imagen) {
       this.hospitales = hospitales;
       this.nombre = nombre;
       this.telParticular = telParticular;
       this.correo = correo;
       this.imagen = imagen;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Hospitales getHospitales() {
        return this.hospitales;
    }
    
    public void setHospitales(Hospitales hospitales) {
        this.hospitales = hospitales;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelParticular() {
        return this.telParticular;
    }
    
    public void setTelParticular(String telParticular) {
        this.telParticular = telParticular;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public byte[] getImagen() {
        return this.imagen;
    }
    
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }




}


