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
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author sergio
 */
public class ModificarEliminarAdministradorAction extends ActionSupport implements SessionAware, ServletRequestAware{
    
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
    Boolean r1 = false;
    Boolean r2 = false;
    Boolean r3 = false;
    String mensajeError = "";

    public String eliminarAdministrador() {
        if (usuarioDAO.deleteAdmin(nombreUsuario)) {
            estatusMensajeEliminar = "usuarioEncontrado";
            System.err.println("Usuario eliminado--->" + nombreUsuario);
            return "pantallaModificarEliminarAdministrador";
        }
        else {
            mensajeError = "Error al eliminar Administrador";
            estatusMensajeEliminar = "usuarioNoEncontrado";
        }
        return "pantallaModificarEliminarAdministrador";
    }
    
    public String eliminarAdministradorPorFiltro() {
        if (usuarioDAO.deleteAdmin(nombreUsuario)) {
            estatusMensajeEliminar = "usuarioEncontrado";
            System.err.println("Usuario eliminado--->" + nombreUsuario);
            return "pantallaModificarEliminarAdministrador";
        }
        else {
            mensajeError = "Error al eliminar Administrador";
            estatusMensajeEliminar = "usuarioNoEncontrado";
        }
        return "pantallaModificarEliminarAdministrador";
    }
    
    public String editarAccesoAdministrador() throws ParseException {
        Boolean actualizacionCorrecta = false;
        userAdmin = usuarioDAO.findById(nombreUsuario);
        //Obtenemos la fecha de registro por que no se va a modificar y la volvemos a setear dentro de Usuario
        Date fecha = userAdmin.getFechaRegistro();
        Date date = fecha;
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);

        userAdmin = new Usuarios(nombreUsuario, tipoUsuario, clave, date);
        if(usuarioDAO.update(userAdmin)) {
          actualizacionCorrecta = true;
         }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Administrador";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Administrador Editado");
            session.put("textoAlertEditar", "Los datos del Administrador fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar administrador.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        return "pantallaModificarEliminarAdministrador";
    }
    
    public String editarDatosPersonalesAdministrador() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userAdmin = usuarioDAO.findById(nombreUsuario);
        
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
        administrador.setUsuarios(userAdmin);
        if(administradorDAO.update(administrador)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Administrador";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Administrador Editado");
            session.put("textoAlertEditar", "Los datos del Administrador fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar administrador.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarAdministrador";
    }
    
    public String editarDireccionAdministrador() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userAdmin = usuarioDAO.findById(nombreUsuario);
        
        administrador = administradorDAO.findById(telParticular);
                
        //Establecemos los datos de dirección para el Aministrador
        domicilioAdmin.setId(id);
        domicilioAdmin.setCalle(calle);
        domicilioAdmin.setColonia(colonia);
        domicilioAdmin.setDelegacion(delegacion);
        domicilioAdmin.setEntidadFederativa(entidadFederativa);
        domicilioAdmin.setCodigoPostal(codigoPostal);
        domicilioAdmin.setAdministradores(administrador);
        
        if(domicilioAdminDAO.update(domicilioAdmin)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Administrador";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Administrador Editado");
            session.put("textoAlertEditar", "Los datos del Administrador fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar administrador.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarAdministrador";
    }
    
    public String editarAdminPorFiltro() throws ParseException, FileNotFoundException, IOException {
        Boolean actualizacionCorrecta = false;
        userAdmin = usuarioDAO.findById(nombreUsuario);
        
        //Obtenemos la fecha de registro por que no se va a modificar y la volvemos a setear dentro de Usuario
        Date fecha = userAdmin.getFechaRegistro();
        Date date = fecha;
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
        r1 = usuarioDAO.update(userAdmin);
        
        //Establecemos la clave foranea del Aministrador
        administrador.setUsuarios(userAdmin);
        
        //Establecemos los datos de dirección para el Aministrador
        domicilioAdmin.setId(id);
        domicilioAdmin.setCalle(calle);
        domicilioAdmin.setColonia(colonia);
        domicilioAdmin.setDelegacion(delegacion);
        domicilioAdmin.setEntidadFederativa(entidadFederativa);
        domicilioAdmin.setCodigoPostal(codigoPostal);
        
        //Guardamos los datos personales para el Aministrador
        r2 = administradorDAO.update(administrador);
        
        //Establecemos la clave foranea del domicilio del Aministrador
        domicilioAdmin.setAdministradores(administrador);
        
        //Guardamos los datos del domicilio para el Aministrador
        r3 = domicilioAdminDAO.update(domicilioAdmin);
        
        if(r1 && r2 && r3)
            actualizacionCorrecta = true;
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar Administrador";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Administrador Editado");
            session.put("textoAlertEditar", "Los datos del Administrador fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar administrador.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }

        return "pantallaModificarEliminarAdministrador";
    }

    public String recuperarEstatusEditar() {
        tituloAlertEditar = "";
        textoAlertEditar = "";
        estatusMensajeEditar = "";
        
        if (session.get("estatusMensajeEditar") != null) {
            tituloAlertEditar = session.get("tituloAlertEditar").toString();
            textoAlertEditar = session.get("textoAlertEditar").toString();
            estatusMensajeEditar = session.get("estatusMensajeEditar").toString();
        }

        session.remove("estatusMensajeEditar");
        session.remove("tituloAlertEditar");
        session.remove("estatusMensajeEditar");
        session.put("estatusMensajeEditar", null);

        return "success";
    }
    
    public String buscarDatosPersonalesAdministrador() throws IOException {
        listUsuarios = usuarioDAO.listarById(nombreUsuario);
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
                FileOutputStream image = new FileOutputStream(filePath+"imagenesPerfilAdmin/"+nombreUsuario+".jpeg");
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
        return "success";
    }
    
    public String buscarDatosDireccionAdministrador() {
        listUsuarios = usuarioDAO.listarById(nombreUsuario);
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
        return "success";
    }
    
    public String buscarDatosPorFiltro() throws FileNotFoundException, IOException {
        Usuarios usuarioResultado;
        ArrayList<Usuarios> listaTemp = new ArrayList<Usuarios>();
        if (nombreUsuario.length() > 0) {
                listaTemp = (ArrayList<Usuarios>) usuarioDAO.findUsuariosLike(nombreUsuario);
                System.out.println("--->Entro a filtro mayor " + listaTemp.size());
                if(listaTemp==null){
                     estatusMensajeEliminar = "usuarioNoEncontrado";
                }else{estatusMensajeEliminar = "usuarioEncontrado";}
                
                
            } else {
                // Obtenemos la lista de la sesión
                listaTemp = (ArrayList<Usuarios>) usuarioDAO.listar(0, 0);
                estatusMensajeEliminar = "usuarioEncontrado";
            }
            session.put(com.saem.actions.GridRegistroAdministradoresAction.LISTA_GRID_MODEL, listaTemp);
            
        /*usuarioResultado = usuarioDAO.findById(nombreUsuario);
        
        if (usuarioResultado == null) {
            estatusMensajeEliminar = "usuarioNoEncontrado";
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(nombreUsuario)) {
            estatusMensajeEliminar = "usuarioEncontrado";
            listUsuarios = usuarioDAO.listarById(nombreUsuario);
            for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
                userAdmin = (Usuarios) iterator1.next();
                Set administradores = userAdmin.getAdministradoreses();
                for (Iterator iterator2 = administradores.iterator(); iterator2.hasNext();) {
                    administrador = (Administradores) iterator2.next(); 
                    Set domAdmin = administrador.getDomicilioAdministradoreses();
                    for (Iterator iterator3 = domAdmin.iterator(); iterator3.hasNext();) {
                        domicilioAdmin = (DomicilioAdministradores) iterator3.next();
                        nombreUsuario = userAdmin.getNombreUsuario();
                        clave = userAdmin.getClave();
                        nombre = administrador.getNombre();
                        apellidoPaterno = administrador.getApellidoPaterno();
                        apellidoMaterno = administrador.getApellidoMaterno();
                        telParticular = administrador.getTelParticular();
                        correo = administrador.getCorreo();
                        imagenAdmin = administrador.getImagen();
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                        System.out.println(filePath);
                        FileOutputStream image = new FileOutputStream(filePath+"imagenesPerfilAdmin/"+nombreUsuario+".jpeg");
                        image.write(imagenAdmin);
                        calle = domicilioAdmin.getCalle();
                        colonia = domicilioAdmin.getColonia();
                        delegacion = domicilioAdmin.getDelegacion();
                        entidadFederativa = domicilioAdmin.getEntidadFederativa();
                        codigoPostal = domicilioAdmin.getCodigoPostal();
                        id = domicilioAdmin.getId();
                        image.close();
                    }
                }
            }
        } 
        else {
            estatusMensajeEliminar = "usuarioNoEncontrado";
        }*/
        return "success";
    }

    public String validarNombreUsuarioEliminar() {
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(nombreUsuario);

        if (usuarioResultado == null) {
            estatusMensajeEliminar = "usuarioNoEncontrado";
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(nombreUsuario)) {
            estatusMensajeEliminar = "usuarioEncontrado";
        } else {
            estatusMensajeEliminar = "usuarioNoEncontrado";
        }

        return SUCCESS;
    }

    public Map<String, Object> getSession() {
        return session;
    }

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
    
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
            this.servletRequest = servletRequest;
    }
}