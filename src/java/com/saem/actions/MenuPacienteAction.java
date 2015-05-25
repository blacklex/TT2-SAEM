/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author gabriela
 */
public class MenuPacienteAction extends ActionSupport{
    public String altaPaciente(){
        return "pantallaAltaPaciente";
    }
    
    public String modElmPaciente(){
        return "pantallaModificarEliminarPaciente";
    }
    
    public String consultarPaciente(){
        return "pantallaConsultarPacientes";
    } 
}
