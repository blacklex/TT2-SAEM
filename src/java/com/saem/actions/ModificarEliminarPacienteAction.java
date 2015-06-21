/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.AlergiaDAO;
import com.hibernate.dao.CirugiaDAO;
import com.hibernate.dao.ContactoDAO;
import com.hibernate.dao.DatosClinicosDAO;
import com.hibernate.dao.DatosPersonalesDAO;
import com.hibernate.dao.DiscapacidadDAO;
import com.hibernate.dao.DomicilioPacienteDAO;
import com.hibernate.dao.EnfermedadCronicaDAO;
import com.hibernate.dao.EspecialidadDAO;
import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.MedicacionDAO;
import com.hibernate.dao.TelefonoPacienteDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.dao.PacienteDAO;
import com.hibernate.model.Alergias;
import com.hibernate.model.Cirugias;
import com.hibernate.model.Contactos;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.Discapacidades;
import com.hibernate.model.DomicilioPacientes;
import com.hibernate.model.EnfermedadesCronicas;
import com.hibernate.model.Especialidades;
import com.hibernate.model.Hospitales;
import com.hibernate.model.Medicacion;
import com.hibernate.model.TelefonosPacientes;
import com.hibernate.model.Usuarios;
import com.hibernate.model.Pacientes;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.persistencia.owl.OWLConsultas;
import com.saem.foaf.InsercionFOAF;
import com.saem.foaf.ModificarEliminarFOAF;
import com.saem.foaf.PersonaFOAF;
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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author gabriela
 */
public class ModificarEliminarPacienteAction extends ActionSupport implements SessionAware, ServletRequestAware {

    private Map<String, Object> session = null;
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final HospitalDAO hospitalDAO = new HospitalDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final DomicilioPacienteDAO domicilioPacienteDAO = new DomicilioPacienteDAO();
    private final TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private final DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
    private final ContactoDAO contactoDAO = new ContactoDAO();
    private final DatosClinicosDAO datosClinicosDAO = new DatosClinicosDAO();
    private final DiscapacidadDAO discapacidadDAO = new DiscapacidadDAO();
    private final AlergiaDAO alergiaDAO = new AlergiaDAO();
    private final CirugiaDAO cirugiaDAO = new CirugiaDAO();
    private final MedicacionDAO medicacionDAO = new MedicacionDAO();
    private final EnfermedadCronicaDAO enfermedadCronicaDAO = new EnfermedadCronicaDAO();

    HttpServletRequest request = ServletActionContext.getRequest();

    private List<Pacientes> listPacientes;
    private List<Usuarios> listUsuarios;
    private List<Hospitales> listHospitales;

    Usuarios userPaciente = new Usuarios();
    Pacientes paciente = new Pacientes();
    Hospitales hospital = new Hospitales();
    DomicilioPacientes domicilioPacientes = new DomicilioPacientes();
    TelefonosPacientes telefonosPacientes = new TelefonosPacientes();
    DatosPersonales datosPersonales = new DatosPersonales();
    Contactos contactos = new Contactos();
    DatosClinicos datosClinicos = new DatosClinicos();
    Alergias alergia = new Alergias();
    Cirugias cirugia = new Cirugias();
    Discapacidades discapacidad = new Discapacidades();
    Medicacion medicacion = new Medicacion();
    EnfermedadesCronicas enfermedadCronica = new EnfermedadesCronicas();

    private HttpServletRequest servletRequest;

    private String codigoHospital;
    private Long noHistorial;
    private ArrayList<String> discapacidades = new ArrayList<>();
    private ArrayList<String> alergias = new ArrayList<>();
    private ArrayList<String> cirugias = new ArrayList<>();

    private String fechaNacimientoFormato;
    private String contactosDelPaciente;
    private String medicamentos;
    private String enfermedadesCronicas;

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
    //Direccion
    Long idDomicilioPaciente;
    private String calle;
    private String colonia;
    private String delegacion;
    private String entidadFederativa;
    private String codigoPostal;
    //Telefonos
    private String telefonoPaciente;
    private Long idTelefonoPaciente;
    private String telefonosDelPaciente;

    //Datos personales
    Long idDatosPersonalesPaciente;
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
    //Datos clínicos
    private String drogas;
    private String alcohol;
    private String fuma;
    //campos json retorno
    private String tituloAlertEditar;
    private String textoAlertEditar;
    private String estatusMensajeEliminar;
    private String estatusMensajeEditar;
    private String mensajeError = "";

    private String htmlEnfermedades;
    private String especialidadCombo;

    public String eliminarPaciente() {
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/foaf.rdf";
        //InsercionFOAF insercionFoaf = new InsercionFOAF(ONTOLOGIA);

        ModificarEliminarFOAF eliminarPersona = new ModificarEliminarFOAF(nombreUsuario, ONTOLOGIA);
        if (usuarioDAO.deletePaciente(nombreUsuario)) {
            if (eliminarPersona.eliminarPersona()) {
                System.out.println("Se elimino a la persona");
            } else {
                System.out.println("No se elimino a la persona");
            }
            estatusMensajeEliminar = "usuarioEncontrado";
            System.err.println("Usuario eliminado--->" + nombreUsuario);
            return "pantallaModificarEliminarPaciente";
        } else {
            mensajeError = "Error al eliminar Administrador";
            estatusMensajeEliminar = "usuarioNoEncontrado";
        }
        return "pantallaModificarEliminarPaciente";
    }

    public String editarAccesoPaciente() throws ParseException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;
        userPaciente = usuarioDAO.findById(s, nombreUsuario);
        //Obtenemos la fecha de registro por que no se va a modificar y la volvemos a setear dentro de Usuario
        Date fecha = userPaciente.getFechaRegistro();
        Date date = fecha;
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);

        userPaciente = new Usuarios(nombreUsuario, "Paciente", clave, date);
        if (usuarioDAO.update(userPaciente)) {
            actualizacionCorrecta = true;
        } else {
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String editarDatosIdentificacionPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;

        userPaciente = usuarioDAO.findById(s, nombreUsuario);
        hospital = hospitalDAO.findById(s, codigoHospital);

        //Establecemos los datos para el Paciente
        paciente.setNss(nss);
        paciente.setApellidoMaterno(apellidoMaterno);
        paciente.setApellidoPaterno(apellidoMaterno);
        paciente.setUnidadMedica(unidadMedica);
        paciente.setNombre(nombre);
        paciente.setNoConsultorio(noConsultorio);
        //Convertimos la imagen a un arreglo de
        if (imagen != null) {
            System.out.println("Camino absoluto    " + imagen.getAbsolutePath());
            byte[] bFile = new byte[(int) imagen.length()];
            FileInputStream fileInputStream = new FileInputStream(imagen);
            fileInputStream.read(bFile);
            fileInputStream.close();
            paciente.setImagen(bFile);
        } else {
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            File fileImg = new File(filePath + "imagenesPerfilPaciente/default/default.jpeg");
            byte[] defaultFile = new byte[(int) fileImg.length()];
            FileInputStream imgDefault = new FileInputStream(fileImg);
            imgDefault.read(defaultFile);
            imgDefault.close();
            paciente.setImagen(defaultFile);
        }
        paciente.setUsuarios(userPaciente);
        paciente.setHospitales(hospital);
        if (pacienteDAO.update(paciente)) {
            actualizacionCorrecta = true;
        } else {
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String editarDireccionPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;

        paciente = pacienteDAO.findById(s, nss);

        //Establecemos los datos de dirección para el Paciente
        domicilioPacientes.setId(idDomicilioPaciente);
        domicilioPacientes.setCalle(calle);
        domicilioPacientes.setColonia(colonia);
        domicilioPacientes.setDelegacion(delegacion);
        domicilioPacientes.setEntidadFederativa(entidadFederativa);
        domicilioPacientes.setCodigoPostal(codigoPostal);
        domicilioPacientes.setPacientes(paciente);

        if (domicilioPacienteDAO.update(domicilioPacientes)) {
            actualizacionCorrecta = true;
        } else {
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String editarTelefonosPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;
        Enumeration<String> parametros;
        parametros = request.getParameterNames();

        while (parametros.hasMoreElements()) {
            String nombreParametro = parametros.nextElement();
            
            if(nombreParametro.startsWith("checkboxTelefonoEliminar")) {
                Long idTelefono = Long.parseLong(request.getParameter(nombreParametro));
                
                if(telefonoPacienteDAO.deleteTelefonoPaciente(idTelefono)) {
                    System.out.println("Se elimino el telefono con id" + request.getParameter(nombreParametro));
                    actualizacionCorrecta = true;
                }
                else {
                    System.out.println("No se elimino el telefono con id: " + request.getParameter(nombreParametro));
                    actualizacionCorrecta = false;
                    mensajeError = "Error al actualizar los datos del Paciente";
                }
            }
            
            if(nombreParametro.startsWith("checkboxTelefonoEditar")) {
                String nssEditar = request.getParameter("nss");
                paciente = pacienteDAO.findById(s, nssEditar);
                String parametroTelefonoEditar = nombreParametro.substring(22);
                Long idTelefonoEditar = Long.parseLong(request.getParameter("checkboxTelefonoEditar"+parametroTelefonoEditar));
                String numTelEditar = request.getParameter("numTelefono"+parametroTelefonoEditar);
                telefonosPacientes.setId(idTelefonoEditar);
                telefonosPacientes.setNumeroTelefono(numTelEditar);
                telefonosPacientes.setPacientes(paciente);
                
                if(telefonoPacienteDAO.update(telefonosPacientes)) {
                    System.out.println("El telefono del paciente se actualizo correctamente");
                    actualizacionCorrecta = true;
                }
                else {
                    System.out.println("El telefono del paciente se no actualizo correctamente");
                    actualizacionCorrecta = false;
                    mensajeError = "Error al actualizar los datos del Paciente";
                }
                
            }
            
            if(nombreParametro.startsWith("newTelefono")) {
                String nssEditar = request.getParameter("nss");
                paciente = pacienteDAO.findById(s, nssEditar);
                String parametroTelefonoEditar = nombreParametro.substring(11);
                String nuevoTelPaciente =request.getParameter("numTelefono"+parametroTelefonoEditar);
                telefonosPacientes.setNumeroTelefono(nuevoTelPaciente);
                telefonosPacientes.setPacientes(paciente);
                
                if(telefonoPacienteDAO.save(telefonosPacientes)) {
                    System.out.println("El telefono se agrego correctamente");
                    actualizacionCorrecta = true;
                    mensajeError = "Error al actualizar los datos del Paciente";
                }
                else {
                    System.out.println("El telefono no se agrego correctamente");
                    actualizacionCorrecta = false;
                    mensajeError = "Error al actualizar los datos del Paciente";
                }
            }
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String editarDatosPersonalesPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;

        paciente = pacienteDAO.findById(s, nss);

        //Establecemos los datos personales para el Paciente
        datosPersonales.setId(idDatosPersonalesPaciente);
        datosPersonales.setEstadoCivil(estadoCivil);
        datosPersonales.setCurp(curp);
        datosPersonales.setSexo(sexo);
        datosPersonales.setFechaNacimiento(fechaNacimiento);
        datosPersonales.setEdad(edad);
        datosPersonales.setPeso(peso);
        datosPersonales.setAltura(altura);
        datosPersonales.setTalla(talla);
        datosPersonales.setCorreo(correo);
        datosPersonales.setFacebook(facebook);

        datosPersonales.setPacientes(paciente);

        if (datosPersonalesDAO.update(datosPersonales)) {
            actualizacionCorrecta = true;
        } else {
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String editarContactosPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;
        Enumeration<String> parametrosContacto;
        parametrosContacto = request.getParameterNames();

        while (parametrosContacto.hasMoreElements()) {
            String nombreParametro = parametrosContacto.nextElement();
            
            if(nombreParametro.startsWith("checkboxContactoEliminar")) {
                //String parametroTelefonoEliminar = nombreParametro.substring(24);
                Long idContacto = Long.parseLong(request.getParameter(nombreParametro));
                
                if(contactoDAO.deleteContactoPaciente(idContacto)){
                    actualizacionCorrecta = true;
                    System.out.println("Se elimino el contacto con id" + request.getParameter(nombreParametro));
                }
                else {
                    actualizacionCorrecta = false;
                    mensajeError = "Error al actualizar los datos del Paciente";
                    System.out.println("No se elimino el contacto con id: " + request.getParameter(nombreParametro));
                }                
            }
            
            if(nombreParametro.startsWith("checkboxContactoEditar")) {
                String nssEditar = request.getParameter("nss");
                paciente = pacienteDAO.findById(s, nssEditar);
                String parametroContactoEditar = nombreParametro.substring(22);
                Long idContactoEditar = Long.parseLong(request.getParameter("checkboxContactoEditar"+parametroContactoEditar));
                String nombreContacto = request.getParameter("nombreContacto"+parametroContactoEditar);
                String apellidoPatContacto = request.getParameter("apellidoPaternoContacto"+parametroContactoEditar);
                String apellidoMatContaco = request.getParameter("apellidoMaternoContacto"+parametroContactoEditar);
                String parentesco = request.getParameter("parentescoContacto"+parametroContactoEditar);
                String celularContacto = request.getParameter("celularContacto"+parametroContactoEditar);
                String facebookContacto = request.getParameter("facebookContacto"+parametroContactoEditar);
                String correoContacto = request.getParameter("correoContacto"+parametroContactoEditar);
                
                contactos.setId(idContactoEditar);
                contactos.setNombre(nombreContacto);
                contactos.setApellidoPaterno(apellidoPatContacto);
                contactos.setApellidoMaterno(apellidoMatContaco);
                contactos.setParentesco(parentesco);
                contactos.setCelular(celularContacto);
                contactos.setFacebook(facebookContacto);
                contactos.setCorreo(correoContacto);
                contactos.setPacientes(paciente);

                if(contactoDAO.update(contactos)) {
                    actualizacionCorrecta = true;
                    System.out.println("El contacto del paciente se actualizo correctamente");
                }
                else {
                    mensajeError = "Error al actualizar los datos del Paciente";
                    actualizacionCorrecta = false;
                    System.out.println("El contacto del paciente se no actualizo correctamente");
                }
                
            }
            
            if(nombreParametro.startsWith("newContacto")) {
                String nssEditar = request.getParameter("nss");
                paciente = pacienteDAO.findById(s, nssEditar);
                String parametroContactoEditar = nombreParametro.substring(11);
                
                String nombreContacto = request.getParameter("nombreContacto"+parametroContactoEditar);
                String apellidoPatContacto = request.getParameter("apellidoPaternoContacto"+parametroContactoEditar);
                String apellidoMatContaco = request.getParameter("apellidoMaternoContacto"+parametroContactoEditar);
                String parentesco = request.getParameter("parentescoContacto"+parametroContactoEditar);
                String celularContacto = request.getParameter("celularContacto"+parametroContactoEditar);
                String facebookContacto = request.getParameter("facebookContacto"+parametroContactoEditar);
                String correoContacto = request.getParameter("correoContacto"+parametroContactoEditar);

                contactos.setNombre(nombreContacto);
                contactos.setApellidoPaterno(apellidoPatContacto);
                contactos.setApellidoMaterno(apellidoMatContaco);
                contactos.setParentesco(parentesco);
                contactos.setCelular(celularContacto);
                contactos.setFacebook(facebookContacto);
                contactos.setCorreo(correoContacto);
                contactos.setPacientes(paciente);
                
                if(contactoDAO.save(contactos)) {
                    System.out.println("El contacto se agrego correctamente");
                    actualizacionCorrecta = true;
                }
                else {
                    mensajeError = "Error al actualizar los datos del Paciente";
                    System.out.println("El contacto no se agrego correctamente");
                    actualizacionCorrecta = false;
                }
            }
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }
    
    public String editarMedicamentosPaciente() {
        System.out.println("Entro a Editar Medicamentos");
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;
        Enumeration<String> parametrosMedicamentos;
        parametrosMedicamentos = request.getParameterNames();

        while (parametrosMedicamentos.hasMoreElements()) {
            String nombreParametro = parametrosMedicamentos.nextElement();
            System.out.println(request.getParameter(nombreParametro));
            if(nombreParametro.startsWith("checkboxMedicamentoEliminar")) {
                //String parametroTelefonoEliminar = nombreParametro.substring(24);
                Long idMedicamento = Long.parseLong(request.getParameter(nombreParametro));
                System.out.println(idMedicamento);
                
                if(medicacionDAO.deleteMedicamentoPaciente(idMedicamento)) {
                    System.out.println("Se elimino el medicamento con id" + request.getParameter(nombreParametro));
                    actualizacionCorrecta = true;
                }
                else {
                    System.out.println("No se elimino el medicamento con id: " + request.getParameter(nombreParametro));
                    actualizacionCorrecta = false;
                }
            }
            
            if(nombreParametro.startsWith("checkboxMedicamentoEditar")) {
                Long noHistorialMedicacion = Long.parseLong(request.getParameter("noHisto"));
                datosClinicos = datosClinicosDAO.findById(s, noHistorialMedicacion);
                String parametroMedicamentoEditar = nombreParametro.substring(25);
                Long idMedicamentoEditar = Long.parseLong(request.getParameter("checkboxMedicamentoEditar"+parametroMedicamentoEditar));
                String nombreMedicamento = request.getParameter("medicamento"+parametroMedicamentoEditar);
                String frecuenciaMedicamentos = request.getParameter("frecuencia"+parametroMedicamentoEditar);
                System.out.println(idMedicamentoEditar);
                System.out.println(nombreMedicamento);
                System.out.println(frecuenciaMedicamentos);
                
                medicacion.setId(idMedicamentoEditar);
                medicacion.setNombreMedicamento(nombreMedicamento);
                medicacion.setFrecuencia(frecuenciaMedicamentos);
                medicacion.setDatosClinicos(datosClinicos);

                if(medicacionDAO.update(medicacion)) {
                    System.out.println("El medicamento del paciente se actualizo correctamente");
                    actualizacionCorrecta = true;
                }
                else {
                    mensajeError = "Error al actualizar los datos del Paciente";
                    System.out.println("El medicamento del paciente se no actualizo correctamente");
                    actualizacionCorrecta = false;
                }                
                

            }
            
            if(nombreParametro.startsWith("newMedicamento")) {
                System.out.println(request.getParameter("noHisto").toString());
                Long noHistorialMedicacion = Long.parseLong(request.getParameter("noHisto"));
                System.out.println(noHistorialMedicacion);
                datosClinicos = datosClinicosDAO.findById(s, noHistorialMedicacion);
                String parametroMedicamentoNuevo = nombreParametro.substring(14);
                
                String nombreMedicamento = request.getParameter("medicamento"+parametroMedicamentoNuevo);
                String frecuenciaMedicamentos = request.getParameter("frecuencia"+parametroMedicamentoNuevo);
                
                System.out.println(nombreMedicamento);
                System.out.println(frecuenciaMedicamentos);
                System.out.println(datosClinicos.getNoHistorial());
                medicacion.setNombreMedicamento(nombreMedicamento);
                medicacion.setFrecuencia(frecuenciaMedicamentos);
                medicacion.setDatosClinicos(datosClinicos);
                
                if(medicacionDAO.save(medicacion)) {
                    System.out.println("El medicamento se agrego correctamente");
                    actualizacionCorrecta = true;
                }
                else {
                    mensajeError = "Error al actualizar los datos del Paciente";
                    System.out.println("El medicamento no se agrego correctamente");
                    actualizacionCorrecta = false;
                }                
            }
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }
    
    public String editarEnfermedadesCronicasPaciente() throws ParseException {
        System.out.println("Entro a enfermedades");
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;
        Enumeration<String> parametrosEnfermedades;
        parametrosEnfermedades = request.getParameterNames();

        while (parametrosEnfermedades.hasMoreElements()) {
            String nombreParametro = parametrosEnfermedades.nextElement();

            if(nombreParametro.startsWith("checkboxEnfermedadEliminar")) {
                //String parametroTelefonoEliminar = nombreParametro.substring(24);
                Long idEnfermedad = Long.parseLong(request.getParameter(nombreParametro));
                System.out.println(idEnfermedad);
                
                if(enfermedadCronicaDAO.deleteEnfermedadPaciente(idEnfermedad)) {
                    System.out.println("Se elimino la enfermedad con id" + request.getParameter(nombreParametro));
                    actualizacionCorrecta = true;
                }
                else {
                    System.out.println("No se elimino la enfermedad con id: " + request.getParameter(nombreParametro));
                    actualizacionCorrecta = false;
                }
            }
            
            if(nombreParametro.startsWith("checkboxEnfermedadEditar")) {
                Long noHistorialEnfermedad = Long.parseLong(request.getParameter("noHistorialEnfermedad"));
                datosClinicos = datosClinicosDAO.findById(s, noHistorialEnfermedad);
                String parametroEnfermedadEditar = nombreParametro.substring(24);                

                Long idEnfermedadNueva = Long.parseLong(request.getParameter("checkboxEnfermedadEditar"+parametroEnfermedadEditar));
                String nombreEnfermedad = request.getParameter("enfermedadCronica"+parametroEnfermedadEditar);
                String tipoEnfermedad = request.getParameter("tipoEnfermedad"+parametroEnfermedadEditar);
                String inicioEnfermedad = request.getParameter("inicioEnfermedad"+parametroEnfermedadEditar);
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                Date dateInicioEnfermedad = null;
                dateInicioEnfermedad = formatoDeFecha.parse(inicioEnfermedad);
                System.out.println(idEnfermedadNueva);
                System.out.println(nombreEnfermedad);
                System.out.println(tipoEnfermedad);
                System.out.println(inicioEnfermedad);
                
                enfermedadCronica.setId(idEnfermedadNueva);
                enfermedadCronica.setNombre(nombreEnfermedad);
                enfermedadCronica.setTipo(tipoEnfermedad);
                enfermedadCronica.setIncioEnfermedad(dateInicioEnfermedad);
                enfermedadCronica.setDatosClinicos(datosClinicos);

                if(enfermedadCronicaDAO.update(enfermedadCronica)) {
                    System.out.println("La enfermedad del paciente se actualizo correctamente");
                    actualizacionCorrecta = true;
                }
                else {
                    mensajeError = "Error al actualizar los datos del Paciente";
                    System.out.println("La enfermedad del paciente no se actualizo correctamente");
                    actualizacionCorrecta = false;
                }                
            }
            
            if(nombreParametro.startsWith("newEnfermedadCronica")) {
                Long noHistorialEnfermedad = Long.parseLong(request.getParameter("noHistorialEnfermedad"));
                datosClinicos = datosClinicosDAO.findById(s, noHistorialEnfermedad);
                String parametroEnfermedadNueva = nombreParametro.substring(20);
                
                String nombreEnfermedad = request.getParameter("enfermedadCronica"+parametroEnfermedadNueva);
                String tipoEnfermedad = request.getParameter("tipoEnfermedad"+parametroEnfermedadNueva);
                String inicioEnfermedad = request.getParameter("inicioEnfermedad"+parametroEnfermedadNueva);
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                Date dateInicioEnfermedad = null;
                dateInicioEnfermedad = formatoDeFecha.parse(inicioEnfermedad);
                System.out.println(nombreEnfermedad);
                System.out.println(tipoEnfermedad);
                System.out.println(inicioEnfermedad);
                
                enfermedadCronica.setNombre(nombreEnfermedad);
                enfermedadCronica.setTipo(tipoEnfermedad);
                enfermedadCronica.setIncioEnfermedad(dateInicioEnfermedad);
                enfermedadCronica.setDatosClinicos(datosClinicos);
                
                if(enfermedadCronicaDAO.save(enfermedadCronica)) {
                    System.out.println("La enfermedad se agrego correctamente");
                    actualizacionCorrecta = true;
                }
                else {
                    mensajeError = "Error al actualizar los datos del Paciente";
                    System.out.println("La enfermedad no se agrego correctamente");
                    actualizacionCorrecta = false;
                }                
            }
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String editarDatosClinicosPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Boolean actualizacionCorrecta = false;

        paciente = pacienteDAO.findById(s, nss);

        //Establecemos los datos clinicos para el paciente----->Tabla de Datos Clinicos
        datosClinicos.setNoHistorial(noHistorial);

        if (drogas.equals("0")) {
            datosClinicos.setUsoDrogas(false);
        } else {
            datosClinicos.setUsoDrogas(true);
        }

        if (alcohol.equals("0")) {
            datosClinicos.setUsoAlcohol(false);
        } else {
            datosClinicos.setUsoAlcohol(true);
        }

        if (fuma.equals("0")) {
            datosClinicos.setFumador(false);
        } else {
            datosClinicos.setFumador(true);
        }

        datosClinicos.setPacientes(paciente);//----->Llave foranea del Paciente

        if (datosClinicosDAO.update(datosClinicos)) {
            actualizacionCorrecta = true;
        } else {
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
        s.close();
        return "pantallaModificarEliminarPaciente";
    }

    public String buscarDatosPaciente() throws IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a datos pacientes");
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
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
                FileOutputStream image = new FileOutputStream(filePath + "imagenesPerfilPaciente/" + nombreUsuario + ".jpeg");
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
        s.close();
        return "success";
    }

    public String buscarDatosDireccionPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a direccion pacientes");
        listUsuarios = usuarioDAO.listarById(s, getNombreUsuario());
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
        s.close();
        return "success";
    }

    public String buscarTelefonosPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a telefonos pacientes");
        String html = "";
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set telPaciente = paciente.getTelefonosPacienteses();
                int index = 0;
                for (Iterator iterator3 = telPaciente.iterator(); iterator3.hasNext();) {
                    telefonosPacientes = (TelefonosPacientes) iterator3.next();
                    html += "<div id=\"telefonoPaciente" + index + "\">"
                            + "   <div id=\"divTelefonoFijoPaciente\" class=\"form-group\">"
                            + "       <label for=\"telefonoPaciente\">Teléfono #" + (index + 1) + "</label><br>"
                            + "       <input type=\"checkbox\" id=\"checkboxTelefonoEliminar" + index +"\" name=\"checkboxTelefonoEliminar" + index +"\" value=\""+telefonosPacientes.getId()+"\">Eliminar     "
                            + "       <input type=\"checkbox\" id=\"checkboxTelefonoEditar"+index+"\" name=\"checkboxTelefonoEditar" + index +"\" value=\""+telefonosPacientes.getId()+"\">Editar"
                            + "       <input type=\"hidden\" id=\"nss\" name=\"nss\" value=\""+paciente.getNss()+"\">"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" disabled=\"true\" value=\"" + telefonosPacientes.getNumeroTelefono() + "\" name=\"numTelefono" + index + "\" id=\"numTelefono" + index + "\" class=\"form-control\" data-inputmask=\"&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;\" data-mask=\"\" placeholder=\"No. Telefono\" type=\"text\">"
                            + "   </div>"
                            + "</div>";
                    System.out.println(index);
                    System.out.println("Telefono-->" + telefonosPacientes.getNumeroTelefono() + " Id: " + telefonosPacientes.getId() + " NSS:" + paciente.getNss());
                    index++;
                }
            }
        }

        telefonosDelPaciente = html;
        System.out.println(telefonosDelPaciente);
        s.close();
        return "success";
    }

    public String buscarDatosPersonalesPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a datos personales pacientes");
        listUsuarios = usuarioDAO.listarById(s, getNombreUsuario());
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
                    DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    fechaNacimientoFormato = hourdateFormat.format(fechaNacimiento);
                    edad = datosPersonales.getEdad();
                    peso = datosPersonales.getPeso();
                    altura = datosPersonales.getAltura();
                    talla = datosPersonales.getTalla();
                    correo = datosPersonales.getCorreo();
                    facebook = datosPersonales.getFacebook();
                    nombreUsuario = userPaciente.getNombreUsuario();
                    nss = paciente.getNss();
                    System.out.println("fecha con formato--->" + fechaNacimientoFormato);
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
        s.close();
        return "success";
    }

    public String buscarContactosPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a contactos pacientes");
        String html = "";
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set contacPaciente = paciente.getContactoses();
                int index = 0;
                for (Iterator iterator3 = contacPaciente.iterator(); iterator3.hasNext();) {
                    contactos = (Contactos) iterator3.next();
                    html += "<div id=\"contactoPaciente" + index + "\">" + "\n"
                            + "   <label for=\"nombreC\">Contacto #" + (index + 1) + "</label><br>" + "\n"
                            + "       <input type=\"checkbox\" id=\"checkboxContactoEliminar" + index +"\" name=\"checkboxContactoEliminar" + index +"\" value=\""+contactos.getId()+"\">Eliminar     "
                            + "       <input type=\"checkbox\" id=\"checkboxContactoEditar"+index+"\" name=\"checkboxContactoEditar" + index +"\" value=\""+contactos.getId()+"\">Editar"
                            + "       <input type=\"hidden\" id=\"nss\" name=\"nss\" value=\""+paciente.getNss()+"\">"
                            + "   <div id=\"divNombreCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"nombreC\">Nombre</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getNombre() + "\" class=\"form-control\" name=\"nombreContacto" + index + "\" id=\"nombreContacto" + index + "\" placeholder=\"Nombre\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "   <div id=\"divApellidoPaternoCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"apellidoPaternoC\">Apellido Paterno</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getApellidoPaterno() + "\" class=\"form-control\" name=\"apellidoPaternoContacto" + index + "\" id=\"apellidoPaternoContacto" + index + "\" placeholder=\"Apellido Paterno\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "   <div id=\"divApellidoMaternoCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"apellidoMaternoC\">Apellido Materno</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getApellidoMaterno() + "\" class=\"form-control\" name=\"apellidoMaternoContacto" + index + "\" id=\"apellidoMaternoContacto" + index + "\" placeholder=\"Apellido Materno\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "   <div id=\"divParentescoCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"parentesco\">Parentesco</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getParentesco() + "\" class=\"form-control\" name=\"parentescoContacto" + index + "\" id=\"parentescoContacto" + index + "\" placeholder=\"Parentesco\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "   <div id=\"divCelularCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"celular\">Telefono Celular</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getCelular() + "\" name=\"celularContacto" + index + "\" id=\"celularContacto" + index + "\" class=\"form-control\" data-inputmask=\"&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;\" data-mask=\"\" placeholder=\"Celular\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "   <div id=\"divFacebookCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"facebookC\">Facebook (www.facebook.com/alguien)</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getFacebook() + "\" class=\"form-control\" name=\"facebookContacto" + index + "\" id=\"facebookContacto" + index + "\" placeholder=\"Facebook\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "   <div id=\"divCorreoCPaciente" + index + "\" class=\"form-group\">" + "\n"
                            + "       <label for=\"correoC\">Email</label>" + "\n"
                            + "       <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + contactos.getCorreo() + "\" class=\"form-control\" name=\"correoContacto" + index + "\" id=\"correoContacto" + index + "\" placeholder=\"Email\" type=\"text\">" + "\n"
                            + "   </div>" + "\n"
                            + "</div>";
                    System.out.println(index);
                    System.out.println("Id: " + contactos.getId() + "\n"
                            + "Nombre: " + contactos.getNombre() + "\n"
                            + "ApellidoP: " + contactos.getApellidoPaterno() + "\n"
                            + "ApellidoM" + contactos.getApellidoMaterno() + "\n"
                            + "Parentesco: " + contactos.getParentesco() + "\n"
                            + "Celular:" + contactos.getCelular() + "\n"
                            + "Face: " + contactos.getFacebook() + "\n"
                            + "Correo:" + contactos.getCorreo() + "\n"
                            + "Nss: " + paciente.getNss());
                    index++;
                }
            }
        }
        contactosDelPaciente = html;
        s.close();
        return "success";
    }

    public String buscarDatosClinicosPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a datos clinicos pacientes");
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    noHistorial = datosClinicos.getNoHistorial();
                    if (datosClinicos.isUsoDrogas() == true) {
                        drogas = "1";
                    } else {
                        drogas = "0";
                    }
                    if (datosClinicos.isUsoAlcohol() == true) {
                        alcohol = "1";
                    } else {
                        alcohol = "0";
                    }
                    if (datosClinicos.isFumador() == true) {
                        fuma = "1";
                    } else {
                        fuma = "0";
                    }
                    nombreUsuario = userPaciente.getNombreUsuario();
                    nss = paciente.getNss();
                    System.out.println(noHistorial);
                    System.out.println("drogas " + drogas);
                    System.out.println("alcohol " + alcohol);
                    System.out.println("fuma " + fuma);
                    System.out.println(nombreUsuario);
                    System.out.println(nss);
                }
            }
        }
        s.close();
        return "success";
    }

    public String buscarAlergiasPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a alergias pacientes");
        ArrayList<String> nombreAlergia = new ArrayList<>();
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    Set datClinicosAlergias = datosClinicos.getAlergiases();
                    for (Iterator iterator5 = datClinicosAlergias.iterator(); iterator5.hasNext();) {
                        alergia = (Alergias) iterator5.next();
                        nombreAlergia.add(alergia.getTipoAlergia() + ";" + alergia.getEspecificacion() + ";" + alergia.getId());
                    }
                }
            }
        }
        alergias = nombreAlergia;
        System.out.println(alergias);
        s.close();
        return "success";
    }

    public String buscarCirugiasPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a cirugias pacientes");
        ArrayList<String> nombreCirugia = new ArrayList<>();
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    Set datClinicosCirugias = datosClinicos.getCirugiases();
                    for (Iterator iterator4 = datClinicosCirugias.iterator(); iterator4.hasNext();) {
                        cirugia = (Cirugias) iterator4.next();
                        nombreCirugia.add(cirugia.getTipoCirugia() + ";" + cirugia.getNoCirugia() + ";" + cirugia.getId());
                    }
                }
            }
        }
        cirugias = nombreCirugia;
        System.out.println(cirugias);
        s.close();
        return "success";
    }

    public String buscarDiscapacidadesPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a discapacidades pacientes");
        ArrayList<String> nombreDiscapacidad = new ArrayList<>();
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    Set datClinicosDiscapacidades = datosClinicos.getDiscapacidadeses();
                    for (Iterator iterator4 = datClinicosDiscapacidades.iterator(); iterator4.hasNext();) {
                        discapacidad = (Discapacidades) iterator4.next();
                        nombreDiscapacidad.add(discapacidad.getTipo() + ";" + discapacidad.getId());
                    }
                }
            }
        }
        discapacidades = nombreDiscapacidad;
        System.out.println(discapacidades);
        s.close();
        return "success";
    }

    public String buscarMedicamentosPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a medicamentos pacientes");
        String html = "";
        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    Set datClinicosMedicacion = datosClinicos.getMedicacions();
                    int index = 0;
                    for (Iterator iterator4 = datClinicosMedicacion.iterator(); iterator4.hasNext();) {
                        medicacion = (Medicacion) iterator4.next();
                        html += "  <input type=\"checkbox\" id=\"checkboxMedicamentoEliminar" + index +"\" name=\"checkboxMedicamentoEliminar" + index +"\" value=\""+medicacion.getId()+"\">Eliminar     "
                                + "<input type=\"checkbox\" id=\"checkboxMedicamentoEditar"+index+"\" name=\"checkboxMedicamentoEditar" + index +"\" value=\""+medicacion.getId()+"\">Editar<br>"
                                + "       <input type=\"hidden\" id=\"noHisto\" name=\"noHisto\" value=\""+datosClinicos.getNoHistorial()+"\">"
                                + "<div id=\"medicamentos" + index + "\" class=\"row\">" + "\n"
                                + "   <div class=\"col-lg-6\">" + "\n"
                                + "       <div style=\"margin-bottom:10px;\" class=\"form-group\">" + "\n"
                                + "           <label>Nombre del medicamento #" + (index + 1) + "</label>" + "\n"
                                + "           <input class=\"form-control\" type=\"text\" value=\"" + medicacion.getNombreMedicamento() + "\" name=\"medicamento" + index + "\" id=\"medicamento" + index + "\" placeholder=\"Medicamentó" + (index + 1) + "\"/>" + "\n"
                                + "       </div>" + "\n"
                                + "   </div>" + "\n"
                                + "   <div class=\"col-lg-6\">" + "\n"
                                + "       <div style=\"margin-bottom:10px;\" class=\"form-group\">" + "\n"
                                + "           <label>Frecuencia</label>" + "\n"
                                + "           <input class=\"form-control\" type=\"text\" value=\"" + medicacion.getFrecuencia() + "\" name=\"frecuencia" + index + "\" id=\"frecuencia" + index + "\" placeholder=\"Frecuencia\"/>" + "\n"
                                + "       </div>" + "\n"
                                + "   </div>" + "\n"
                                + "</div>";
                        index++;
                    }
                }
            }
        }
        medicamentos = html;
        System.out.println(medicamentos);
        s.close();
        return "success";
    }

    public String buscarEnfermedadesCronicasPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a enfermedades cronicas pacientes");
        String html = "";
        ArrayList<Especialidades> especialidadesBD = new ArrayList<Especialidades>();
        EspecialidadDAO especialidadesDAO = new EspecialidadDAO();
        especialidadesBD = (ArrayList<Especialidades>) especialidadesDAO.findAll(s);

        if (especialidadesBD == null) {
            especialidadesBD = new ArrayList<Especialidades>();
        }

        listUsuarios = usuarioDAO.listarById(s, nombreUsuario);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userPaciente = (Usuarios) iterator1.next();
            Set pacientes = userPaciente.getPacienteses();
            for (Iterator iterator2 = pacientes.iterator(); iterator2.hasNext();) {
                paciente = (Pacientes) iterator2.next();
                Set datCliPaciente = paciente.getDatosClinicoses();
                for (Iterator iterator3 = datCliPaciente.iterator(); iterator3.hasNext();) {
                    datosClinicos = (DatosClinicos) iterator3.next();
                    Set datClinicosEnfermedadesCronicas = datosClinicos.getEnfermedadesCronicases();
                    int index = 0;
                    for (Iterator iterator4 = datClinicosEnfermedadesCronicas.iterator(); iterator4.hasNext();) {
                        enfermedadCronica = (EnfermedadesCronicas) iterator4.next();
                        Date inicioEnfermedad = enfermedadCronica.getIncioEnfermedad();
                        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String inicioEnfermedadFormato = hourdateFormat.format(inicioEnfermedad);
                        String enfermedad = enfermedadCronica.getTipo();
                        html += "  <input type=\"checkbox\" id=\"checkboxEnfermedadEliminar" + index +"\" name=\"checkboxEnfermedadEliminar" + index +"\" value=\""+enfermedadCronica.getId()+"\">Eliminar     "
                                + "<input type=\"checkbox\" id=\"checkboxEnfermedadEditar"+index+"\" name=\"checkboxEnfermedadEditar" + index +"\" value=\""+enfermedadCronica.getId()+"\">Editar"
                                + "<input type=\"hidden\" id=\"noHistorialEnfermedad\" name=\"noHistorialEnfermedad\" value=\""+datosClinicos.getNoHistorial()+"\"><br>"
                                + "<div id=\"enfermedadesCronicas" + index + "\" class=\"row\">" + "\n"
                                + "   <div class=\"col-lg-4\">" + "\n"
                                + "       <div style=\"margin-bottom:10px;\" class=\"form-group\">" + "\n"
                                + "           <label>Nombre enfermedad #" + (index + 1) + " : </label>" + "\n"
                                + "           <select class=\"form-control\" name=\"enfermedadCronica" + index + "\" id=\"enfermedadCronica" + index + "\" >" + "\n"
                                + "           <option value=\""+enfermedadCronica.getNombre()+"\">"+enfermedadCronica.getNombre()+"</option></select>" + "\n"
                                + "       </div>" + "\n"
                                + "   </div>" + "\n"
                                + "   <div class=\"col-lg-4\">" + "\n"
                                + "       <div style=\"margin-bottom:10px;\" class=\"form-group\">" + "\n"
                                + "           <label>Tipo</label>" + "\n"
                                + "           <select onchange=\"recuperarEnfermedadesCronicas("+ index +",this);\" name=\"tipoEnfermedad" + index + "\" class=\"form-control\">" + "\n"
                                + "           <option value=\"-1\">Seleccionar</option>" + "\n";

                        for (Especialidades especTem : especialidadesBD) {
                            if (enfermedad.equals(especTem.getNombreEspecialidad())) {
                                html += "<option value=\"" + especTem.getNombreEspecialidad() + "\" " + "selected" + ">" + especTem.getNombreEspecialidad() + "</option>" + "\n";
                            } else {
                                html += "<option value=\"" + especTem.getNombreEspecialidad() + "\" >" + especTem.getNombreEspecialidad() + "</option>" + "\n";

                            }
                        }

                        html +=  "     </select>  </div>" + "\n"
                                + "   </div>" + "\n"
                                + "   <div class=\"col-lg-4\">" + "\n"
                                + "       <div id=\"divInicioEnfermedadPaciente\" class=\"form-group\">" + "\n"
                                + "           <label for=\"inicioEnfermedad\">Inicio de enfermedad</label>" + "\n"
                                + "           <div class=\"input-group\">" + "\n"
                                + "               <div class=\"input-group-addon\">" + "\n"
                                + "                   <i class=\"fa fa-calendar\"></i>" + "\n"
                                + "               </div>" + "\n"
                                + "               <input kl_virtual_keyboard_secure_input=\"on\" value=\"" + inicioEnfermedadFormato + "\" class=\"form-control\" name=\"inicioEnfermedad" + index + "\" id=\"inicioEnfermedad" + index + "\" data-inputmask=\"\\'alias\\': \\'dd/mm/yyyy\\'\" data-mask placeholder=\"dd/mm/yyyy\" type=\"text\">" + "\n"
                                + "           </div>" + "\n"
                                + "       </div>" + "\n"
                                + "   </div>" + "\n"
                                + "</div>";

                        index++;
                    }
                }
            }
        }
        enfermedadesCronicas = html;
        System.out.println(enfermedadesCronicas);
        s.close();
        return "success";
    }

    public String recuperarComboEnfermedadesPorEspecialidadModElim() {
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
        System.out.println("--->Entro a recuperarComboEnfermedadesPorEspecialidad" + especialidadCombo);
        OWLConsultas consultor = new OWLConsultas(ONTOLOGIA, BASE_URI);
        String html = "<option value=\"-1\">Seleccionar</option>\n";

        ArrayList<String> listaEnfermedadesOntologia = (ArrayList<String>) consultor.especialidadEstudiaAEnfermedad(especialidadCombo);

        if (listaEnfermedadesOntologia == null) {
            listaEnfermedadesOntologia = new ArrayList<String>();
        }

        for (String efermedadTemp : listaEnfermedadesOntologia) {
            html += "<option value=\"" + efermedadTemp + "\">" + efermedadTemp + "</option>\n";
        }

        htmlEnfermedades = html;

        return SUCCESS;
    }

    public String validarNombreUsuarioEliminar() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(s, getNombreUsuario());

        if (usuarioResultado == null) {
            setEstatusMensajeEliminar("usuarioNoEncontrado");
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(getNombreUsuario())) {
            setEstatusMensajeEliminar("usuarioEncontrado");
        } else {
            setEstatusMensajeEliminar("usuarioNoEncontrado");
        }
        s.close();
        return SUCCESS;
    }

    public String buscarDatosPacienteMostrarFiltro() throws FileNotFoundException, IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        ArrayList<Usuarios> listaTemp = new ArrayList<Usuarios>();

        if (nombreUsuario.length() > 0) {
            listaTemp = (ArrayList<Usuarios>) usuarioDAO.findPacientesLike(s, nombreUsuario);
            System.out.println("--->Entro a filtro mayor " + listaTemp.size());
            if (listaTemp == null) {
                estatusMensajeEliminar = "usuarioNoEncontrado";
            } else {
                estatusMensajeEliminar = "usuarioEncontrado";
            }

        } else {
            // Obtenemos la lista de la sesión
            listaTemp = (ArrayList<Usuarios>) usuarioDAO.listarPacientes(s, 0, 0);
            estatusMensajeEliminar = "usuarioEncontrado";
        }
        session.put(com.saem.actions.GridRegistroPaciente.LISTA_GRID_MODEL, listaTemp);
        s.close();
        return "success";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        servletRequest = hsr;
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

    public String getDrogas() {
        return drogas;
    }

    public void setDrogas(String drogas) {
        this.drogas = drogas;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
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

    public String getEstatusMensajeEliminar() {
        return estatusMensajeEliminar;
    }

    public void setEstatusMensajeEliminar(String estatusMensajeEliminar) {
        this.estatusMensajeEliminar = estatusMensajeEliminar;
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

    public String getFechaNacimientoFormato() {
        return fechaNacimientoFormato;
    }

    public void setFechaNacimientoFormato(String fechaNacimientoFormato) {
        this.fechaNacimientoFormato = fechaNacimientoFormato;
    }

    public Long getNoHistorial() {
        return noHistorial;
    }

    public void setNoHistorial(Long noHistorial) {
        this.noHistorial = noHistorial;
    }

    public String getTelefonosDelPaciente() {
        return telefonosDelPaciente;
    }

    public void setTelefonosDelPaciente(String telefonosDelPaciente) {
        this.telefonosDelPaciente = telefonosDelPaciente;
    }

    public String getContactosDelPaciente() {
        return contactosDelPaciente;
    }

    public void setContactosDelPaciente(String contactosDelPaciente) {
        this.contactosDelPaciente = contactosDelPaciente;
    }

    public ArrayList<String> getCirugias() {
        return cirugias;
    }

    public void setCirugias(ArrayList<String> cirugias) {
        this.cirugias = cirugias;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(String enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }

    public String getHtmlEnfermedades() {
        return htmlEnfermedades;
    }

    public void setHtmlEnfermedades(String htmlEnfermedades) {
        this.htmlEnfermedades = htmlEnfermedades;
    }

    public String getEspecialidadCombo() {
        return especialidadCombo;
    }

    public void setEspecialidadCombo(String especialidadCombo) {
        this.especialidadCombo = especialidadCombo;
    }

}
