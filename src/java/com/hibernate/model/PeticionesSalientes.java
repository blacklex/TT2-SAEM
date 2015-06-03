package com.hibernate.model;
// Generated 2/06/2015 02:02:20 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * PeticionesSalientes generated by hbm2java
 */
public class PeticionesSalientes  implements java.io.Serializable {


     private String idPeticionesSalientes;
     private Hospitales hospitales;
     private Pacientes pacientes;
     private Date fechaRegistro;
     private String estatus;
     private String latitudPaciente;
     private String longitudPaciente;
     private String prioridad;
     private String comentario;

    public PeticionesSalientes() {
    }

	
    public PeticionesSalientes(String idPeticionesSalientes, Hospitales hospitales, Pacientes pacientes, Date fechaRegistro, String estatus, String latitudPaciente, String longitudPaciente, String prioridad) {
        this.idPeticionesSalientes = idPeticionesSalientes;
        this.hospitales = hospitales;
        this.pacientes = pacientes;
        this.fechaRegistro = fechaRegistro;
        this.estatus = estatus;
        this.latitudPaciente = latitudPaciente;
        this.longitudPaciente = longitudPaciente;
        this.prioridad = prioridad;
    }
    public PeticionesSalientes(String idPeticionesSalientes, Hospitales hospitales, Pacientes pacientes, Date fechaRegistro, String estatus, String latitudPaciente, String longitudPaciente, String prioridad, String comentario) {
       this.idPeticionesSalientes = idPeticionesSalientes;
       this.hospitales = hospitales;
       this.pacientes = pacientes;
       this.fechaRegistro = fechaRegistro;
       this.estatus = estatus;
       this.latitudPaciente = latitudPaciente;
       this.longitudPaciente = longitudPaciente;
       this.prioridad = prioridad;
       this.comentario = comentario;
    }
   
    public String getIdPeticionesSalientes() {
        return this.idPeticionesSalientes;
    }
    
    public void setIdPeticionesSalientes(String idPeticionesSalientes) {
        this.idPeticionesSalientes = idPeticionesSalientes;
    }
    public Hospitales getHospitales() {
        return this.hospitales;
    }
    
    public void setHospitales(Hospitales hospitales) {
        this.hospitales = hospitales;
    }
    public Pacientes getPacientes() {
        return this.pacientes;
    }
    
    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public String getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    public String getLatitudPaciente() {
        return this.latitudPaciente;
    }
    
    public void setLatitudPaciente(String latitudPaciente) {
        this.latitudPaciente = latitudPaciente;
    }
    public String getLongitudPaciente() {
        return this.longitudPaciente;
    }
    
    public void setLongitudPaciente(String longitudPaciente) {
        this.longitudPaciente = longitudPaciente;
    }
    public String getPrioridad() {
        return this.prioridad;
    }
    
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }




}


