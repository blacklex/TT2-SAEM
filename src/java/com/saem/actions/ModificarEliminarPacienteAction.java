/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.ContactoDAO;
import com.hibernate.dao.DatosClinicosDAO;
import com.hibernate.dao.DatosPersonalesDAO;
import com.hibernate.dao.DomicilioPacienteDAO;
import com.hibernate.dao.TelefonoPacienteDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.dao.PacienteDAO;
import com.hibernate.model.Contactos;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.DomicilioPacientes;
import com.hibernate.model.TelefonosPacientes;
import com.hibernate.model.Usuarios;
import com.hibernate.model.Pacientes;
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
 * @author gabriela
 */
public class ModificarEliminarPacienteAction extends ActionSupport implements SessionAware, ServletRequestAware{

    private Map<String, Object> session = null;
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final DomicilioPacienteDAO domicilioPacienteDAO = new DomicilioPacienteDAO();
    private final TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private final DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
    private final ContactoDAO contactoDAO = new ContactoDAO();
    private final DatosClinicosDAO datosClinicosDAO = new DatosClinicosDAO();
    
    private List<Pacientes> listPacientes;
    private List<Usuarios> listUsuarios;
    
    Pacientes paciente = new Pacientes();
    DomicilioPacientes domicilioPacientes = new DomicilioPacientes();
    Usuarios userPaciente = new Usuarios();
    TelefonosPacientes telefonosPacientes = new TelefonosPacientes();
    DatosPersonales datosPersonales = new DatosPersonales();
    Contactos contactos = new Contactos();
    DatosClinicos datosClinicos = new DatosClinicos();
    
    private HttpServletRequest servletRequest;
    
    //Acceso
    private String nombreUsuario; //Clave Primaria
    private String clave;
    private String tipoUsuario;
    //Datos 
    private String nss;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String unidadMedica;
    private String noConsultorio;
    private File imagen;
    private byte[] imagenPaciente;
    private String userImageContentType;
    private String userImageFileName;
    //Direccion
    private Long id; //Clave Primaria
    private String calle;
    private String colonia;
    private String delegacion;
    private String entidadFederativa;
    private String codigoPostal;
    //Telefonos
    private String telefonoFijo;
    private String telefonoParticular;
    //Datos personales
    private String estadoCivil;
    private String curp;
    private String sexo;
    private Date fechaNacimiento;
    private String edad;
    private String peso;
    private String altura;
    private String talla;
    private String telCasa;
    private String telCel;
    private String correo;
    private String facebook;
    //Contacto
    private String nombreC;
    private String apellidoPaternoC;
    private String apellidoMaternoC;
    private String parentesco;
    private String celular;
    private String facebookC;
    private String correoC;
    //Datos clínicos
    private boolean usoDrogas;
    private boolean usoAlcohol;
    private boolean fumador;
    //campos json retorno
    private String tituloAlertEditar;
    private String textoAlertEditar;
    private String estatusMensajeEliminar;
    private String estatusMensajeEditar;
    Boolean r1 = false;
    Boolean r2 = false;
    Boolean r3 = false;
    Boolean r4 = false;
    Boolean r5 = false;
    Boolean r6 = false;
    Boolean r7 = false;
    String mensajeError = "";

    public String eliminarPaciente() {
        if (usuarioDAO.deletePaciente(getNombreUsuario())) {
            setEstatusMensajeEliminar("usuarioEncontrado");
            System.err.println("Usuario eliminado--->" + getNombreUsuario());
            return "pantallaModificarEliminarPaciente";
        }
        else {
            mensajeError = "Error al eliminar Paciente";
            setEstatusMensajeEliminar("usuarioNoEncontrado");
        }
        return "pantallaModificarEliminarPaciente";
    }
    
    public String eliminarPacientePorFiltro() {
        if (usuarioDAO.deletePaciente(getNombreUsuario())) {
            setEstatusMensajeEliminar("usuarioEncontrado");
            System.err.println("Usuario eliminado--->" + getNombreUsuario());
            return "pantallaModificarEliminarPaciente";
        }
        else {
            mensajeError = "Error al eliminar Paciente";
            setEstatusMensajeEliminar("usuarioNoEncontrado");
        }
        return "pantallaModificarEliminarPaciente";
    }
    
    public String editarAccesoPaciente() throws ParseException {
        Boolean actualizacionCorrecta = false;
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        //Obtenemos la fecha de registro por que no se va a modificar y la volvemos a setear dentro de Usuario
        Date fecha = userPaciente.getFechaRegistro();
        Date date = fecha;
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);

        userPaciente = new Usuarios(getNombreUsuario(), getTipoUsuario(), getClave(), date);
        if(usuarioDAO.update(userPaciente)) {
          actualizacionCorrecta = true;
         }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        return "pantallaModificarEliminarPaciente";
    }
    
    public String editarDatosPaciente() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //Establecemos los datos para el Paciente
        paciente.setNss(getNss());
        paciente.setApellidoMaterno(getApellidoPaterno());
        paciente.setApellidoPaterno(getApellidoMaterno());
        paciente.setUnidadMedica(getUnidadMedica());
        paciente.setNombre(getNombre());
        paciente.setNoConsultorio(getNoConsultorio());
        //Convertimos la imagen a un arreglo de
        if(getImagen() != null) {
            System.out.println("Camino absoluto    "+getImagen().getAbsolutePath());
            byte[] bFile = new byte[(int) getImagen().length()];
            FileInputStream fileInputStream = new FileInputStream(getImagen());
            fileInputStream.read(bFile);
            fileInputStream.close();
            paciente.setImagen(bFile);
        }
        else{
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileImg = new File(filePath+"imagenesPerfilPaciente/default/default.jpeg");
            byte[] defaultFile = new byte[(int) fileImg.length()];
            FileInputStream imgDefault = new FileInputStream(fileImg);
            imgDefault.read(defaultFile);
            imgDefault.close();
            paciente.setImagen(defaultFile);
        }
        paciente.setUsuarios(userPaciente);
        if(pacienteDAO.update(paciente)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarPaciente";
    }
    
    public String editarDireccionPaciente() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //paciente = pacienteDAO.findById();
                
        //Establecemos los datos de dirección para el Paciente
        domicilioPacientes.setId(getId());
        domicilioPacientes.setCalle(getCalle());
        domicilioPacientes.setColonia(getColonia());
        domicilioPacientes.setDelegacion(getDelegacion());
        domicilioPacientes.setEntidadFederativa(getEntidadFederativa());
        domicilioPacientes.setCodigoPostal(getCodigoPostal());
        domicilioPacientes.setPacientes(paciente);
        
        if(domicilioPacienteDAO.update(domicilioPacientes)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar Paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarPacientes";
    }
    
    public String editarTelefonosPaciente() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //paciente = pacienteDAO.findById();
                
        //Establecemos los telefonos para el Paciente
//        telefonosPacientes.setTelefonoFijo(getTelefonoFijo());
//        telefonosPacientes.setTelefonoParticular(getTelefonoParticular());
        
        if(telefonoPacienteDAO.update(telefonosPacientes)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar Paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarPacientes";
    }
    
    public String editarDatosPersonalesPaciente() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //paciente = pacienteDAO.findById();
                
        //Establecemos los datos personales para el Paciente
        datosPersonales.setEstadoCivil(getEstadoCivil());
        datosPersonales.setCurp(getCurp());
        datosPersonales.setSexo(getSexo());
        datosPersonales.setFechaNacimiento(getFechaNacimiento());
        datosPersonales.setEdad(getEdad());
        datosPersonales.setPeso(getPeso());
        datosPersonales.setAltura(getAltura());
        datosPersonales.setTalla(getTalla());
//        datosPersonales.setTelCasa(getTelCasa());
//        datosPersonales.setTelCel(getTelCel());
        datosPersonales.setCorreo(getCorreo());
        datosPersonales.setFacebook(getFacebook());
        
        if(datosPersonalesDAO.update(datosPersonales)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar Paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarPacientes";
    }
    
    public String editarContactosPaciente() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //paciente = pacienteDAO.findById();
                
        //Establecemos los datos de contacto para el Paciente
//        contactos.setNombreC(getNombreC());
//        contactos.setApellidoPaternoC(getApellidoPaternoC());
//        contactos.setApellidoMaternoC(getApellidoMaternoC());
//        contactos.setParentesco(getParentesco());
//        contactos.setCelular(getCelular());
//        contactos.setFacebookC(getFacebookC());
//        contactos.setCorreoC(getCorreoC());
        
        if(contactoDAO.update(contactos)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar Paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarPacientes";
    }
    
    public String editarDatosClinicosPaciente() throws IOException {
        Boolean actualizacionCorrecta = false;
        
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //paciente = pacienteDAO.findById();
                
        //Establecemos los telefonos para el Paciente
        datosClinicos.setUsoDrogas(isUsoDrogas());
        datosClinicos.setUsoAlcohol(isUsoAlcohol());
        datosClinicos.setFumador(isFumador());
        
        if(datosClinicosDAO.update(datosClinicos)) {
            actualizacionCorrecta = true;
        }
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar los datos del Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar Paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }
        
        return "pantallaModificarEliminarPacientes";
    }
    
    public String editarPacientePorFiltro() throws ParseException, FileNotFoundException, IOException {
        Boolean actualizacionCorrecta = false;
        userPaciente = usuarioDAO.findById(getNombreUsuario());
        
        //Obtenemos la fecha de registro por que no se va a modificar y la volvemos a setear dentro de Usuario
        Date fecha = userPaciente.getFechaRegistro();
        Date date = fecha;
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);
        
        //Establecemos los datos de acceso para el Aministrador
        userPaciente = new Usuarios(getNombreUsuario(), getTipoUsuario(), getClave(), date);
        
        //Establecemos los datos personales para el Paciente
        paciente.setNss(getNss());
        paciente.setApellidoMaterno(getApellidoPaterno());
        paciente.setApellidoPaterno(getApellidoMaterno());
        paciente.setUnidadMedica(getUnidadMedica());
        paciente.setNombre(getNombre());
        paciente.setNoConsultorio(getNoConsultorio());
        //Convertimos la imagen a un arreglo de
        if(getImagen() != null) {
            System.out.println("Camino absoluto    "+getImagen().getAbsolutePath());
            byte[] bFile = new byte[(int) getImagen().length()];
            FileInputStream fileInputStream = new FileInputStream(getImagen());
            fileInputStream.read(bFile);
            fileInputStream.close();
            paciente.setImagen(bFile);
        }
        else{
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileImg = new File(filePath+"imagenesPerfilPaciente/default/default.jpeg");
            byte[] defaultFile = new byte[(int) fileImg.length()];
            FileInputStream imgDefault = new FileInputStream(fileImg);
            imgDefault.read(defaultFile);
            imgDefault.close();
            paciente.setImagen(defaultFile);
        }
        //Guardamos los datos de acceso del Paciente
        r1 = usuarioDAO.update(userPaciente);
        
        //Establecemos la clave foranea del Paciente
        paciente.setUsuarios(userPaciente);
        
        //Establecemos los datos de dirección para el Paciente
        domicilioPacientes.setId(getId());
        domicilioPacientes.setCalle(getCalle());
        domicilioPacientes.setColonia(getColonia());
        domicilioPacientes.setDelegacion(getDelegacion());
        domicilioPacientes.setEntidadFederativa(getEntidadFederativa());
        domicilioPacientes.setCodigoPostal(getCodigoPostal());
        domicilioPacientes.setPacientes(paciente);
        
        //Guardamos los datos personales para el Paciente
        r2 = pacienteDAO.update(paciente);
        
        //Establecemos la clave foranea del domicilio del Paciente
        domicilioPacientes.setPacientes(paciente);
        
        //Guardamos los datos del domicilio para el Paciente
        r3 = domicilioPacienteDAO.update(domicilioPacientes);
        
        //Establecemos los telefonos para el Paciente
//        telefonosPacientes.setTelefonoFijo(getTelefonoFijo());
//        telefonosPacientes.setTelefonoParticular(getTelefonoParticular());
        
        //Establecemos la clave foranea de telefonos del Paciente
        telefonosPacientes.setPacientes(paciente);
        
        //Guardamos los datos de telefonos para el Paciente
        r4 = telefonoPacienteDAO.update(telefonosPacientes);
        
        //Establecemos los datos personales para el Paciente
        datosPersonales.setEstadoCivil(getEstadoCivil());
        datosPersonales.setCurp(getCurp());
        datosPersonales.setSexo(getSexo());
        datosPersonales.setFechaNacimiento(getFechaNacimiento());
        datosPersonales.setEdad(getEdad());
        datosPersonales.setPeso(getPeso());
        datosPersonales.setAltura(getAltura());
        datosPersonales.setTalla(getTalla());
//        datosPersonales.setTelCasa(getTelCasa());
//        datosPersonales.setTelCel(getTelCel());
        datosPersonales.setCorreo(getCorreo());
        datosPersonales.setFacebook(getFacebook());
        
        //Establecemos la clave foranea de datos personales del Paciente
        datosPersonales.setPacientes(paciente);
        
        //Guardamos los datos de datos personales para el Paciente
        r5 = datosPersonalesDAO.update(datosPersonales);
        
//        //Establecemos los datos de contacto para el Paciente
//        contactos.setNombreC(getNombreC());
//        contactos.setApellidoPaternoC(getApellidoPaternoC());
//        contactos.setApellidoMaternoC(getApellidoMaternoC());
//        contactos.setParentesco(getParentesco());
//        contactos.setCelular(getCelular());
//        contactos.setFacebookC(getFacebookC());
//        contactos.setCorreoC(getCorreoC());
        
        //Establecemos la clave foranea de contactos del Paciente
        contactos.setPacientes(paciente);
        
        //Guardamos los datos de contactos para el Paciente
        r6 = contactoDAO.update(contactos);
        
        //Establecemos los datos clinicos para el Paciente
        datosClinicos.setUsoDrogas(isUsoDrogas());
        datosClinicos.setUsoAlcohol(isUsoAlcohol());
        datosClinicos.setFumador(isFumador());
        
        //Establecemos la clave foranea de datos clinicos del Paciente
        datosClinicos.setPacientes(paciente);
        
        //Guardamos los datos de datos clinicos para el Paciente
        r7 = datosClinicosDAO.update(datosClinicos);
        
        if(r1 && r2 && r3 && r4 && r5 && r6 && r7)
            actualizacionCorrecta = true;
        else {
            actualizacionCorrecta = false;
            mensajeError = "Error al actualizar Paciente";
        }
        
        if (actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Paciente Editado");
            session.put("textoAlertEditar", "Los datos del Paciente fueron actualizados correctamente.");
            session.put("estatusMensajeEditar", "success");

        } else if (!actualizacionCorrecta) {
            session.put("tituloAlertEditar", "Error al actualizar Paciente.");
            session.put("textoAlertEditar", mensajeError);
            session.put("estatusMensajeEditar", "error");
        }

        return "pantallaModificarEliminarPaciente";
    }

    public String recuperarEstatusEditar() {
        setTituloAlertEditar("");
        setTextoAlertEditar("");
        setEstatusMensajeEditar("");
        
        if (session.get("estatusMensajeEditar") != null) {
            setTituloAlertEditar(session.get("tituloAlertEditar").toString());
            setTextoAlertEditar(session.get("textoAlertEditar").toString());
            setEstatusMensajeEditar(session.get("estatusMensajeEditar").toString());
        }

        session.remove("estatusMensajeEditar");
        session.remove("tituloAlertEditar");
        session.remove("estatusMensajeEditar");
        session.put("estatusMensajeEditar", null);

        return "success";
    }
    
    public String buscarDatosPaciente() throws IOException {
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                setNss(paciente.getNss());
                setNombre(paciente.getNombre());
                setApellidoPaterno(paciente.getApellidoPaterno());
                setApellidoMaterno(paciente.getApellidoMaterno());
                setUnidadMedica(paciente.getUnidadMedica());
                setNoConsultorio(paciente.getNoConsultorio());
                setImagenPaciente(paciente.getImagen());
                String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                System.out.println(filePath);
                FileOutputStream image = new FileOutputStream(filePath+"imagenesPerfilPaciente/"+getNombreUsuario()+".jpeg");
                image.write(getImagenPaciente());
                setNombreUsuario(userPaciente.getNombreUsuario());
                System.out.println(getNombre());
                System.out.println(getApellidoPaterno());
                System.out.println(getApellidoMaterno());
                System.out.println(getUnidadMedica());
                System.out.println(getNoConsultorio());
                System.out.println(getNombreUsuario());
                image.close();
            }
        }
        return "success";
    }
    
    public String buscarDatosDireccionPaciente() {
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set domPaciente = paciente.getDomicilioPacienteses();
                for (Iterator iterator3 = domPaciente.iterator(); iterator3.hasNext();) {
                    domicilioPacientes = (DomicilioPacientes) iterator3.next();
                    setCalle(domicilioPacientes.getCalle());
                    setColonia(domicilioPacientes.getColonia());
                    setDelegacion(domicilioPacientes.getDelegacion());
                    setEntidadFederativa(domicilioPacientes.getEntidadFederativa());
                    setCodigoPostal(domicilioPacientes.getCodigoPostal());
                    setId(domicilioPacientes.getId());
                    setNombreUsuario(userPaciente.getNombreUsuario());
                    System.out.println(getId());
                    System.out.println(getCalle());
                    System.out.println(getColonia());
                    System.out.println(getDelegacion());
                    System.out.println(getEntidadFederativa());
                    System.out.println(getCodigoPostal());
                    System.out.println(getNombreUsuario());
                }
            }
        }
        return "success";
    }
    
    public String buscarTelefonosPaciente() {
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set telPaciente = paciente.getTelefonosPacienteses();
                for (Iterator iterator3 = telPaciente.iterator(); iterator3.hasNext();) {
                    telefonosPacientes = (TelefonosPacientes) iterator3.next();
//                    setTelefonoFijo(telefonosPacientes.getTelefonoFijo());
//                    setTelefonoParticular(telefonosPacientes.getTelefonoParticular());
                    setNombreUsuario(userPaciente.getNombreUsuario());
                    System.out.println(getTelefonoFijo());
                    System.out.println(getTelefonoParticular());
                    System.out.println(getNombreUsuario());
                }
            }
        }
        return "success";
    }
    
    public String buscarDatosPersonalesPaciente() {
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set datPerPaciente = paciente.getDatosPersonaleses();
                for (Iterator iterator3 = datPerPaciente.iterator(); iterator3.hasNext();) {
                    datosPersonales = (DatosPersonales) iterator3.next();
                    setEstadoCivil(datosPersonales.getEstadoCivil());
                    setCurp(datosPersonales.getCurp());
                    setSexo(datosPersonales.getSexo());
                    setFechaNacimiento(datosPersonales.getFechaNacimiento());
                    setEdad(datosPersonales.getEdad());
                    setPeso(datosPersonales.getPeso());
                    setAltura(datosPersonales.getAltura());
                    setTalla(datosPersonales.getTalla());
//                    setTelCasa(datosPersonales.getTelCasa());
//                    setTelCel(datosPersonales.getTelCel());
                    setCorreo(datosPersonales.getCorreo());
                    setFacebook(datosPersonales.getFacebook());
                    setNombreUsuario(userPaciente.getNombreUsuario());
                    System.out.println(getEstadoCivil());
                    System.out.println(getCurp());
                    System.out.println(getSexo());
                    System.out.println(getFechaNacimiento());
                    System.out.println(getEdad());
                    System.out.println(getPeso());
                    System.out.println(getAltura());
                    System.out.println(getTalla());
                    System.out.println(getTelCasa());
                    System.out.println(getTelCel());
                    System.out.println(getCorreo());
                    System.out.println(getFacebook());
                    System.out.println(getNombreUsuario());
                }
            }
        }
        return "success";
    }
    
    public String buscarContactosPaciente() {
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set contacPaciente = paciente.getContactoses();
                for (Iterator iterator3 = contacPaciente.iterator(); iterator3.hasNext();) {
                    contactos = (Contactos) iterator3.next();
//                    setNombreC(contactos.getNombreC());
//                    setApellidoPaternoC(contactos.getApellidoPaternoC());
//                    setApellidoMaternoC(contactos.getApellidoMaternoC());
//                    setParentesco(contactos.getParentesco());
//                    setCelular(contactos.getCelular());
//                    setFacebookC(contactos.getFacebookC());
//                    setCorreoC(contactos.getCorreoC());
                    setNombreUsuario(userPaciente.getNombreUsuario());
                    System.out.println(getNombreC());
                    System.out.println(getApellidoPaternoC());
                    System.out.println(getApellidoMaternoC());
                    System.out.println(getParentesco());
                    System.out.println(getCelular());
                    System.out.println(getFacebookC());
                    System.out.println(getCorreoC());
                    System.out.println(getNombreUsuario());
                }
            }
        }
        return "success";
    }
    
    public String buscarDatosClinicosPaciente() {
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    setUsoDrogas(datosClinicos.isUsoDrogas());
                    setUsoAlcohol(datosClinicos.isUsoAlcohol());
                    setFumador(datosClinicos.isFumador());
                    setNombreUsuario(userPaciente.getNombreUsuario());
                    System.out.println(isUsoDrogas());
                    System.out.println(isUsoAlcohol());
                    System.out.println(isFumador());
                    System.out.println(getNombreUsuario());
                }
            }
        }
        return "success";
    }
    
    public String buscarDatosPorFiltro() throws FileNotFoundException, IOException {
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(getNombreUsuario());
        
        if (usuarioResultado == null) {
            setEstatusMensajeEliminar("usuarioNoEncontrado");
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(getNombreUsuario())) {
            setEstatusMensajeEliminar("usuarioEncontrado");
            listUsuarios = usuarioDAO.listarById(getNombreUsuario());
            for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
                userPaciente = (Usuarios) iterator1.next();
                Set pacientes = userPaciente.getPacienteses();
                for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                    paciente = (Pacientes) iterator2.next(); 
                    Set domPaciente = paciente.getDomicilioPacienteses();
                    for (Iterator iterator3 = domPaciente.iterator(); iterator3.hasNext();) {
                        domicilioPacientes = (DomicilioPacientes) iterator3.next();
                        setNombreUsuario(userPaciente.getNombreUsuario());
                        setClave(userPaciente.getClave());
                        setNss(paciente.getNss());
                        setNombre(paciente.getNombre());
                        setApellidoPaterno(paciente.getApellidoPaterno());
                        setApellidoMaterno(paciente.getApellidoMaterno());
                        setUnidadMedica(paciente.getUnidadMedica());
                        setNoConsultorio(paciente.getNoConsultorio());
                        setImagenPaciente(paciente.getImagen());
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                        System.out.println(filePath);
                        FileOutputStream image = new FileOutputStream(filePath+"imagenesPerfilPaciente/"+getNombreUsuario()+".jpeg");
                        image.write(getImagenPaciente());
                        setCalle(domicilioPacientes.getCalle());
                        setColonia(domicilioPacientes.getColonia());
                        setDelegacion(domicilioPacientes.getDelegacion());
                        setEntidadFederativa(domicilioPacientes.getEntidadFederativa());
                        setCodigoPostal(domicilioPacientes.getCodigoPostal());
                        setId(domicilioPacientes.getId());
//                        setTelefonoFijo(telefonosPacientes.getTelefonoFijo());
//                        setTelefonoParticular(telefonosPacientes.getTelefonoParticular());
                        setEstadoCivil(datosPersonales.getEstadoCivil());
                        setCurp(datosPersonales.getCurp());
                        setSexo(datosPersonales.getSexo());
                        setFechaNacimiento(datosPersonales.getFechaNacimiento());
                        setEdad(datosPersonales.getEdad());
                        setPeso(datosPersonales.getPeso());
                        setAltura(datosPersonales.getAltura());
                        setTalla(datosPersonales.getTalla());
//                        setTelCasa(datosPersonales.getTelCasa());
//                        setTelCel(datosPersonales.getTelCel());
                        setCorreo(datosPersonales.getCorreo());
                        setFacebook(datosPersonales.getFacebook());
//                        setNombreC(contactos.getNombreC());
//                        setApellidoPaternoC(contactos.getApellidoPaternoC());
//                        setApellidoMaternoC(contactos.getApellidoMaternoC());
//                        setParentesco(contactos.getParentesco());
//                        setCelular(contactos.getCelular());
//                        setFacebookC(contactos.getFacebookC());
//                        setCorreoC(contactos.getCorreoC());
                        setUsoDrogas(datosClinicos.isUsoDrogas());
                        setUsoAlcohol(datosClinicos.isUsoAlcohol());
                        setFumador(datosClinicos.isFumador());
                        
                        image.close();
                    }
                }
            }
        } 
        else {
            setEstatusMensajeEliminar("usuarioNoEncontrado");
        }
        return "success";
    }

    public String validarNombreUsuarioEliminar() {
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(getNombreUsuario());

        if (usuarioResultado == null) {
            setEstatusMensajeEliminar("usuarioNoEncontrado");
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(getNombreUsuario())) {
            setEstatusMensajeEliminar("usuarioEncontrado");
        } else {
            setEstatusMensajeEliminar("usuarioNoEncontrado");
        }

        return SUCCESS;
    }
    
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        servletRequest =hsr;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the nss
     */
    public String getNss() {
        return nss;
    }

    /**
     * @param nss the nss to set
     */
    public void setNss(String nss) {
        this.nss = nss;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the unidadMedica
     */
    public String getUnidadMedica() {
        return unidadMedica;
    }

    /**
     * @param unidadMedica the unidadMedica to set
     */
    public void setUnidadMedica(String unidadMedica) {
        this.unidadMedica = unidadMedica;
    }

    /**
     * @return the noConsultorio
     */
    public String getNoConsultorio() {
        return noConsultorio;
    }

    /**
     * @param noConsultorio the noConsultorio to set
     */
    public void setNoConsultorio(String noConsultorio) {
        this.noConsultorio = noConsultorio;
    }

    /**
     * @return the imagen
     */
    public File getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the imagenPaciente
     */
    public byte[] getImagenPaciente() {
        return imagenPaciente;
    }

    /**
     * @param imagenPaciente the imagenPaciente to set
     */
    public void setImagenPaciente(byte[] imagenPaciente) {
        this.imagenPaciente = imagenPaciente;
    }

    /**
     * @return the userImageContentType
     */
    public String getUserImageContentType() {
        return userImageContentType;
    }

    /**
     * @param userImageContentType the userImageContentType to set
     */
    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    /**
     * @return the userImageFileName
     */
    public String getUserImageFileName() {
        return userImageFileName;
    }

    /**
     * @param userImageFileName the userImageFileName to set
     */
    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the delegacion
     */
    public String getDelegacion() {
        return delegacion;
    }

    /**
     * @param delegacion the delegacion to set
     */
    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    /**
     * @return the entidadFederativa
     */
    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    /**
     * @param entidadFederativa the entidadFederativa to set
     */
    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the telefonoFijo
     */
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * @param telefonoFijo the telefonoFijo to set
     */
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    /**
     * @return the telefonoParticular
     */
    public String getTelefonoParticular() {
        return telefonoParticular;
    }

    /**
     * @param telefonoParticular the telefonoParticular to set
     */
    public void setTelefonoParticular(String telefonoParticular) {
        this.telefonoParticular = telefonoParticular;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the edad
     */
    public String getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }

    /**
     * @return the peso
     */
    public String getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }

    /**
     * @return the altura
     */
    public String getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(String altura) {
        this.altura = altura;
    }

    /**
     * @return the talla
     */
    public String getTalla() {
        return talla;
    }

    /**
     * @param talla the talla to set
     */
    public void setTalla(String talla) {
        this.talla = talla;
    }

    /**
     * @return the telCasa
     */
    public String getTelCasa() {
        return telCasa;
    }

    /**
     * @param telCasa the telCasa to set
     */
    public void setTelCasa(String telCasa) {
        this.telCasa = telCasa;
    }

    /**
     * @return the telCel
     */
    public String getTelCel() {
        return telCel;
    }

    /**
     * @param telCel the telCel to set
     */
    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the nombreC
     */
    public String getNombreC() {
        return nombreC;
    }

    /**
     * @param nombreC the nombreC to set
     */
    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    /**
     * @return the apellidoPaternoC
     */
    public String getApellidoPaternoC() {
        return apellidoPaternoC;
    }

    /**
     * @param apellidoPaternoC the apellidoPaternoC to set
     */
    public void setApellidoPaternoC(String apellidoPaternoC) {
        this.apellidoPaternoC = apellidoPaternoC;
    }

    /**
     * @return the apellidoMaternoC
     */
    public String getApellidoMaternoC() {
        return apellidoMaternoC;
    }

    /**
     * @param apellidoMaternoC the apellidoMaternoC to set
     */
    public void setApellidoMaternoC(String apellidoMaternoC) {
        this.apellidoMaternoC = apellidoMaternoC;
    }

    /**
     * @return the parentesco
     */
    public String getParentesco() {
        return parentesco;
    }

    /**
     * @param parentesco the parentesco to set
     */
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the facebookC
     */
    public String getFacebookC() {
        return facebookC;
    }

    /**
     * @param facebookC the facebookC to set
     */
    public void setFacebookC(String facebookC) {
        this.facebookC = facebookC;
    }

    /**
     * @return the correoC
     */
    public String getCorreoC() {
        return correoC;
    }

    /**
     * @param correoC the correoC to set
     */
    public void setCorreoC(String correoC) {
        this.correoC = correoC;
    }

    /**
     * @return the usoDrogas
     */
    public boolean isUsoDrogas() {
        return usoDrogas;
    }

    /**
     * @param usoDrogas the usoDrogas to set
     */
    public void setUsoDrogas(boolean usoDrogas) {
        this.usoDrogas = usoDrogas;
    }

    /**
     * @return the usoAlcohol
     */
    public boolean isUsoAlcohol() {
        return usoAlcohol;
    }

    /**
     * @param usoAlcohol the usoAlcohol to set
     */
    public void setUsoAlcohol(boolean usoAlcohol) {
        this.usoAlcohol = usoAlcohol;
    }

    /**
     * @return the fumador
     */
    public boolean isFumador() {
        return fumador;
    }

    /**
     * @param fumador the fumador to set
     */
    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    /**
     * @return the tituloAlertEditar
     */
    public String getTituloAlertEditar() {
        return tituloAlertEditar;
    }

    /**
     * @param tituloAlertEditar the tituloAlertEditar to set
     */
    public void setTituloAlertEditar(String tituloAlertEditar) {
        this.tituloAlertEditar = tituloAlertEditar;
    }

    /**
     * @return the textoAlertEditar
     */
    public String getTextoAlertEditar() {
        return textoAlertEditar;
    }

    /**
     * @param textoAlertEditar the textoAlertEditar to set
     */
    public void setTextoAlertEditar(String textoAlertEditar) {
        this.textoAlertEditar = textoAlertEditar;
    }

    /**
     * @return the estatusMensajeEliminar
     */
    public String getEstatusMensajeEliminar() {
        return estatusMensajeEliminar;
    }

    /**
     * @param estatusMensajeEliminar the estatusMensajeEliminar to set
     */
    public void setEstatusMensajeEliminar(String estatusMensajeEliminar) {
        this.estatusMensajeEliminar = estatusMensajeEliminar;
    }

    /**
     * @return the estatusMensajeEditar
     */
    public String getEstatusMensajeEditar() {
        return estatusMensajeEditar;
    }

    /**
     * @param estatusMensajeEditar the estatusMensajeEditar to set
     */
    public void setEstatusMensajeEditar(String estatusMensajeEditar) {
        this.estatusMensajeEditar = estatusMensajeEditar;
    }

   
    
}
