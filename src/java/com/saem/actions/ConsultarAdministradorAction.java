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
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public class ConsultarAdministradorAction extends ActionSupport implements SessionAware, ServletRequestAware {

    private Map<String, Object> session = null;
    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final DomicilioAdministradorDAO domicilioAdminDAO = new DomicilioAdministradorDAO();
    private List<Administradores> listAdministradores;
    private List<Usuarios> listUsuarios;
    Administradores administrador = new Administradores();
    DomicilioAdministradores domicilioAdmin = new DomicilioAdministradores();
    Usuarios userAdmin = new Usuarios();

    private HttpServletRequest servletRequest;

    //Acceso
    String nombreUsuario; //Clave Primaria
    String clave;
    String tipoUsuario;
    //Datos Personales
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String telParticular; //Clave Primaria
    String correo;
    File imagen;
    byte[] imagenAdmin;
    private String userImageContentType;
    private String userImageFileName;
    //Direccion
    Long id; //Clave Primaria
    String calle;
    String colonia;
    String delegacion;
    String entidadFederativa;
    String codigoPostal;
    //campos json retorno
    String tituloAlertEditar;
    String textoAlertEditar;
    String estatusMensajeEliminar;
    String estatusMensajeEditar;
    String mensajeError = "";

    public String buscarDatosPersonalesAdministrador() throws IOException {
        System.out.println("---> BuscarDAtosPersonales");
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userAdmin = (Usuarios) iterator1.next();
            Set administradores = userAdmin.getAdministradoreses();
            for (Iterator iterator2 = administradores.iterator(); iterator2.hasNext();) {
                administrador = (Administradores) iterator2.next();
                nombre = administrador.getNombre();
                apellidoPaterno = administrador.getApellidoPaterno();
                apellidoMaterno = administrador.getApellidoMaterno();
                telParticular = administrador.getTelParticular();
                correo = administrador.getCorreo();
                imagenAdmin = administrador.getImagen();
                String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                System.out.println(filePath);
                FileOutputStream image = new FileOutputStream(filePath + "imagenesPerfilAdmin/" + nombreUsuario + ".jpeg");
                image.write(imagenAdmin);
                nombreUsuario = userAdmin.getNombreUsuario();
                System.out.println(nombre);
                System.out.println(apellidoPaterno);
                System.out.println(apellidoMaterno);
                System.out.println(telParticular);
                System.out.println(correo);
                System.out.println(nombreUsuario);
                image.close();
            }
        }
        s.close();
        return "success";
    }

    public String buscarDatosDireccionAdministrador() {
        System.out.println("---> BuscarDAtosDireccion");
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userAdmin = (Usuarios) iterator1.next();
            Set administradores = userAdmin.getAdministradoreses();
            for (Iterator iterator2 = administradores.iterator(); iterator2.hasNext();) {
                administrador = (Administradores) iterator2.next();
                Set domAdmin = administrador.getDomicilioAdministradoreses();
                for (Iterator iterator3 = domAdmin.iterator(); iterator3.hasNext();) {
                    domicilioAdmin = (DomicilioAdministradores) iterator3.next();
                    calle = domicilioAdmin.getCalle();
                    colonia = domicilioAdmin.getColonia();
                    delegacion = domicilioAdmin.getDelegacion();
                    entidadFederativa = domicilioAdmin.getEntidadFederativa();
                    codigoPostal = domicilioAdmin.getCodigoPostal();
                    id = domicilioAdmin.getId();
                    telParticular = administrador.getTelParticular();
                    nombreUsuario = userAdmin.getNombreUsuario();
                    System.out.println(id);
                    System.out.println(calle);
                    System.out.println(colonia);
                    System.out.println(delegacion);
                    System.out.println(entidadFederativa);
                    System.out.println(codigoPostal);
                    System.out.println(telParticular);
                    System.out.println(nombreUsuario);
                }
            }
        }
        s.close();
        return "success";
    }

    public String buscarDatosMostrarFiltro() throws FileNotFoundException, IOException {
        System.out.println("---> BuscarDAtosFiltro");
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        ArrayList<Usuarios> listaTemp = new ArrayList<Usuarios>();
        ArrayList<Usuarios> listaFinal = new ArrayList<Usuarios>();

        if (nombreUsuario.length() > 0) {
            listaTemp = (ArrayList<Usuarios>) usuarioDAO.findUsuariosLike(s, nombreUsuario);
            System.out.println("--->Entro a filtro mayor " + listaTemp.size());
            if (listaTemp == null) {
                estatusMensajeEliminar = "usuarioNoEncontrado";
            } else {
                estatusMensajeEliminar = "usuarioEncontrado";
            }

        } else {
            // Obtenemos la lista de la sesión
            listaTemp = (ArrayList<Usuarios>) usuarioDAO.listar(s, 0, 0);
            estatusMensajeEliminar = "usuarioEncontrado";
        }
        for(Usuarios usuarioTemp : listaTemp){
            listaFinal.add(new Usuarios(usuarioTemp.getNombreUsuario(), usuarioTemp.getTipoUsuario(), "", usuarioTemp.getFechaRegistro()));
        }
        
        session.put(com.saem.actions.GridRegistroAdministradoresAction.LISTA_GRID_MODEL, listaFinal);
        s.close();
        return "success";
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

    public Administradores getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administradores administrador) {
        this.administrador = administrador;
    }

    public DomicilioAdministradores getDomicilioAdmin() {
        return domicilioAdmin;
    }

    public void setDomicilioAdmin(DomicilioAdministradores domicilioAdmin) {
        this.domicilioAdmin = domicilioAdmin;
    }

    public Usuarios getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(Usuarios userAdmin) {
        this.userAdmin = userAdmin;
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

    public String getTituloAlertEditar() {
        return tituloAlertEditar;
    }

    public void setTituloAlertEditar(String tituloAlertEditar) {
        this.tituloAlertEditar = tituloAlertEditar;
    }

    public String getTextoAlertEditar() {
        return textoAlertEditar;
    }

    public void setTextoAlertEditar(String textoAlertEditar) {
        this.textoAlertEditar = textoAlertEditar;
    }

    public String getEstatusMensajeEliminar() {
        return estatusMensajeEliminar;
    }

    public void setEstatusMensajeEliminar(String estatusMensajeEliminar) {
        this.estatusMensajeEliminar = estatusMensajeEliminar;
    }

    public String getEstatusMensajeEditar() {
        return estatusMensajeEditar;
    }

    public void setEstatusMensajeEditar(String estatusMensajeEditar) {
        this.estatusMensajeEditar = estatusMensajeEditar;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImagenAdmin() {
        return imagenAdmin;
    }

    public void setImagenAdmin(byte[] imagenAdmin) {
        this.imagenAdmin = imagenAdmin;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
