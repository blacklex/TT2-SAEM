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
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author gabriela
 */
public class RegistrarPaciente implements SessionAware {
    
    private Map<String, Object> session = null;
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private final DomicilioPacienteDAO domicilioPacienteDAO = new DomicilioPacienteDAO();
    private final DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
    private final DatosClinicosDAO datosClinicosDAO = new DatosClinicosDAO();
    private final ContactoDAO contactoDAO = new ContactoDAO();
    private final UsuarioDAO usuariosDAO = new UsuarioDAO();

    HttpServletRequest request = ServletActionContext.getRequest();
//    MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
    
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
//    private String userImageContentType;
//    private String userImageFileName;
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
    boolean drogas;
    boolean alcohol;
    boolean fuma;
    //Alergias
    String checkboxPolen; 
    String especificarPolen;
    String checkboxAcaros;
    String especificarAcaros;
    String checkboxAnimales;
    String especificarAnimales;
    String checkboxMedicamentos;
    String especificarMedicamentos;
    String checkboxInsectos;
    String especificarInsectos;
    String checkboxAlimentos;
    String especificarAlimentos;
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
    //Medicamentos
//    String medicamentos;
//    String frecuencias;
    //Enfermedades cronicas
//    String enfermedadesCronicas;
//    String tiposEnfermedades;
//    String inicioEnfermedades;
    //Contacto
//    String nombresContactos;
//    String apellidosPaternosContactos;
//    String apellidosMaternosContactos;
//    String parentescos;
//    String celulares;
//    String facebookContactos;
//    String correosContactos;
    //campos json retorno
    private String tituloAlert;
    private String textoAlert;
    private String estatusMensaje;

    
    String mensajeError = "";
    
    public String registrarPaciente() throws ParseException, FileNotFoundException, IOException {
        MultiPartRequestWrapper multiWrapper =(MultiPartRequestWrapper) ServletActionContext.getRequest();
        Enumeration<String> parametros;
//        Boolean registroCorrecto = false;
//        Pacientes paciente = new Pacientes();
//        Usuarios userPaciente = new Usuarios();
//        TelefonosPacientes telefonoPaciente = new TelefonosPacientes();
//        DomicilioPacientes domicilioPaciente = new DomicilioPacientes();
//        DatosPersonales datosPersonales = new DatosPersonales();
//        DatosClinicos datosClinicos = new DatosClinicos();
//        Contactos contacto = new Contactos();
//        
//        Date date = new Date();
//        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String fechaRegistro = hourdateFormat.format(date);
//        date = hourdateFormat.parse(fechaRegistro);
      
        parametros = multiWrapper.getParameterNames();
//        ArrayList<TelefonosPacientes> listaEspec = new ArrayList<>();
        //numTelefono
         while (parametros.hasMoreElements()) {
            String nombreParametro = parametros.nextElement();
//            String[] contentType = multiWrapper.getContentTypes(nombreParametro);
//
//    if((contentType.length < 1) || (contentType[0] == null)){
//        contentType = new String[]{"text/plain"};
//    }

           if (nombreParametro.startsWith("numTelefono")) {
                System.out.println("Parametro: " + Arrays.toString(multiWrapper.getParameterValues(nombreParametro)));
           }
        }

        System.out.println();
        System.out.println();

        return "pantallaAltaPaciente";
    }
    
    public String validarNombreUsuarioPaciente() {
        Usuarios usuarioResultado;

        usuarioResultado = usuariosDAO.findById(nombreUsuario);

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

    public String getCheckboxPolen() {
        return checkboxPolen;
    }

    public void setCheckboxPolen(String checkboxPolen) {
        this.checkboxPolen = checkboxPolen;
    }

    public String getEspecificarPolen() {
        return especificarPolen;
    }

    public void setEspecificarPolen(String especificarPolen) {
        this.especificarPolen = especificarPolen;
    }

    public String getCheckboxAcaros() {
        return checkboxAcaros;
    }

    public void setCheckboxAcaros(String checkboxAcaros) {
        this.checkboxAcaros = checkboxAcaros;
    }

    public String getEspecificarAcaros() {
        return especificarAcaros;
    }

    public void setEspecificarAcaros(String especificarAcaros) {
        this.especificarAcaros = especificarAcaros;
    }

    public String getCheckboxAnimales() {
        return checkboxAnimales;
    }

    public void setCheckboxAnimales(String checkboxAnimales) {
        this.checkboxAnimales = checkboxAnimales;
    }

    public String getEspecificarAnimales() {
        return especificarAnimales;
    }

    public void setEspecificarAnimales(String especificarAnimales) {
        this.especificarAnimales = especificarAnimales;
    }

    public String getCheckboxMedicamentos() {
        return checkboxMedicamentos;
    }

    public void setCheckboxMedicamentos(String checkboxMedicamentos) {
        this.checkboxMedicamentos = checkboxMedicamentos;
    }

    public String getEspecificarMedicamentos() {
        return especificarMedicamentos;
    }

    public void setEspecificarMedicamentos(String especificarMedicamentos) {
        this.especificarMedicamentos = especificarMedicamentos;
    }

    public String getCheckboxInsectos() {
        return checkboxInsectos;
    }

    public void setCheckboxInsectos(String checkboxInsectos) {
        this.checkboxInsectos = checkboxInsectos;
    }

    public String getEspecificarInsectos() {
        return especificarInsectos;
    }

    public void setEspecificarInsectos(String especificarInsectos) {
        this.especificarInsectos = especificarInsectos;
    }

    public String getCheckboxAlimentos() {
        return checkboxAlimentos;
    }

    public void setCheckboxAlimentos(String checkboxAlimentos) {
        this.checkboxAlimentos = checkboxAlimentos;
    }

    public String getEspecificarAlimentos() {
        return especificarAlimentos;
    }

    public void setEspecificarAlimentos(String especificarAlimentos) {
        this.especificarAlimentos = especificarAlimentos;
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
    
    
}