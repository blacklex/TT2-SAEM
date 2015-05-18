/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Usuarios;
import com.opensymphony.xwork2.Action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class Login implements SessionAware{
    //HttpServletRequest request = ServletActionContext.getRequest();
    private Map<String, Object> session = null;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
  
    //campos del formulario
    private String formNombreUsuario;
    private String formClave;
    private String formCheckRecordarme;

   
    
    public String execute() {
        System.out.println("\n\nEntro al execute"+formNombreUsuario+"  "+formClave+"  "+formCheckRecordarme );
        Usuarios usuario =  usuarioDAO.findById(formNombreUsuario);
        
        if(usuario==null){
            return Action.LOGIN;
        }
        
        String tipoUsuario = usuario.getTipoUsuario();
        String nombreUsuario = usuario.getNombreUsuario();
        String clave = usuario.getClave();
        
        if(!(nombreUsuario.equals(formNombreUsuario) && clave.equals(formClave)))
            return Action.LOGIN;
        
        session.put("nombreUsuario", nombreUsuario);
        session.put("tipoUsuario", tipoUsuario);
        
        if(tipoUsuario.toUpperCase().equals("ADMINISTRADOR"))
            return "pantallaInicioAdministrador";
        
        if(tipoUsuario.toUpperCase().equals("PACIENTE"))
            return "pantallaInicioPaciente";
        
        if(tipoUsuario.toUpperCase().equals("HOSPITAL"))
            return "pantallaInicioHospital";
        
        return Action.LOGIN;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
     public String getFormNombreUsuario() {
        return formNombreUsuario;
    }

    public void setFormNombreUsuario(String formNombreUsuario) {
        this.formNombreUsuario = formNombreUsuario;
    }

    public String getFormClave() {
        return formClave;
    }

    public void setFormClave(String formClave) {
        this.formClave = formClave;
    }

    public String getFormCheckRecordarme() {
        return formCheckRecordarme;
    }

    public void setFormCheckRecordarme(String formCheckRecordarme) {
        this.formCheckRecordarme = formCheckRecordarme;
    }
    
}
