package com.hibernate.model;
// Generated 28/05/2015 03:29:27 PM by Hibernate Tools 4.3.1



/**
 * Alergias generated by hbm2java
 */
public class Alergias  implements java.io.Serializable {


     private Long id;
     private DatosClinicos datosClinicos;
     private String tipoAlergia;
     private String especificacion;

    public Alergias() {
    }

	
    public Alergias(DatosClinicos datosClinicos, String tipoAlergia) {
        this.datosClinicos = datosClinicos;
        this.tipoAlergia = tipoAlergia;
    }
    public Alergias(DatosClinicos datosClinicos, String tipoAlergia, String especificacion) {
       this.datosClinicos = datosClinicos;
       this.tipoAlergia = tipoAlergia;
       this.especificacion = especificacion;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public DatosClinicos getDatosClinicos() {
        return this.datosClinicos;
    }
    
    public void setDatosClinicos(DatosClinicos datosClinicos) {
        this.datosClinicos = datosClinicos;
    }
    public String getTipoAlergia() {
        return this.tipoAlergia;
    }
    
    public void setTipoAlergia(String tipoAlergia) {
        this.tipoAlergia = tipoAlergia;
    }
    public String getEspecificacion() {
        return this.especificacion;
    }
    
    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }




}


