/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author sergio
 */
public class MenuAdministradorAction extends ActionSupport{
    
    public String altaAdministrador(){
        return "pantallaAltaAdministrador";
    }
    
    public String modElmAdministrador(){
        return "pantallaModificarEliminarAdministrador";
    }
    
    public String consultarAdministrador(){
        return "pantallaConsultarAdministrador";
    }   
}