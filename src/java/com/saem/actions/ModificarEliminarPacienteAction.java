/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.AlergiaDAO;
import com.hibernate.dao.ContactoDAO;
import com.hibernate.dao.DatosClinicosDAO;
import com.hibernate.dao.DatosPersonalesDAO;
import com.hibernate.dao.DiscapacidadDAO;
import com.hibernate.dao.DomicilioPacienteDAO;
import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.TelefonoPacienteDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.dao.PacienteDAO;
import com.hibernate.model.Alergias;
import com.hibernate.model.Contactos;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.Discapacidades;
import com.hibernate.model.DomicilioPacientes;
import com.hibernate.model.Hospitales;
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
    private final HospitalDAO hospitalDAO = new HospitalDAO();
    private final DiscapacidadDAO discapacidadDAO = new DiscapacidadDAO();
    private final AlergiaDAO alergiaDAO = new AlergiaDAO();
    
    
    private List<Pacientes> listPacientes;
    private List<Usuarios> listUsuarios;
    private List<Hospitales> listHospitales;
    
    Pacientes paciente = new Pacientes();
    DomicilioPacientes domicilioPacientes = new DomicilioPacientes();
    Usuarios userPaciente = new Usuarios();
    TelefonosPacientes telefonosPacientes = new TelefonosPacientes();
    DatosPersonales datosPersonales = new DatosPersonales();
    Contactos contactos = new Contactos();
    DatosClinicos datosClinicos = new DatosClinicos();
    Alergias alergia = new Alergias();
    Discapacidades discapacidad = new Discapacidades();
    Hospitales hospital = new Hospitales();
    
    private HttpServletRequest servletRequest;
    
    String codigoHospital;
    Long idDomicilioPaciente;
    Long idDatosPersonalesPaciente;
    Long noHistorial;
    ArrayList<String> discapacidades = new ArrayList<String>();
    ArrayList<String> alergias = new ArrayList<String>();
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
    private boolean drogas;
    private boolean alcohol;
    private boolean fuma;;
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
        listUsuarios = usuarioDAO.listarById(nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                nss = paciente.getNss();
                nombre = paciente.getNombre();
                apellidoPaterno = paciente.getApellidoPaterno();
                apellidoMaterno = paciente.getApellidoMaterno();
                unidadMedica = paciente.getUnidadMedica();
                noConsultorio = paciente.getNoConsultorio();
                imagenPaciente = paciente.getImagen();
                String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                System.out.println(filePath);
                FileOutputStream image = new FileOutputStream(filePath+"imagenesPerfilPaciente/"+nombreUsuario+".jpeg");
                image.write(imagenPaciente);
                nombreUsuario = userPaciente.getNombreUsuario();                
                System.out.println(nss);
                System.out.println(nombre);
                System.out.println(apellidoPaterno);
                System.out.println(apellidoMaterno);
                System.out.println(unidadMedica);
                System.out.println(noConsultorio);
                System.out.println(nombreUsuario);
                codigoHospital = pacienteDAO.obtenerCogigoHospital(nss);
                System.out.println(codigoHospital);
                
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
                    idDomicilioPaciente = domicilioPacientes.getId();
                    calle = domicilioPacientes.getCalle();
                    colonia = domicilioPacientes.getColonia();
                    delegacion = domicilioPacientes.getDelegacion();
                    entidadFederativa = domicilioPacientes.getEntidadFederativa();
                    codigoPostal = domicilioPacientes.getCodigoPostal();
                    nss = paciente.getNss();
                    nombreUsuario = userPaciente.getNombreUsuario();
                    System.out.println(idDomicilioPaciente);
                    System.out.println(calle);
                    System.out.println(colonia);
                    System.out.println(delegacion);
                    System.out.println(entidadFederativa);
                    System.out.println(codigoPostal);
                    System.out.println(nss);
                    System.out.println(nombreUsuario);
                }
            }
        }
        return "success";
    }
    
    public String buscarTelefonosPaciente() {
        System.out.println("--->Entro a telefonos pacientes");
        String html = "";
        TelefonoPacienteDAO telPacienteDAO = new TelefonoPacienteDAO();
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
                    idDatosPersonalesPaciente = datosPersonales.getId();
                    estadoCivil = datosPersonales.getEstadoCivil();
                    curp = datosPersonales.getCurp();
                    sexo = datosPersonales.getSexo();
                    fechaNacimiento = datosPersonales.getFechaNacimiento();
                    edad = datosPersonales.getEdad();
                    peso = datosPersonales.getPeso();
                    altura = datosPersonales.getAltura();
                    talla = datosPersonales.getTalla();
                    correo = datosPersonales.getCorreo();
                    facebook = datosPersonales.getFacebook();
                    nombreUsuario = userPaciente.getNombreUsuario();
                    nss = paciente.getNss();
                    System.out.println(idDatosPersonalesPaciente);
                    System.out.println(estadoCivil);
                    System.out.println(curp);
                    System.out.println(sexo);
                    System.out.println(fechaNacimiento);
                    System.out.println(edad);
                    System.out.println(peso);
                    System.out.println(altura);
                    System.out.println(talla);
                    System.out.println(correo);
                    System.out.println(facebook);
                    System.out.println(nss);
                    System.out.println(nombreUsuario);
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
        ArrayList<String> nombreDiscapacidad = new ArrayList<String>();
        ArrayList<String> nombreAlergia = new ArrayList<String>();
        listUsuarios = usuarioDAO.listarById(getNombreUsuario());
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next(); 
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    noHistorial = datosClinicos.getNoHistorial();
                    drogas = datosClinicos.isUsoDrogas();
                    alcohol = datosClinicos.isUsoAlcohol();
                    fuma = datosClinicos.isFumador();
                    nombreUsuario = userPaciente.getNombreUsuario();
                    nss = paciente.getNss();
                    System.out.println(noHistorial);
                    System.out.println(drogas);
                    System.out.println(alcohol);
                    System.out.println(fuma);
                    System.out.println(nombreUsuario);
                    System.out.println(nss);
                    
                    Set datClinicosDiscapacidades = datosClinicos.getDiscapacidadeses();
                    for (Iterator iterator4 = datClinicosDiscapacidades.iterator(); iterator4.hasNext();) {
                        discapacidad = (Discapacidades) iterator4.next();
                        nombreDiscapacidad.add(discapacidad.getTipo() + ":" + discapacidad.getId());                      
                    }
                    discapacidades = nombreDiscapacidad;
                    System.out.println(discapacidades);
                    Set datClinicosAlergias = datosClinicos.getAlergiases();
                    for (Iterator iterator5 = datClinicosAlergias.iterator(); iterator5.hasNext();) {
                        alergia = (Alergias) iterator5.next();
                        nombreAlergia.add(alergia.getTipoAlergia()+";"+alergia.getEspecificacion()+":"+alergia.getId());                      
                    }
                    
                    alergias = nombreAlergia;
                    System.out.println(alergias);
                    
                    
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

    public String getCodigoHospital() {
        return codigoHospital;
    }

    public void setCodigoHospital(String codigoHospital) {
        this.codigoHospital = codigoHospital;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
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

    public String getUnidadMedica() {
        return unidadMedica;
    }

    public void setUnidadMedica(String unidadMedica) {
        this.unidadMedica = unidadMedica;
    }

    public String getNoConsultorio() {
        return noConsultorio;
    }

    public void setNoConsultorio(String noConsultorio) {
        this.noConsultorio = noConsultorio;
    }

    public byte[] getImagenPaciente() {
        return imagenPaciente;
    }

    public void setImagenPaciente(byte[] imagenPaciente) {
        this.imagenPaciente = imagenPaciente;
    }

    public Long getIdDomicilioPaciente() {
        return idDomicilioPaciente;
    }

    public void setIdDomicilioPaciente(Long idDomicilioPaciente) {
        this.idDomicilioPaciente = idDomicilioPaciente;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Long getIdDatosPersonalesPaciente() {
        return idDatosPersonalesPaciente;
    }

    public void setIdDatosPersonalesPaciente(Long idDatosPersonalesPaciente) {
        this.idDatosPersonalesPaciente = idDatosPersonalesPaciente;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public void setDrogas(boolean drogas) {
        this.drogas = drogas;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isFuma() {
        return fuma;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public ArrayList<String> getDiscapacidades() {
        return discapacidades;
    }

    public void setDiscapacidades(ArrayList<String> discapacidades) {
        this.discapacidades = discapacidades;
    }  

    public ArrayList<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(ArrayList<String> alergias) {
        this.alergias = alergias;
    }
    
    
    
}
