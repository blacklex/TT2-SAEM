package com.hibernate.model;
// Generated 2/06/2015 02:02:20 AM by Hibernate Tools 4.3.1



/**
 * Discapacidades generated by hbm2java
 */
public class Discapacidades  implements java.io.Serializable {


     private Long id;
     private DatosClinicos datosClinicos;
     private String tipo;

    public Discapacidades() {
    }

    public Discapacidades(DatosClinicos datosClinicos, String tipo) {
       this.datosClinicos = datosClinicos;
       this.tipo = tipo;
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
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}


