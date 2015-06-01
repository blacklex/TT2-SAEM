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
import com.hibernate.dao.HospitalDAO;
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
import com.hibernate.model.Hospitales;
import com.hibernate.model.TelefonosPacientes;
import com.hibernate.model.Usuarios;
import com.hibernate.model.Pacientes;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author gabriela
 */
public class RegistrarPaciente implements SessionAware, ServletRequestAware {
    
    private Map<String, Object> session = null;
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private final DomicilioPacienteDAO domicilioPacienteDAO = new DomicilioPacienteDAO();
    private final DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
    private final DatosClinicosDAO datosClinicosDAO = new DatosClinicosDAO();
    private final ContactoDAO contactoDAO = new ContactoDAO();
    private final HospitalDAO hospitalDAO = new HospitalDAO();
    private final AlergiaDAO alergiaDAO = new AlergiaDAO();
    private final CirugiaDAO cirugiaDAO = new CirugiaDAO();
    private final DiscapacidadDAO discapacidadDAO = new DiscapacidadDAO();
    

    private HttpServletRequest servletRequest;
    
    //Acceso
    String nombreUsuario;
    String clave;
    //Datos Paciente
    String nss;
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String unidadMedica;
    String noConsultorio;
    File imagen;
    //Direccion
    String calle;
    String colonia;
    String delegacion;
    String entidadFederativa;
    String codigoPostal;
    //Telefonos
    String numerosTelefonicos;
    //Datos Personales
    String estadoCivil;
    String curp;
    String sexo;
    Date fechaNacimiento;
    String edad;
    String peso;
    String altura;
    String talla;
    String correo;
    String facebook;
    //Clinicos
    String drogas;
    String alcohol;
    String fuma;
    //Alergias
    String checkboxAlergiaPolen; 
    String especificarAlergiaPolen;
    String checkboxAlergiaAcaros;
    String especificarAlergiaAcaros;
    String checkboxAlergiaAnimales;
    String especificarAlergiaAnimales;
    String checkboxAlergiaMedicamentos;
    String especificarAlergiaMedicamentos;
    String checkboxAlergiaInsectos;
    String especificarAlergiaInsectos;
    String checkboxAlergiaAlimentos;
    String especificarAlergiaAlimentos;
    //Cirugias
    String checkboxInterna;
    String noCirugiaInterna;
    String checkboxExterna;
    String noCirugiaExterna;
    String checkboxMayor;
    String noCirugiaMayor;
    String checkboxMenor;
    String noCirugiaMenor;
    String checkboxCurativa;
    String noCirugiaCurativa;
    String checkboxReparadora;
    String noCirugiaReparadora;
    String checkboxPaliativa;
    String noCirugiaPaliativa;
    String checkboxCosmetica;
    String noCirugiaCosmetica;
    //Discapacidades
    String checkboxFisica;
    String checkboxIntelectual;
    String checkboxPsiquica;
    String checkboxVisual;
    String checkboxAuditiva;
    String checkboxHabla;
    
    String htmlHospitales;
    String codigoHospital;

    //campos json retorno
    private String tituloAlert;
    private String textoAlert;
    private String estatusMensaje;

    
    String mensajeError = "";
    
    public String registrarPaciente() throws ParseException, FileNotFoundException, IOException {
        MultiPartRequestWrapper multiWrapper =(MultiPartRequestWrapper) ServletActionContext.getRequest();
        Enumeration<String> parametros;
        Boolean registroCorrecto = false;
        Usuarios userPaciente = new Usuarios();
        Pacientes paciente = new Pacientes();
        TelefonosPacientes telefonoPaciente = new TelefonosPacientes();
        DomicilioPacientes domicilioPaciente = new DomicilioPacientes();
        DatosPersonales datosPersonales = new DatosPersonales();
        DatosClinicos datosClinicos = new DatosClinicos();
        Contactos contacto = new Contactos();
        Hospitales hospital = new Hospitales();
        Alergias alergiasPaciente = new Alergias();
        Cirugias cirugiasPaciente = new Cirugias();
        Discapacidades discapacidadPaciente = new Discapacidades();
        
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);
        //Recuperamos el codigo del hospital
        
        Calendar cal = Calendar.getInstance();
        String codigoClinico = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DAY_OF_MONTH) + "" + cal.get(Calendar.HOUR) + "" + cal.get(Calendar.MINUTE) + "" + cal.get(Calendar.SECOND) + "" + cal.get(Calendar.MILLISECOND);
        hospital = hospitalDAO.findById(codigoHospital);
        
        //Establecemos los datos de acceso para el Paciente
        userPaciente = new Usuarios(nombreUsuario, "Paciente", clave, date);
        
        //Establecemos los datos para el Paciente
        paciente.setNss(nss);
        paciente.setNombre(nombre);
        paciente.setApellidoPaterno(apellidoPaterno);
        paciente.setApellidoMaterno(apellidoMaterno);
        paciente.setUnidadMedica(unidadMedica);
        paciente.setNoConsultorio(noConsultorio);
        //Establecemos la clave foranea del Paciente haciendo referencia al hospital
        paciente.setHospitales(hospital);
        //Convertimos la imagen a un arreglo de
        if(imagen != null) {
            System.out.println("Camino absoluto    "+imagen.getAbsolutePath());
            byte[] bFile = new byte[(int) imagen.length()];
            FileInputStream fileInputStream = new FileInputStream(imagen);
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
        //Guardamos los datos de acceso del paciente
        usuarioDAO.save(userPaciente);
        
        //Establecemos la clave foranea del Paciente con referencia a Usuarios
        paciente.setUsuarios(userPaciente);
        
        //Establecemos los datos de dirección para el Paciente
        domicilioPaciente.setCalle(calle);
        domicilioPaciente.setColonia(colonia);
        domicilioPaciente.setDelegacion(delegacion);
        domicilioPaciente.setEntidadFederativa(entidadFederativa);
        domicilioPaciente.setCodigoPostal(codigoPostal);

        pacienteDAO.save(paciente);
        
        domicilioPaciente.setPacientes(paciente);
        
        datosClinicos.setNoHistorial(Long.parseLong(codigoClinico));
        
        if(drogas.equals("0"))
            datosClinicos.setUsoDrogas(false);
        else
            datosClinicos.setUsoDrogas(true);
        
        if(alcohol.equals("0"))
            datosClinicos.setUsoAlcohol(false);
        else
            datosClinicos.setUsoAlcohol(true);
        
        if(fuma.equals("0"))
            datosClinicos.setFumador(false);
        else
            datosClinicos.setFumador(true);
        
        domicilioPacienteDAO.save(domicilioPaciente);
        
        datosClinicos.setPacientes(paciente);

        ///////////////////////////
        datosClinicosDAO.save(datosClinicos);
        parametros = multiWrapper.getParameterNames();
        while (parametros.hasMoreElements()) {
            
            String nombreParametro = parametros.nextElement();
            /*********************************************Alergias************************************************************/

            if (nombreParametro.startsWith("checkboxAlergia") || nombreParametro.startsWith("especificarAlergia")) {
                
                if(multiWrapper.getParameter(nombreParametro).equals("polen") && (multiWrapper.getParameter("especificarAlergiaPolen0") != null)){
                    alergiasPaciente.setTipoAlergia(multiWrapper.getParameter(nombreParametro));
                    alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergiaPolen0"));
                    alergiasPaciente.setDatosClinicos(datosClinicos);
                    alergiaDAO.save(alergiasPaciente);
                    System.out.println("Parametro Alergias: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro Especificar: " + multiWrapper.getParameter("especificarAlergiaPolen0"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("acaros") && (multiWrapper.getParameter("especificarAlergiaAcaros1") != null)){
                    alergiasPaciente.setTipoAlergia(multiWrapper.getParameter(nombreParametro));
                    alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergiaAcaros1"));
                    alergiasPaciente.setDatosClinicos(datosClinicos);
                    alergiaDAO.save(alergiasPaciente);
                    System.out.println("Parametro Alergias: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro Especificar: " + multiWrapper.getParameter("especificarAlergiaAcaros1"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("animales") && (multiWrapper.getParameter("especificarAlergiaAnimales2") != null)){
                    alergiasPaciente.setTipoAlergia(multiWrapper.getParameter(nombreParametro));
                    alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergiaAnimales2"));
                    alergiasPaciente.setDatosClinicos(datosClinicos);
                    alergiaDAO.save(alergiasPaciente);
                    System.out.println("Parametro Alergias: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro Especificar: " + multiWrapper.getParameter("especificarAlergiaAnimales2"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("medicamentos") && (multiWrapper.getParameter("especificarAlergiaMedicamentos3") != null)){
                    alergiasPaciente.setTipoAlergia(multiWrapper.getParameter(nombreParametro));
                    alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergiaMedicamentos3"));
                    alergiasPaciente.setDatosClinicos(datosClinicos);
                    alergiaDAO.save(alergiasPaciente);
                    System.out.println("Parametro Alergias: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro Especificar: " + multiWrapper.getParameter("especificarAlergiaMedicamentos3"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("insectos") && (multiWrapper.getParameter("especificarAlergiaInsectos4") != null)){
                    alergiasPaciente.setTipoAlergia(multiWrapper.getParameter(nombreParametro));
                    alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergiaInsectos4"));
                    alergiasPaciente.setDatosClinicos(datosClinicos);
                    alergiaDAO.save(alergiasPaciente);
                    System.out.println("Parametro Alergias: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro Especificar: " + multiWrapper.getParameter("especificarAlergiaInsectos4"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("alimentos") && (multiWrapper.getParameter("especificarAlergiaAlimentos5") != null)){
                    alergiasPaciente.setTipoAlergia(multiWrapper.getParameter(nombreParametro));
                    alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergiaAlimentos5"));
                    alergiasPaciente.setDatosClinicos(datosClinicos);
                    alergiaDAO.save(alergiasPaciente);
                    System.out.println("Parametro Alergias: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro Especificar: " + multiWrapper.getParameter("especificarAlergiaAlimentos5"));
                }
                
                
                
            }
            /*********************************************Cirugias************************************************************/
            if (nombreParametro.startsWith("checkboxCirugia") || nombreParametro.startsWith("noCirugia")) {
                
                if(multiWrapper.getParameter(nombreParametro).equals("interna") && (multiWrapper.getParameter("noCirugiaInterna") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaInterna")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaInterna"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("externa") && (multiWrapper.getParameter("noCirugiaExterna") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaExterna")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaExterna"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("mayor") && (multiWrapper.getParameter("noCirugiaMayor") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaMayor")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaMayor"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("menor") && (multiWrapper.getParameter("noCirugiaMenor") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaMenor")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaMenor"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("curativa") && (multiWrapper.getParameter("noCirugiaCurativa") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaCurativa")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaCurativa"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("reparadora") && (multiWrapper.getParameter("noCirugiaReparadora") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaReparadora")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaReparadora"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("paliativa") && (multiWrapper.getParameter("noCirugiaPaliativa") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaPaliativa")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaPaliativa"));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("cosmetica") && (multiWrapper.getParameter("noCirugiaCosmetica") != null)){
                    cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter(nombreParametro));
                    cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugiaCosmetica")));
                    cirugiasPaciente.setDatosClinicos(datosClinicos);
                    cirugiaDAO.save(cirugiasPaciente);
                    System.out.println("Parametro Cirugia: " + multiWrapper.getParameter(nombreParametro));
                    System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugiaCosmetica"));
                }
                
                
                
            }
            /*********************************************Discapacidades************************************************************/
            if (nombreParametro.startsWith("checkboxDiscapacidad")) {
                
                if(multiWrapper.getParameter(nombreParametro).equals("fisica")){
                    discapacidadPaciente.setTipo(multiWrapper.getParameter(nombreParametro));
                    discapacidadPaciente.setDatosClinicos(datosClinicos);
                    discapacidadDAO.save(discapacidadPaciente);
                    System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter(nombreParametro));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("intelectual") ){
                    discapacidadPaciente.setTipo(multiWrapper.getParameter(nombreParametro));
                    discapacidadPaciente.setDatosClinicos(datosClinicos);
                    discapacidadDAO.save(discapacidadPaciente);
                    System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter(nombreParametro));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("psiquica") ){
                    discapacidadPaciente.setTipo(multiWrapper.getParameter(nombreParametro));
                    discapacidadPaciente.setDatosClinicos(datosClinicos);
                    discapacidadDAO.save(discapacidadPaciente);
                    System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter(nombreParametro));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("visual")){
                    discapacidadPaciente.setTipo(multiWrapper.getParameter(nombreParametro));
                    discapacidadPaciente.setDatosClinicos(datosClinicos);
                    discapacidadDAO.save(discapacidadPaciente);
                    System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter(nombreParametro));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("auditiva")){
                    discapacidadPaciente.setTipo(multiWrapper.getParameter(nombreParametro));
                    discapacidadPaciente.setDatosClinicos(datosClinicos);
                    discapacidadDAO.save(discapacidadPaciente);
                    System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter(nombreParametro));
                }
                
                if(multiWrapper.getParameter(nombreParametro).equals("habla")){
                    discapacidadPaciente.setTipo(multiWrapper.getParameter(nombreParametro));
                    discapacidadPaciente.setDatosClinicos(datosClinicos);
                    discapacidadDAO.save(discapacidadPaciente);
                    System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter(nombreParametro));
                }
                              
                
            }
            
            
//            
            
//            alergiasPaciente.setDatosClinicos(datosClinicos);
//            
//            alergiaDAO.save(alergiasPaciente);
        }

        
        
       
        
        /////
        
        
//        if(checkboxAlergiaPolen != null && especificarAlergiaPolen != null) {
//            alergiasPaciente.setTipoAlergia(checkboxAlergiaPolen);
//            alergiasPaciente.setEspecificacion(especificarAlergiaPolen);
//        }
//        
//        if(checkboxAlergiaAcaros != null && especificarAlergiaAcaros != null) {
//            alergiasPaciente.setTipoAlergia(checkboxAlergiaAcaros);
//            alergiasPaciente.setEspecificacion(especificarAlergiaAcaros);
//        }
//        
//        if(checkboxAlergiaAnimales != null && especificarAlergiaAnimales != null) {
//            alergiasPaciente.setTipoAlergia(checkboxAlergiaAnimales);
//            alergiasPaciente.setEspecificacion(especificarAlergiaAnimales);
//        }
//        
//        if(checkboxAlergiaMedicamentos != null && especificarAlergiaMedicamentos != null) {
//            alergiasPaciente.setTipoAlergia(checkboxAlergiaMedicamentos);
//            alergiasPaciente.setEspecificacion(especificarAlergiaMedicamentos);
//        }
//        
//        if(checkboxAlergiaInsectos != null && especificarAlergiaInsectos != null) {
//            alergiasPaciente.setTipoAlergia(checkboxAlergiaInsectos);
//            alergiasPaciente.setEspecificacion(especificarAlergiaInsectos);
//        }
//
//        if(checkboxAlergiaAlimentos != null && especificarAlergiaAlimentos != null) {
//            alergiasPaciente.setTipoAlergia(checkboxAlergiaAlimentos);
//            alergiasPaciente.setEspecificacion(especificarAlergiaAlimentos);
//        }     
        
        
//        parametros = multiWrapper.getParameterNames();
//        while (parametros.hasMoreElements()) {
//            String nombreParametro = parametros.nextElement();
//
//            if (nombreParametro.startsWith("numTelefono")) {
//                System.out.println("Parametro Telefono Paciente: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("medicamento")) {
//                 System.out.println("Parametro Medicamento: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("frecuencia")) {
//                 System.out.println("Parametro Frecuencia: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("enfermedadCronica")) {
//                 System.out.println("Parametro enfermedadCronica: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("tipoEnfermedad")) {
//                 System.out.println("Parametro tipoEnfermedad: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("inicioEnfermedad")) {
//                 System.out.println("Parametro inicioEnfermedad: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//            ///////////////////
//            if (nombreParametro.startsWith("nombreContacto")) {
//                 System.out.println("Parametro nombreContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("apellidoPaternoContacto")) {
//                 System.out.println("Parametro apellidoPaternoContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("apellidoMaternoContacto")) {
//                 System.out.println("Parametro apellidoMaternoContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("parentescoContacto")) {
//                 System.out.println("Parametro parentescoContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("celularContacto")) {
//                 System.out.println("Parametro celularContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("facebookContacto")) {
//                 System.out.println("Parametro facebookContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }
//
//            if (nombreParametro.startsWith("correoContacto")) {
//                 System.out.println("Parametro correoContacto: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
//            }           
//        }

        return "pantallaAltaPaciente";
    }
    
    public String validarNombreUsuarioPaciente() {
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
    
    public String recuperarHospitales() {
        System.out.println("--->Entro a recuperarHospitales");
        String html = 
                        "<label>Hospital</label>"
                        + "<select name=\"codigoHospital\" class=\"form-control\">" +
                            "<option value=\"-1\">Seleccionar</option>";
        HospitalDAO hospitalDAO = new HospitalDAO();
        ArrayList<Hospitales> hospitales = (ArrayList<Hospitales>) hospitalDAO.findAll();

        if (hospitales == null) {
            return SUCCESS;
        }
        int contHosp = 0;
        for (Hospitales hospitalTemp : hospitales) {
            html += "<option value=\""+hospitalTemp.getCodigoHospital()+"\">"+hospitalTemp.getNombre()+"</option>";
            contHosp++;
        }
        
        html += "</select>";

        htmlHospitales = html;

        return SUCCESS;
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

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
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

    public String getNumerosTelefonicos() {
        return numerosTelefonicos;
    }

    public void setNumerosTelefonicos(String numerosTelefonicos) {
        this.numerosTelefonicos = numerosTelefonicos;
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

    public String isDrogas() {
        return drogas;
    }

    public void setDrogas(String drogas) {
        this.drogas = drogas;
    }

    public String isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String isFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    public String getCheckboxAlergiaPolen() {
        return checkboxAlergiaPolen;
    }

    public void setCheckboxAlergiaPolen(String checkboxAlergiaPolen) {
        this.checkboxAlergiaPolen = checkboxAlergiaPolen;
    }

    public String getEspecificarAlergiaPolen() {
        return especificarAlergiaPolen;
    }

    public void setEspecificarAlergiaPolen(String especificarAlergiaPolen) {
        this.especificarAlergiaPolen = especificarAlergiaPolen;
    }

    public String getCheckboxAlergiaAcaros() {
        return checkboxAlergiaAcaros;
    }

    public void setCheckboxAlergiaAcaros(String checkboxAlergiaAcaros) {
        this.checkboxAlergiaAcaros = checkboxAlergiaAcaros;
    }

    public String getEspecificarAlergiaAcaros() {
        return especificarAlergiaAcaros;
    }

    public void setEspecificarAlergiaAcaros(String especificarAlergiaAcaros) {
        this.especificarAlergiaAcaros = especificarAlergiaAcaros;
    }

    public String getCheckboxAlergiaAnimales() {
        return checkboxAlergiaAnimales;
    }

    public void setCheckboxAlergiaAnimales(String checkboxAlergiaAnimales) {
        this.checkboxAlergiaAnimales = checkboxAlergiaAnimales;
    }

    public String getEspecificarAlergiaAnimales() {
        return especificarAlergiaAnimales;
    }

    public void setEspecificarAlergiaAnimales(String especificarAlergiaAnimales) {
        this.especificarAlergiaAnimales = especificarAlergiaAnimales;
    }

    public String getCheckboxAlergiaMedicamentos() {
        return checkboxAlergiaMedicamentos;
    }

    public void setCheckboxAlergiaMedicamentos(String checkboxAlergiaMedicamentos) {
        this.checkboxAlergiaMedicamentos = checkboxAlergiaMedicamentos;
    }

    public String getEspecificarAlergiaMedicamentos() {
        return especificarAlergiaMedicamentos;
    }

    public void setEspecificarAlergiaMedicamentos(String especificarAlergiaMedicamentos) {
        this.especificarAlergiaMedicamentos = especificarAlergiaMedicamentos;
    }

    public String getCheckboxAlergiaInsectos() {
        return checkboxAlergiaInsectos;
    }

    public void setCheckboxAlergiaInsectos(String checkboxAlergiaInsectos) {
        this.checkboxAlergiaInsectos = checkboxAlergiaInsectos;
    }

    public String getEspecificarAlergiaInsectos() {
        return especificarAlergiaInsectos;
    }

    public void setEspecificarAlergiaInsectos(String especificarAlergiaInsectos) {
        this.especificarAlergiaInsectos = especificarAlergiaInsectos;
    }

    public String getCheckboxAlergiaAlimentos() {
        return checkboxAlergiaAlimentos;
    }

    public void setCheckboxAlergiaAlimentos(String checkboxAlergiaAlimentos) {
        this.checkboxAlergiaAlimentos = checkboxAlergiaAlimentos;
    }

    public String getEspecificarAlergiaAlimentos() {
        return especificarAlergiaAlimentos;
    }

    public void setEspecificarAlergiaAlimentos(String especificarAlergiaAlimentos) {
        this.especificarAlergiaAlimentos = especificarAlergiaAlimentos;
    }

    public String getCheckboxInterna() {
        return checkboxInterna;
    }

    public void setCheckboxInterna(String checkboxInterna) {
        this.checkboxInterna = checkboxInterna;
    }

    public String getNoCirugiaInterna() {
        return noCirugiaInterna;
    }

    public void setNoCirugiaInterna(String noCirugiaInterna) {
        this.noCirugiaInterna = noCirugiaInterna;
    }

    public String getCheckboxExterna() {
        return checkboxExterna;
    }

    public void setCheckboxExterna(String checkboxExterna) {
        this.checkboxExterna = checkboxExterna;
    }

    public String getNoCirugiaExterna() {
        return noCirugiaExterna;
    }

    public void setNoCirugiaExterna(String noCirugiaExterna) {
        this.noCirugiaExterna = noCirugiaExterna;
    }

    public String getCheckboxMayor() {
        return checkboxMayor;
    }

    public void setCheckboxMayor(String checkboxMayor) {
        this.checkboxMayor = checkboxMayor;
    }

    public String getNoCirugiaMayor() {
        return noCirugiaMayor;
    }

    public void setNoCirugiaMayor(String noCirugiaMayor) {
        this.noCirugiaMayor = noCirugiaMayor;
    }

    public String getCheckboxMenor() {
        return checkboxMenor;
    }

    public void setCheckboxMenor(String checkboxMenor) {
        this.checkboxMenor = checkboxMenor;
    }

    public String getNoCirugiaMenor() {
        return noCirugiaMenor;
    }

    public void setNoCirugiaMenor(String noCirugiaMenor) {
        this.noCirugiaMenor = noCirugiaMenor;
    }

    public String getCheckboxCurativa() {
        return checkboxCurativa;
    }

    public void setCheckboxCurativa(String checkboxCurativa) {
        this.checkboxCurativa = checkboxCurativa;
    }

    public String getNoCirugiaCurativa() {
        return noCirugiaCurativa;
    }

    public void setNoCirugiaCurativa(String noCirugiaCurativa) {
        this.noCirugiaCurativa = noCirugiaCurativa;
    }

    public String getCheckboxReparadora() {
        return checkboxReparadora;
    }

    public void setCheckboxReparadora(String checkboxReparadora) {
        this.checkboxReparadora = checkboxReparadora;
    }

    public String getNoCirugiaReparadora() {
        return noCirugiaReparadora;
    }

    public void setNoCirugiaReparadora(String noCirugiaReparadora) {
        this.noCirugiaReparadora = noCirugiaReparadora;
    }

    public String getCheckboxPaliativa() {
        return checkboxPaliativa;
    }

    public void setCheckboxPaliativa(String checkboxPaliativa) {
        this.checkboxPaliativa = checkboxPaliativa;
    }

    public String getNoCirugiaPaliativa() {
        return noCirugiaPaliativa;
    }

    public void setNoCirugiaPaliativa(String noCirugiaPaliativa) {
        this.noCirugiaPaliativa = noCirugiaPaliativa;
    }

    public String getCheckboxCosmetica() {
        return checkboxCosmetica;
    }

    public void setCheckboxCosmetica(String checkboxCosmetica) {
        this.checkboxCosmetica = checkboxCosmetica;
    }

    public String getNoCirugiaCosmetica() {
        return noCirugiaCosmetica;
    }

    public void setNoCirugiaCosmetica(String noCirugiaCosmetica) {
        this.noCirugiaCosmetica = noCirugiaCosmetica;
    }

    public String getCheckboxFisica() {
        return checkboxFisica;
    }

    public void setCheckboxFisica(String checkboxFisica) {
        this.checkboxFisica = checkboxFisica;
    }

    public String getCheckboxIntelectual() {
        return checkboxIntelectual;
    }

    public void setCheckboxIntelectual(String checkboxIntelectual) {
        this.checkboxIntelectual = checkboxIntelectual;
    }

    public String getCheckboxPsiquica() {
        return checkboxPsiquica;
    }

    public void setCheckboxPsiquica(String checkboxPsiquica) {
        this.checkboxPsiquica = checkboxPsiquica;
    }

    public String getCheckboxVisual() {
        return checkboxVisual;
    }

    public void setCheckboxVisual(String checkboxVisual) {
        this.checkboxVisual = checkboxVisual;
    }

    public String getCheckboxAuditiva() {
        return checkboxAuditiva;
    }

    public void setCheckboxAuditiva(String checkboxAuditiva) {
        this.checkboxAuditiva = checkboxAuditiva;
    }

    public String getCheckboxHabla() {
        return checkboxHabla;
    }

    public void setCheckboxHabla(String checkboxHabla) {
        this.checkboxHabla = checkboxHabla;
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

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getHtmlHospitales() {
        return htmlHospitales;
    }

    public void setHtmlHospitales(String htmlHospitales) {
        this.htmlHospitales = htmlHospitales;
    }

    public String getCodigoHospital() {
        return codigoHospital;
    }

    public void setCodigoHospital(String codigoHospital) {
        this.codigoHospital = codigoHospital;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
            this.servletRequest = servletRequest;
    }
}