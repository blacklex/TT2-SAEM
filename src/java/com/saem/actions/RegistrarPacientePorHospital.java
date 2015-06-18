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
import com.hibernate.model.Hospitales;
import com.hibernate.model.Medicacion;
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
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;

/**
 *
 * @author gabriela
 */
public class RegistrarPacientePorHospital implements SessionAware, ServletRequestAware {

    private Map<String, Object> session = null;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
    private final DomicilioPacienteDAO domicilioPacienteDAO = new DomicilioPacienteDAO();
    private final TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private final ContactoDAO contactoDAO = new ContactoDAO();
    private final DatosClinicosDAO datosClinicosDAO = new DatosClinicosDAO();
    private final DiscapacidadDAO discapacidadDAO = new DiscapacidadDAO();
    private final AlergiaDAO alergiaDAO = new AlergiaDAO();
    private final CirugiaDAO cirugiaDAO = new CirugiaDAO();
    private final EnfermedadCronicaDAO enfermedadCronicaDAO = new EnfermedadCronicaDAO();
    private final MedicacionDAO medicacionDAO = new MedicacionDAO();

    Usuarios userHospital = new Usuarios();

    private final HospitalDAO hospitalDAO = new HospitalDAO();

    private HttpServletRequest servletRequest;

    private List<Usuarios> listUsuarios;

    String nombreHospital;
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

    public String registrarPacientePorHospital() throws ParseException, FileNotFoundException, IOException {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
        Enumeration<String> parametros;
        Boolean registroCorrecto = false;
        Usuarios userPaciente = new Usuarios();
        Pacientes paciente = new Pacientes();
        DatosPersonales datosPersonales = new DatosPersonales();
        DomicilioPacientes domicilioPaciente = new DomicilioPacientes();
        TelefonosPacientes telefonoPaciente = new TelefonosPacientes();
        Contactos contacto = new Contactos();
        DatosClinicos datosClinicos = new DatosClinicos();
        Discapacidades discapacidadPaciente = new Discapacidades();
        Hospitales hospital = new Hospitales();
        Alergias alergiasPaciente = new Alergias();
        Cirugias cirugiasPaciente = new Cirugias();
        EnfermedadesCronicas enfermedadCronica = new EnfermedadesCronicas();
        Medicacion medicacionPaciente = new Medicacion();

        //Fecha de Registro
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);

        //Generamos el codigo de Historial Clinico
        Calendar cal = Calendar.getInstance();
        String codigoClinico = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DAY_OF_MONTH) + "" + cal.get(Calendar.HOUR) + "" + cal.get(Calendar.MINUTE) + "" + cal.get(Calendar.SECOND) + "" + cal.get(Calendar.MILLISECOND);

        listUsuarios = usuarioDAO.listarById(s, nombreHospital);
        for (Iterator iterator1 = listUsuarios.iterator(); iterator1.hasNext();) {
            userHospital = (Usuarios) iterator1.next();
            Set hospitales = userHospital.getHospitaleses();
            for (Iterator iterator2 = hospitales.iterator(); iterator2.hasNext();) {
                hospital = (Hospitales) iterator2.next();
                codigoHospital = hospital.getCodigoHospital();
            }
        }

        //Buscamos el hospital que se encarga del paciente
        hospital = hospitalDAO.findById(s, codigoHospital);

        //Establecemos los datos de acceso para el Paciente------> Tabla Usuario
        userPaciente = new Usuarios(nombreUsuario, "Paciente", clave, date);

        //Guardamos los datos de acceso del paciente
        if (usuarioDAO.save(userPaciente)) {
            //Establecemos los datos para el Paciente----->Tabla de Pacientes
            paciente.setNss(nss);
            paciente.setNombre(nombre);
            paciente.setApellidoPaterno(apellidoPaterno);
            paciente.setApellidoMaterno(apellidoMaterno);
            paciente.setUnidadMedica(unidadMedica);
            paciente.setNoConsultorio(noConsultorio);
            //Establecemos la clave foranea del Paciente haciendo referencia al hospital
            paciente.setHospitales(hospital);//----->Llave foranea del Hospital

            //Convertimos la imagen a un arreglo de
            if (imagen != null) {
                System.out.println("Camino absoluto    " + imagen.getAbsolutePath());
                byte[] bFile = new byte[(int) imagen.length()];
                try (FileInputStream fileInputStream = new FileInputStream(imagen)) {
                    fileInputStream.read(bFile);
                }
                paciente.setImagen(bFile);
            } else {
                String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                File fileImg = new File(filePath + "imagenesPerfilPaciente/default/default.jpeg");
                byte[] defaultFile = new byte[(int) fileImg.length()];
                try (FileInputStream imgDefault = new FileInputStream(fileImg)) {
                    imgDefault.read(defaultFile);
                }
                paciente.setImagen(defaultFile);
            }

            //Establecemos la clave foranea del Paciente con referencia a Usuarios
            paciente.setUsuarios(userPaciente);//----->Llave foranea del Paciente

            if (pacienteDAO.save(paciente)) {
                //Establecemos los datos personales para el Paciente----->Tabla de Datos Personales
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

                datosPersonales.setPacientes(paciente);//----->Llave foranea del Paciente

                //Guardamos los datos personales del paciente
                datosPersonalesDAO.save(datosPersonales);

                //Establecemos los datos de dirección para el Paciente----->Tabla de Domicilio de Pacientes
                domicilioPaciente.setCalle(calle);
                domicilioPaciente.setColonia(colonia);
                domicilioPaciente.setDelegacion(delegacion);
                domicilioPaciente.setEntidadFederativa(entidadFederativa);
                domicilioPaciente.setCodigoPostal(codigoPostal);

                domicilioPaciente.setPacientes(paciente);//----->Llave foranea del Paciente

                //Guardamos los datos de domicilio del paciente
                domicilioPacienteDAO.save(domicilioPaciente);

                //Establecemos los datos clinicos para el paciente----->Tabla de Datos Clinicos
                datosClinicos.setNoHistorial(Long.parseLong(codigoClinico));

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

                //Guardamos los datos clinicos del paciente
                if (datosClinicosDAO.save(datosClinicos)) {
                    parametros = multiWrapper.getParameterNames();
                    while (parametros.hasMoreElements()) {

                        String nombreParametro = parametros.nextElement();

                        /**
                         * *******************************************Alergias***************************************************************
                         */
                        if (nombreParametro.startsWith("checkboxAlergia")) {
                            String parametroAlergia = nombreParametro.substring(15);
                            alergiasPaciente.setTipoAlergia(multiWrapper.getParameter("checkboxAlergia" + parametroAlergia));
                            alergiasPaciente.setEspecificacion(multiWrapper.getParameter("especificarAlergia" + parametroAlergia));
                            alergiasPaciente.setDatosClinicos(datosClinicos);
                            alergiaDAO.save(alergiasPaciente);

                            System.out.println("Parametro Alergia: " + multiWrapper.getParameter("checkboxAlergia" + parametroAlergia));
                            System.out.println("Parametro Especificacion: " + multiWrapper.getParameter("especificarAlergia" + parametroAlergia));
                        }

                        /**
                         * *******************************************Cirugias****************************************************************
                         */
                        if (nombreParametro.startsWith("checkboxCirugia")) {
                            String parametroCirugia = nombreParametro.substring(15);
                            cirugiasPaciente.setTipoCirugia(multiWrapper.getParameter("checkboxCirugia" + parametroCirugia));
                            cirugiasPaciente.setNoCirugia(Integer.parseInt(multiWrapper.getParameter("noCirugia" + parametroCirugia)));
                            cirugiasPaciente.setDatosClinicos(datosClinicos);
                            cirugiaDAO.save(cirugiasPaciente);
                            System.out.println("Parametro Cirugia: " + multiWrapper.getParameter("checkboxCirugia" + parametroCirugia));
                            System.out.println("Parametro No. Cirugias: " + multiWrapper.getParameter("noCirugia" + parametroCirugia));
                        }

                        /**
                         * *******************************************Discapacidades***********************************************************
                         */
                        if (nombreParametro.startsWith("checkboxDiscapacidad")) {
                            String parametroDiscapacidad = nombreParametro.substring(20);
                            discapacidadPaciente.setTipo(multiWrapper.getParameter("checkboxDiscapacidad" + parametroDiscapacidad));
                            discapacidadPaciente.setDatosClinicos(datosClinicos);
                            discapacidadDAO.save(discapacidadPaciente);
                            System.out.println("Parametro Discapacidad: " + multiWrapper.getParameter("checkboxDiscapacidad" + parametroDiscapacidad));
                        }

                        /**
                         * *******************************************Telefonos
                         * de paciente***************************************************
                         */
                        if (nombreParametro.startsWith("numTelefono")) {
                            telefonoPaciente.setNumeroTelefono(multiWrapper.getParameter(nombreParametro));
                            telefonoPaciente.setPacientes(paciente);
                            telefonoPacienteDAO.save(telefonoPaciente);
                            System.out.println("Parametro No. Telefono: " + multiWrapper.getParameter(nombreParametro));

                        }

                        /**
                         * *******************************************Medicamentos************************************************************
                         */
                        if (nombreParametro.startsWith("medicamento")) {
                            String parametroMedicamento = nombreParametro.substring(11);
                            medicacionPaciente.setNombreMedicamento(multiWrapper.getParameter("medicamento" + parametroMedicamento));
                            medicacionPaciente.setFrecuencia(multiWrapper.getParameter("frecuencia" + parametroMedicamento));
                            medicacionPaciente.setDatosClinicos(datosClinicos);
                            if (!medicacionDAO.save(medicacionPaciente)) {
                                medicacionDAO.delete(medicacionPaciente);
                            }
                            System.out.println("Parametro Medicamento: " + multiWrapper.getParameter("medicamento" + parametroMedicamento));
                            System.out.println("Parametro Frecuencia: " + multiWrapper.getParameter("frecuencia" + parametroMedicamento));
                        }
                        /**
                         * *******************************************Enfermedad Cronica*******************************************************
                         */
                        if (nombreParametro.startsWith("enfermedadCronica")) {
                            String parametroEnfermedadCronica = nombreParametro.substring(17);
                            enfermedadCronica.setNombre(multiWrapper.getParameter("enfermedadCronica" + parametroEnfermedadCronica));
                            enfermedadCronica.setTipo(multiWrapper.getParameter("tipoEnfermedad" + parametroEnfermedadCronica));
                            String inicioEnfermedad = multiWrapper.getParameter("inicioEnfermedad" + parametroEnfermedadCronica);
                            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                            Date dateInicioEnfermedad = null;
                            String html = "data-inputmask=\"'alias': 'dd/mm/yyyy'\"";
                            dateInicioEnfermedad = formatoDeFecha.parse(inicioEnfermedad);
                            enfermedadCronica.setIncioEnfermedad(dateInicioEnfermedad);
                            enfermedadCronica.setDatosClinicos(datosClinicos);
                            if (!enfermedadCronicaDAO.save(enfermedadCronica)) {
                                enfermedadCronicaDAO.delete(enfermedadCronica);
                            }
                            System.out.println("Parametro Enfermedad: " + multiWrapper.getParameter("enfermedadCronica" + parametroEnfermedadCronica));
                            System.out.println("Parametro Tipo: " + multiWrapper.getParameter("tipoEnfermedad" + parametroEnfermedadCronica));
                            System.out.println("Parametro Inicioenfermedad: " + dateInicioEnfermedad);
                        }

                        /**
                         * *******************************************Enfermedad Cronica*******************************************************
                         */
                        if (nombreParametro.startsWith("nombreContacto")) {
                            String parametroContacto = nombreParametro.substring(14);
                            contacto.setNombre(multiWrapper.getParameter("nombreContacto" + parametroContacto));
                            contacto.setApellidoPaterno(multiWrapper.getParameter("apellidoPaternoContacto" + parametroContacto));
                            contacto.setApellidoMaterno(multiWrapper.getParameter("apellidoMaternoContacto" + parametroContacto));
                            contacto.setParentesco(multiWrapper.getParameter("parentescoContacto" + parametroContacto));
                            contacto.setCelular(multiWrapper.getParameter("celularContacto" + parametroContacto));
                            contacto.setFacebook(multiWrapper.getParameter("facebookContacto" + parametroContacto));
                            contacto.setCorreo(multiWrapper.getParameter("correoContacto" + parametroContacto));
                            contacto.setPacientes(paciente);
                            if (!contactoDAO.save(contacto)) {
                                contactoDAO.delete(contacto);
                            }
                            System.out.println(parametroContacto);
                            System.out.println("Parametro Nommbre: " + multiWrapper.getParameter("nombreContacto" + parametroContacto));
                            System.out.println("Parametro ApellidoP: " + multiWrapper.getParameter("apellidoPaternoContacto" + parametroContacto));
                            System.out.println("Parametro ApellidoM: " + multiWrapper.getParameter("apellidoMaternoContacto" + parametroContacto));
                            System.out.println("Parametro Parentesco: " + multiWrapper.getParameter("parentescoContacto" + parametroContacto));
                            System.out.println("Parametro Celular: " + multiWrapper.getParameter("celularContacto" + parametroContacto));
                            System.out.println("Parametro Face: " + multiWrapper.getParameter("facebookContacto" + parametroContacto));
                            System.out.println("Parametro E-mail: " + multiWrapper.getParameter("correoContacto" + parametroContacto));

                        }
                    }
                    registroCorrecto = true;
                } else {
                    registroCorrecto = false;
                    usuarioDAO.delete(userPaciente);
                    pacienteDAO.delete(paciente);
                    datosPersonalesDAO.delete(datosPersonales);
                    domicilioPacienteDAO.delete(domicilioPaciente);
                    datosClinicosDAO.delete(datosClinicos);
                }
            } else {
                registroCorrecto = false;
                usuarioDAO.delete(userPaciente);
            }
        } else {
            registroCorrecto = false;
            mensajeError = "Error al registrar al Paciente.";
        }

        if (registroCorrecto) {
            session.put("tituloAlert", "Paciente Registrado");
            session.put("textoAlert", "El Paciente fue registrado exitosamente.");
            session.put("estatusMensaje", "success");

        } else if (!registroCorrecto) {
            session.put("tituloAlert", "Error al registrar Paciente.");
            session.put("textoAlert", mensajeError);
            session.put("estatusMensaje", "error");
        }
        s.close();
        return "pantallaAltaPacientePorHospital";
    }

    public String recuperarEstatusPaciente() {
        System.out.println("--->Entro a recuperarEstatusPaciente");
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

        return SUCCESS;
    }

    public String validarNombreUsuarioPaciente() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(s, nombreUsuario);

        if (usuarioResultado == null) {
            estatusMensaje = "nombreValido";
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(nombreUsuario)) {
            estatusMensaje = "nombreNoValido";
        } else {
            estatusMensaje = "nombreValido";
        }
        s.close();
        return SUCCESS;
    }

//    public String recuperarHospitales() {
//        System.out.println("--->Entro a recuperarHospitales");
//        String html = 
//                        "<label>Hospital</label>"
//                      + "<select name=\"codigoHospital\" class=\"form-control\">" +
//                            "<option value=\"-1\">Seleccionar</option>";
//        HospitalDAO hospitalDAO = new HospitalDAO();
//        ArrayList<Hospitales> hospitales = (ArrayList<Hospitales>) hospitalDAO.findAll();
//        if (hospitales == null) {
//            return SUCCESS;
//        }
//        int contHosp = 0;
//        for (Hospitales hospitalTemp : hospitales) {
//            html += "<option value=\""+hospitalTemp.getCodigoHospital()+"\">"+hospitalTemp.getNombre()+"</option>";
//            contHosp++;
//        }
//        html += "</select>";
//        htmlHospitales = html;
//        
//        return SUCCESS;
//    }
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

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

}
