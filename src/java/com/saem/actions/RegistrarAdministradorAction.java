/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.AdministradorDAO;
import com.hibernate.dao.DomicilioAdministradorDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Administradores;
import com.hibernate.model.DomicilioAdministradores;
import com.hibernate.model.Usuarios;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.struts2.interceptor.SessionAware;
import static com.opensymphony.xwork2.Action.SUCCESS;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author sergio
 */
public class RegistrarAdministradorAction implements SessionAware, ServletRequestAware {
    
    private Map<String, Object> session = null;
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final DomicilioAdministradorDAO domicilioAdminDAO = new DomicilioAdministradorDAO();

    private HttpServletRequest servletRequest;
    
    //Acceso
    String nombreUsuario;
    String clave;
    String tipoUsuario;
    //Datos Personales
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String telParticular;
    String correo;
    File imagen;
    private String userImageContentType;
    private String userImageFileName;
    //Direccion
    String calle;
    String colonia;
    String delegacion;
    String entidadFederativa;
    String codigoPostal;
    //campos json retorno
    String tituloAlert;
    String textoAlert;
    String estatusMensaje;
    Boolean r1 = false;
    Boolean r2 = false;
    Boolean r3 = false;
    String mensajeError = "";

    public String registrarAdministrador() throws ParseException, FileNotFoundException, IOException {
        Boolean registroCorrecto = false;
        Administradores administrador = new Administradores();
        DomicilioAdministradores domicilioAdmin = new DomicilioAdministradores();
        Usuarios userAdmin = new Usuarios();
        String lada;
        lada = telParticular.substring(0, 4);
        telParticular = telParticular.substring(5, telParticular.length());
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);
        
        //Establecemos los datos de acceso para el Aministrador
        userAdmin = new Usuarios(nombreUsuario, tipoUsuario, clave, date);
        
        //Establecemos los datos personales para el Aministrador
        administrador.setApellidoMaterno(apellidoPaterno);
        administrador.setApellidoPaterno(apellidoMaterno);
        administrador.setCorreo(correo);
        administrador.setNombre(nombre);
        administrador.setTelParticular(telParticular);
        //Convertimos la imagen a un arreglo de
        if(imagen != null) {
            System.out.println("Camino absoluto    "+imagen.getAbsolutePath());
            byte[] bFile = new byte[(int) imagen.length()];
            FileInputStream fileInputStream = new FileInputStream(imagen);
            fileInputStream.read(bFile);
            fileInputStream.close();
            administrador.setImagen(bFile);
        }
        else{
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileImg = new File(filePath+"imagenesPerfilAdmin/default/default.jpeg");
            byte[] defaultFile = new byte[(int) fileImg.length()];
            FileInputStream imgDefault = new FileInputStream(fileImg);
            imgDefault.read(defaultFile);
            imgDefault.close();
            administrador.setImagen(defaultFile);
        }
            
        //Guardamos los datos de acceso del Aministrador
        r1 = usuarioDAO.save(userAdmin);
        
        //Establecemos la clave foranea del Aministrador
        administrador.setUsuarios(userAdmin);
        
        //Establecemos los datos de dirección para el Aministrador
        domicilioAdmin.setCalle(calle);
        domicilioAdmin.setColonia(colonia);
        domicilioAdmin.setDelegacion(delegacion);
        domicilioAdmin.setEntidadFederativa(entidadFederativa);
        domicilioAdmin.setCodigoPostal(codigoPostal);
        
        //Guardamos los datos personales para el Aministrador
        r2 = administradorDAO.save(administrador);
        
        //Establecemos la clave foranea del domicilio del Aministrador
        domicilioAdmin.setAdministradores(administrador);
        
        //Guardamos los datos del domicilio para el Aministrador
        r3 = domicilioAdminDAO.save(domicilioAdmin);
        
        if(r1 && r2 && r3)
            registroCorrecto = true;
        else {
            registroCorrecto = false;
            mensajeError = "Error al ingresar Administrador";
        }
        
        if (registroCorrecto) {
            session.put("tituloAlert", "Administrador Registrado");
            session.put("textoAlert", "El Administrador fue registrado exitosamente.");
            session.put("estatusMensaje", "success");

        } else if (!registroCorrecto) {
            session.put("tituloAlert", "Error al registrar administrador.");
            session.put("textoAlert", mensajeError);
            session.put("estatusMensaje", "error");
        }

        return "pantallaAltaAdministrador";
    }
    
    public String recuperarEstatusAdmin() {

        tituloAlert = "";
        textoAlert = "";
        estatusMensaje = "";

        if (session.get("estatusMensaje") != null) {
            tituloAlert = session.get("tituloAlert").toString();
            textoAlert = session.get("textoAlert").toString();
            estatusMensaje = session.get("estatusMensaje").toString();
        }

        session.remove("estatusMensaje");
        session.remove("tituloAlert");
        session.remove("estatusMensaje");
        session.put("estatusMensaje", null);

        return "success";
    }
    
    public String validarNombreUsuarioAdmin() {
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(nombreUsuario);

        if (usuarioResultado == null) {
            estatusMensaje = "nombreValido";
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(nombreUsuario)) {
            estatusMensaje = "nombreNoValido";
        } else {
            estatusMensaje = "nombreValido";
        }

        return SUCCESS;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    /**
     *
     * @param session
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelParticular() {
        return telParticular;
    }

    public void setTelParticular(String telParticular) {
        this.telParticular = telParticular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }
    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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
    
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
            this.servletRequest = servletRequest;
    }
}