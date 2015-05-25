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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author gabriela
 */
public class Registrarpaciente implements SessionAware, ServletRequestAware{
    
    private Map<String, Object> session = null;
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private final DomicilioPacienteDAO domicilioPacienteDAO = new DomicilioPacienteDAO();
    private final DatosPersonalesDAO datosPersonalesDAO = new DatosPersonalesDAO();
    private final DatosClinicosDAO datosClinicosDAO = new DatosClinicosDAO();
    private final ContactoDAO contactoDAO = new ContactoDAO();

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
    private String userImageContentType;
    private String userImageFileName;
    //Direccion
    String calle;
    String colonia;
    String delegacion;
    String entidadFederativa;
    String codigoPostal;
    //Telefonos
    String telefonoFijo;
    String telefonoParticular;
    //Datos Personales
    String estadoCivil;
    String curp;
    String sexo;
    Date fechaNacimiento;
    String edad;
    String peso;
    String altura;
    String talla;
    String telCasa;
    String telCel;
    String correo;
    String facebook;
    //Contacto
    String nombreC;
    String apellidoPaternoC;
    String apellidoMaternoC;
    String parentesco;
    String celular;
    String facebookC;
    String correoC;
    //Datos clinicos
    private boolean usoDrogas;
    private boolean usoAlcohol;
    private boolean fumador;
    //campos json retorno
    private String tituloAlert;
    private String textoAlert;
    private String estatusMensaje;
    Boolean r1 = false;
    Boolean r2 = false;
    Boolean r3 = false;
    Boolean r4 = false;
    Boolean r5 = false;
    Boolean r6 = false;
    Boolean r7 = false;
    
    String mensajeError = "";
    
    public String registrarPaciente() throws ParseException, FileNotFoundException, IOException {
        Boolean registroCorrecto = false;
        Pacientes paciente = new Pacientes();
        Usuarios userPaciente = new Usuarios();
        TelefonosPacientes telefonoPaciente = new TelefonosPacientes();
        DomicilioPacientes domicilioPaciente = new DomicilioPacientes();
        DatosPersonales datosPersonales = new DatosPersonales();
        DatosClinicos datosClinicos = new DatosClinicos();
        Contactos contacto = new Contactos();
        
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = hourdateFormat.format(date);
        date = hourdateFormat.parse(fechaRegistro);
        
        //Establecemos los datos de acceso para el Paciente
        userPaciente = new Usuarios(nombreUsuario, "Paciente", clave, date);
        
        //Establecemos los datos para el Pacientes
        paciente.setNombre(nombre);
        paciente.setApellidoPaterno(apellidoPaterno);
        paciente.setApellidoMaterno(apellidoMaterno);
        paciente.setUnidadMedica(unidadMedica);
        paciente.setNoConsultorio(noConsultorio);
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
            
        //Guardamos los datos de acceso del Paciente
        r1 = usuarioDAO.save(userPaciente);
        
        //Establecemos la clave foranea del Paciente
        paciente.setUsuarios(userPaciente);
        
        //Establecemos los datos de dirección para el Paciente
        domicilioPaciente.setCalle(calle);
        domicilioPaciente.setColonia(colonia);
        domicilioPaciente.setDelegacion(delegacion);
        domicilioPaciente.setEntidadFederativa(entidadFederativa);
        domicilioPaciente.setCodigoPostal(codigoPostal);
        
        //Guardamos los datos para el Paciente
        r2 = pacienteDAO.save(paciente);
        
        //Establecemos la clave foranea del domicilio del Paciente
        domicilioPaciente.setPacientes(paciente);
        
        //Guardamos los datos del domicilio para el Paciente
        r3 = domicilioPacienteDAO.save(domicilioPaciente);
        
        //Establecemos los telefonos del Paciente
        telefonoPaciente.setTelefonoFijo(telefonoFijo);
        telefonoPaciente.setTelefonoParticular(telefonoParticular);
        
        //Establecemos la clave foranea de telefonos del Paciente
        telefonoPaciente.setPacientes(paciente);
        
        //Guardamos los telefonos para el Paciente
        r4 = telefonoPacienteDAO.save(telefonoPaciente);
        
        //Establecemos los datos personales del Paciente
        datosPersonales.setEstadoCivil(estadoCivil);
        datosPersonales.setCurp(curp);
        datosPersonales.setSexo(sexo);
        datosPersonales.setFechaNacimiento(fechaNacimiento);
        datosPersonales.setEdad(edad);
        datosPersonales.setPeso(peso);
        datosPersonales.setAltura(altura);
        datosPersonales.setTalla(talla);
        datosPersonales.setTelCasa(telCasa);
        datosPersonales.setTelCel(telCel);
        datosPersonales.setCorreo(correo);
        datosPersonales.setFacebook(facebook);
        
        //Establecemos la clave foranea de datos personales del Paciente
        datosPersonales.setPacientes(paciente);
        
        //Guardamos los datos personales para el Paciente
        r5 = datosPersonalesDAO.save(datosPersonales);
        
        //Establecemos los datos de contacto del Paciente
        contacto.setNombreC(nombreC);
        contacto.setApellidoPaternoC(apellidoPaternoC);
        contacto.setApellidoMaternoC(apellidoMaternoC);
        contacto.setParentesco(parentesco);
        contacto.setCelular(celular);
        contacto.setFacebookC(facebookC);
        contacto.setCorreoC(correoC);
        
        //Establecemos la clave foranea del contacto del Paciente
        contacto.setPacientes(paciente);
        
        //Guardamos los datos del contacto para el Paciente
        r6 = contactoDAO.save(contacto);
        
        //Establecemos los datos clinicos del Paciente
        datosClinicos.setUsoDrogas(isUsoDrogas());
        datosClinicos.setUsoAlcohol(isUsoAlcohol());
        datosClinicos.setFumador(isFumador());
        
        //Establecemos la clave foranea de los datos clinicos del Paciente
        datosClinicos.setPacientes(paciente);
        
        //Guardamos los datos clinicos para el Paciente
        r7 = datosClinicosDAO.save(datosClinicos);
        
        if(r1 && r2 && r3 && r4 && r5 && r6 && r7)
            registroCorrecto = true;
        else {
            registroCorrecto = false;
            mensajeError = "Error al ingresar Paciente";
        }
        
        if (registroCorrecto) {
            session.put("tituloAlert", "Paciente Registrado");
            session.put("textoAlert", "El Paciente fue registrado exitosamente.");
            session.put("estatusMensaje", "success");

        } else if (!registroCorrecto) {
            session.put("tituloAlert", "Error al registrar paciente.");
            session.put("textoAlert", mensajeError);
            session.put("estatusMensaje", "error");
        }

        return "pantallaAltaPaciente";
    }
    
    public String recuperarEstatusPaciente() {

        setTituloAlert("");
        setTextoAlert("");
        setEstatusMensaje("");

        if (session.get("estatusMensaje") != null) {
            setTituloAlert(session.get("tituloAlert").toString());
            setTextoAlert(session.get("textoAlert").toString());
            setEstatusMensaje(session.get("estatusMensaje").toString());
        }

        session.remove("estatusMensaje");
        session.remove("tituloAlert");
        session.remove("estatusMensaje");
        session.put("estatusMensaje", null);

        return "success";
    }
    
    public String validarNombreUsuarioPaciente() {
        Usuarios usuarioResultado;

        usuarioResultado = usuarioDAO.findById(nombreUsuario);

        if (usuarioResultado == null) {
            setEstatusMensaje("nombreValido");
            return SUCCESS;
        }

        if (usuarioResultado.getNombreUsuario().equals(nombreUsuario)) {
            setEstatusMensaje("nombreNoValido");
        } else {
            setEstatusMensaje("nombreValido");
        }

        return SUCCESS;
    }
    
     public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return this.nss;
    }
    
    public void setNss(String nss) {
        this.nss = nss;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getUnidadMedica() {
        return this.unidadMedica;
    }
    
    public void setUnidadMedica(String unidadMedica) {
        this.unidadMedica = unidadMedica;
    }
    public String getNoConsultorio() {
        return this.noConsultorio;
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
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getColonia() {
        return this.colonia;
    }
    
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public String getDelegacion() {
        return this.delegacion;
    }
    
    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }
    public String getEntidadFederativa() {
        return this.entidadFederativa;
    }
    
    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }
    public String getCodigoPostal() {
        return this.codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    public String getTelefonoFijo() {
        return this.telefonoFijo;
    }
    
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }
    public String getTelefonoParticular() {
        return this.telefonoParticular;
    }
    
    public void setTelefonoParticular(String telefonoParticular) {
        this.telefonoParticular = telefonoParticular;
    }
    
    public String getEstadoCivil() {
        return this.estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getCurp() {
        return this.curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getEdad() {
        return this.edad;
    }
    
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getPeso() {
        return this.peso;
    }
    
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getAltura() {
        return this.altura;
    }
    
    public void setAltura(String altura) {
        this.altura = altura;
    }
    public String getTalla() {
        return this.talla;
    }
    
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public String getTelCasa() {
        return this.telCasa;
    }
    
    public void setTelCasa(String telCasa) {
        this.telCasa = telCasa;
    }
    public String getTelCel() {
        return this.telCel;
    }
    
    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getFacebook() {
        return this.facebook;
    }
    
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    
    public String getNombreC() {
        return this.nombreC;
    }
    
    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
    public String getApellidoPaternoC() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaternoC(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaternoC() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaternoC(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getParentesco() {
        return this.parentesco;
    }
    
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getFacebookC() {
        return this.facebook;
    }
    
    public void setFacebookC(String facebook) {
        this.facebook = facebook;
    }
    public String getCorreoC() {
        return this.correo;
    }
    
    public void setCorreoC(String correo) {
        this.correo = correo;
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
     * @return the tituloAlert
     */
    public String getTituloAlert() {
        return tituloAlert;
    }

    /**
     * @param tituloAlert the tituloAlert to set
     */
    public void setTituloAlert(String tituloAlert) {
        this.tituloAlert = tituloAlert;
    }

    /**
     * @return the textoAlert
     */
    public String getTextoAlert() {
        return textoAlert;
    }

    /**
     * @param textoAlert the textoAlert to set
     */
    public void setTextoAlert(String textoAlert) {
        this.textoAlert = textoAlert;
    }

    /**
     * @return the estatusMensaje
     */
    public String getEstatusMensaje() {
        return estatusMensaje;
    }

    /**
     * @param estatusMensaje the estatusMensaje to set
     */
    public void setEstatusMensaje(String estatusMensaje) {
        this.estatusMensaje = estatusMensaje;
    }
    
    
    
}
