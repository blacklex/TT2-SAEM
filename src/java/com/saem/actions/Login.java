/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Usuarios;
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class Login implements SessionAware{
    private static final String LLAVE_ESTATUS_LOGIN ="LLAVE_ESTATUS_LOGIN";
    private Map<String, Object> session = null;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    HttpServletRequest request = ServletActionContext.getRequest();
    //campos del formulario
    private String formNombreUsuario;
    private String formClave;
    private String formCheckRecordarme;
    
    private String tituloAlert = "";
    private String textoAlert = "";
    private String estatusMensaje = "";

   
    
    public String execute() {
        System.out.println("\n\nEntro al execute"+formNombreUsuario+"  "+formClave+"  "+formCheckRecordarme );
        Usuarios usuario =  usuarioDAO.findById(formNombreUsuario);
        
        if(usuario==null){
            tituloAlert="Error al Iniciar Sesion";
            textoAlert="El Usuario o Clave de Acceso no son validos.";
            estatusMensaje = "errorLogin";
            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_LOGIN, estatusMensaje);
            return Action.LOGIN;
        }
        
        String tipoUsuario = usuario.getTipoUsuario();
        String nombreUsuario = usuario.getNombreUsuario();
        String clave = usuario.getClave();
        
        if(!(nombreUsuario.equals(formNombreUsuario) && clave.equals(formClave))){
            tituloAlert="Error al Iniciar Sesion";
            textoAlert="El Usuario o Clave de Acceso no son validos.";
            estatusMensaje = "errorLogin";
            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_LOGIN, estatusMensaje);
            return Action.LOGIN;
        }
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

    public String recuperarEstatusLogin() {
        System.out.println("-->Entro a recuperar estatus login");
        tituloAlert = "";
        textoAlert = "";
        estatusMensaje = "";

        if (session.get(LLAVE_ESTATUS_LOGIN) != null) {
            tituloAlert = session.get("tituloAlert").toString();
            textoAlert = session.get("textoAlert").toString();
            estatusMensaje = session.get(LLAVE_ESTATUS_LOGIN).toString();
        }

        session.remove(LLAVE_ESTATUS_LOGIN);
        session.remove("tituloAlert");
        session.remove("textoAlert");
        session.remove(LLAVE_ESTATUS_LOGIN);
        session.put(LLAVE_ESTATUS_LOGIN, null);

        return SUCCESS;
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

    public String getTituloAlert() {
        return tituloAlert;
    }

    public void setTituloAlert(String tituloAlert) {
        this.tituloAlert = tituloAlert;
    }

    public String getTextoAlert() {
        return textoAlert;
    }

    public void setTextoAlert(String textoAlert) {
        this.textoAlert = textoAlert;
    }

    public String getEstatusMensaje() {
        return estatusMensaje;
    }

    public void setEstatusMensaje(String estatusMensaje) {
        this.estatusMensaje = estatusMensaje;
    }
    
    
    
}
