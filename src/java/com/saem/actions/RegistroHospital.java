/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.DirectivoDAO;
import com.hibernate.dao.DomicilioHospitalDAO;
import com.hibernate.dao.EspecialidadDAO;
import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Directivo;
import com.hibernate.model.DomicilioHospitales;
import com.hibernate.model.Especialidades;
import com.hibernate.model.Hospitales;
import com.hibernate.model.Usuarios;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.SessionAware;
import com.persistencia.owl.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

/**
 *
 * @author Alejandro
 */
public class RegistroHospital implements SessionAware {

    HttpServletRequest request = ServletActionContext.getRequest();
    private Map<String, Object> session = null;
    private final HospitalDAO hospitalesDAO = new HospitalDAO();
    private final UsuarioDAO usuariosDAO = new UsuarioDAO();
    private final DirectivoDAO directivoDAO = new DirectivoDAO();
    private final DomicilioHospitalDAO domiciliosHospitalDAO = new DomicilioHospitalDAO();
    //campos del formulario
    String nombreUsuario;
    String claveUsuario;

    String nombreHospital;
    String telefonoHospital;
    String emailHospital;

    String latitudY;
    String longitudX;
    String calle;
    String numero;
    String colonia;
    String delegacion;
    String entidadFederativa;
    String codigoPostal;

    String telefonoDirectivo;
    String emailDirectivo;
    String nombreDirectivo;

    String htmlEspecialidades;
    //fin de campos formularo

    //campos json retorno
    String tituloAlert;
    String textoAlert;
    String estatusMensaje;

    public String execute() {
        return "pantallaRegistroHospital";
    }

    public String registrarHospital() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Enumeration<String> parametros;

        Boolean registroCorrecto = false;
        Hospitales hospital = new Hospitales();
        Directivo directivo = new Directivo();
        DomicilioHospitales domicilioHospital = new DomicilioHospitales();
        Usuarios usuario = new Usuarios();
        String lada;
        String mensajeError = "";
        lada = telefonoHospital.substring(1, 3) + telefonoHospital.substring(4, 6);
        telefonoHospital = telefonoHospital.substring(7, 12) + telefonoHospital.substring(13, telefonoHospital.length());
        telefonoDirectivo = telefonoDirectivo.substring(7, 12) + telefonoDirectivo.substring(13, telefonoDirectivo.length());

        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            date = format.parse(format.format(cal.getTime()));//format.parse("2012-12-13 14:54:30"); // mysql datetime format
        } catch (ParseException ex) {
            Logger.getLogger(RegistroHospital.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codigoHospital = cal.get(Calendar.YEAR) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DAY_OF_MONTH) + "" + cal.get(Calendar.HOUR) + "" + cal.get(Calendar.MINUTE) + "" + cal.get(Calendar.SECOND);
        System.out.println("-->T " + codigoHospital);

        /*if(true)
         return "pantallaRegistroHospital";*/
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setClave(claveUsuario);
        usuario.setFechaRegistro(date);
        usuario.setTipoUsuario("Hospital");
        if (usuariosDAO.save(usuario)) {

            hospital.setCodigoHospital(codigoHospital);
            hospital.setEMail(emailHospital);
            hospital.setNombre(nombreHospital);
            hospital.setTelefono(telefonoHospital);
            hospital.setLada(lada);
            hospital.setUsuarios(usuario);

            parametros = request.getParameterNames();
            ArrayList<Especialidades> listaEspec = new ArrayList<>();
            Set<Especialidades> setEspecialidadesHosp = new HashSet<Especialidades>();
            while (parametros.hasMoreElements()) {
                String nombreParametro = parametros.nextElement();

                if (nombreParametro.startsWith("checkbox")) {
                    Especialidades espcecTemp = new EspecialidadDAO().findById(s, Integer.parseInt(request.getParameter(nombreParametro)));
                    listaEspec.add(espcecTemp);
                    setEspecialidadesHosp.add(espcecTemp);
                }

            }
            hospital.setEspecialidadeses(setEspecialidadesHosp);
            
            if (hospitalesDAO.save(hospital)) {
                guardarEnOntologia(listaEspec);

                directivo.setCorreo(emailDirectivo);
                directivo.setNombre(nombreDirectivo);
                directivo.setTelParticular(telefonoDirectivo);
                directivo.setHospitales(hospital);

                if (directivoDAO.save(directivo)) {
                    domicilioHospital.setCalle(calle);
                    domicilioHospital.setCodigoPostal(codigoPostal);
                    domicilioHospital.setColonia(colonia);
                    domicilioHospital.setDelegacion(delegacion);
                    domicilioHospital.setEntidadFederativa(entidadFederativa);
                    domicilioHospital.setNumero(numero);
                    domicilioHospital.setHospitales(hospital);

                    if (domiciliosHospitalDAO.save(domicilioHospital)) {
                        registroCorrecto = true;

                    } else {
                        registroCorrecto = false;
                        directivoDAO.delete(directivo);
                        hospitalesDAO.delete(hospital);
                        usuariosDAO.delete(usuario);
                        System.out.println("-->Error al registrar el domicilio del hospital.");
                        mensajeError = "Error al registrar el domicilio del hospital.";
                    }
                } else {
                    registroCorrecto = false;
                    usuariosDAO.delete(usuario);
                    hospitalesDAO.delete(hospital);
                    System.out.println("-->Error al registrar el directivo.");
                    mensajeError = "Error al registrar el directivo.";
                }
            } else {
                registroCorrecto = false;
                usuariosDAO.delete(usuario);
                System.out.println("-->Error al registrar hospital.");
                mensajeError = "Error al registrar hospital.";
            }
        } else {
            registroCorrecto = false;
            System.out.println("-->Error al registrar el usuario.");
            mensajeError = "Error al registrar el usuario.";
        }

        if (registroCorrecto) {
            session.put("tituloAlert", "Hospital Registrado");
            session.put("textoAlert", "El Hospital fue registrado exitosamente.");
            session.put("estatusMensaje", "success");

        } else if (!registroCorrecto) {
            session.put("tituloAlert", "Error al registrar hospital.");
            session.put("textoAlert", mensajeError);
            session.put("estatusMensaje", "error");

        }
        s.close();
        return "pantallaRegistroHospital";
    }

    public String recuperarEstatus() {
        System.out.println("--->Entro a recuperarEstatus");
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

    public String recuperarEspecialidades() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        System.out.println("--->Entro a recuperarEspecialidades");
        String html = "";
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        ArrayList<Especialidades> especialidades = (ArrayList<Especialidades>) especialidadDAO.findAll(s);
        s.close();
        if (especialidades == null) {
            return SUCCESS;
        }
        int contEspec = 0;
        for (Especialidades especialidadTemp : especialidades) {
            html += "<div style=\"margin-bottom:10px;\"; class=\"input-group\">"
                    + "<span class=\"input-group-addon\">"
                    + "<input type=\"checkbox\" name=\"checkbox" + contEspec + "\" value=\"" + especialidadTemp.getNoEspecialidad() + "\">"
                    + "</span>"
                    + "<input disabled=\"true\" class=\"form-control\" type=\"text\" value=\"" + especialidadTemp.getNombreEspecialidad() + "\">"
                    + "</div><!-- /input-group -->";
            contEspec++;
        }

        htmlEspecialidades = html;

        return SUCCESS;
    }

    public String validarNombreUsuario() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        Usuarios usuarioResultado;

        usuarioResultado = usuariosDAO.findById(s, nombreUsuario);
        s.close();
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
     * ************************************************************************
     */
    private Boolean guardarEnOntologia(ArrayList<Especialidades> listaEspec) {

        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";

        OWLInsercionIndividuo insercionIndividuos = new OWLInsercionIndividuo(ONTOLOGIA, BASE_URI);
        String nombreHospitalConEspacios = nombreHospital;
        nombreHospital = nombreHospital.replaceAll("\\s+", "");

        insercionIndividuos.agregarHospital(nombreHospital);
        insercionIndividuos.agregarNombreHospital(nombreHospital, nombreHospitalConEspacios);

        insercionIndividuos.agregarDireccion("Direccion" + nombreHospital);
        insercionIndividuos.agregarCoordenadas("Direccion" + nombreHospital, longitudX, latitudY);

        //-----------------------------------------------------------------------------
        OWLInsercionRelacion insercionRelaciones = new OWLInsercionRelacion(ONTOLOGIA, BASE_URI);

        for (Especialidades especialidadTemp : listaEspec) {
            insercionRelaciones.agregarRelacionSeEspecializaEn(nombreHospital, especialidadTemp.getNombreEspecialidad());
        }

        insercionRelaciones.agregarRelacionSeUbicaEn(nombreHospital, "Direccion" + nombreHospital);
        //-----------------------------------------------------------------------
        OWLConsultas consultor = new OWLConsultas(ONTOLOGIA, BASE_URI);

        ArrayList<String> enfemedadesEspecialidades = new ArrayList<String>();

        for (Especialidades especialidadTemp : listaEspec) {
            ArrayList<String> enfemedadesEspcOnt = (ArrayList<String>) consultor.especialidadEstudiaAEnfermedad(especialidadTemp.getNombreEspecialidad());
            for (String enfermedadOnt : enfemedadesEspcOnt) {
                enfemedadesEspecialidades.add(enfermedadOnt);
            }
        }

        /*consultor.perteneceAClase(nombreHospital);
         consultor.hospitalseUbicaEnDireccion(nombreHospital);
         consultor.direccionSeUbicaUnHospital("Direccion" + nombreHospital);
         consultor.getCoordenadaXDireccion("Direccion" + nombreHospital);
         consultor.getCoordenadaYDireccion("Direccion" + nombreHospital);
         */
        //------------------------------------CREAR RELACION ENTRE HOSPITAL Y ENFERMEDADES----------------------------------------
        insercionRelaciones = new OWLInsercionRelacion(ONTOLOGIA, BASE_URI);

        for (String enfemedadInsertar : enfemedadesEspecialidades) {
            insercionRelaciones.agregarRelacionSeAtiende(nombreHospital, enfemedadInsertar);
        }

        return true;
    }

    /**
     * ****************************SETTER Y
     * GETTER**********************************
     */
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public String getLatitudY() {
        return latitudY;
    }

    public void setLatitudY(String latitudY) {
        this.latitudY = latitudY;
    }

    public String getLongitudX() {
        return longitudX;
    }

    public void setLongitudX(String longitudX) {
        this.longitudX = longitudX;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getTelefonoHospital() {
        return telefonoHospital;
    }

    public void setTelefonoHospital(String telefonoHospital) {
        this.telefonoHospital = telefonoHospital;
    }

    public String getEmailHospital() {
        return emailHospital;
    }

    public void setEmailHospital(String emailHospital) {
        this.emailHospital = emailHospital;
    }

    public String getTelefonoDirectivo() {
        return telefonoDirectivo;
    }

    public void setTelefonoDirectivo(String telefonoDirectivo) {
        this.telefonoDirectivo = telefonoDirectivo;
    }

    public String getEmailDirectivo() {
        return emailDirectivo;
    }

    public void setEmailDirectivo(String emailDirectivo) {
        this.emailDirectivo = emailDirectivo;
    }

    public String getNombreDirectivo() {
        return nombreDirectivo;
    }

    public void setNombreDirectivo(String nombreDirectivo) {
        this.nombreDirectivo = nombreDirectivo;
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

    public String getHtmlEspecialidades() {
        return htmlEspecialidades;
    }

    public void setHtmlEspecialidades(String htmlEspecialidades) {
        this.htmlEspecialidades = htmlEspecialidades;
    }

}
