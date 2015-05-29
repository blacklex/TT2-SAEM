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
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.persistencia.owl.OWLConsultas;
import com.persistencia.owl.OWLEliminarIndividuo;
import com.persistencia.owl.OWLInsercionIndividuo;
import com.persistencia.owl.OWLInsercionRelacion;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class ModificarEliminarHospital implements SessionAware {

    private static final String LISTA_HOSPITALES = "LISTA_HOSPITALES_ME";
    private static final String LLAVE_ESTATUS_ME = "MODIFICARELIMINARHOSPITAL_ESTATUS_LLAVE";

    private String codigoHospitalEditar;
    private String codigoHospital;
    //campos del formulario
    //String nombreUsuario;
    private String claveUsuario;

    private String nombreHospital;
    private String telefonoHospital;
    private String emailHospital;

    private String latitudY;
    private String longitudX;
    private String calle;
    private String numero;
    private String colonia;
    private String delegacion;
    private String entidadFederativa;
    private String codigoPostal;

    private String telefonoDirectivo;
    private String emailDirectivo;
    private String nombreDirectivo;
    //fin de campos formularo

    private String especialidades;

    private String tituloAlert = "";
    private String textoAlert = "";
    private String estatusMensaje = "";

    HttpServletRequest request = ServletActionContext.getRequest();
    private Map<String, Object> session = null;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute() {
        return "pantallaModifcarEliminarHospital";
    }

    public String modificarDatosSesionHospital() {
        Usuarios usuarioTemp = new HospitalDAO().findById(codigoHospitalEditar).getUsuarios();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuarios temp = new Usuarios();

        temp.setNombreUsuario(usuarioTemp.getNombreUsuario());
        temp.setTipoUsuario(usuarioTemp.getTipoUsuario());
        temp.setClave(claveUsuario);
        temp.setFechaRegistro(usuarioTemp.getFechaRegistro());

        if (usuarioDAO.update(temp)) {
            tituloAlert = "Datos Editados.";
            textoAlert = "Los datos de Sesion han sido satisfactoriamente.";
            estatusMensaje = "success";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);

        } else {
            tituloAlert = "Error Datos no Editados.";
            textoAlert = "Los Datos de Sesion no fueron editados.";
            estatusMensaje = "error";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
        }

        System.out.println("--->Mod sesion " + claveUsuario + " " + codigoHospitalEditar);
        return "pantallaModifcarEliminarHospital";
    }

    public String modificarDatosHospital() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        Hospitales hospitalBD = new HospitalDAO().findById(codigoHospitalEditar);
        Hospitales hospitalTemp = new Hospitales();

        String lada;
        lada = telefonoHospital.substring(1, 3) + telefonoHospital.substring(4, 6);
        telefonoHospital = telefonoHospital.substring(7, 12) + telefonoHospital.substring(13, telefonoHospital.length());

        hospitalTemp.setCodigoHospital(hospitalBD.getCodigoHospital());
        hospitalTemp.setEMail(emailHospital);
        hospitalTemp.setNombre(nombreHospital);
        hospitalTemp.setLada(lada);
        hospitalTemp.setTelefono(telefonoHospital);
        hospitalTemp.setUsuarios(new UsuarioDAO().findById(hospitalBD.getUsuarios().getNombreUsuario()));

        if (hospitalDAO.update(hospitalTemp)) {
            tituloAlert = "Datos Editados.";
            textoAlert = "Los datos del Hospital han sido satisfactoriamente.";
            estatusMensaje = "success";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);

        } else {
            tituloAlert = "Error Datos no Editados.";
            textoAlert = "Los Datos del Hospital no fueron editados.";
            estatusMensaje = "error";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
        }

        System.out.println("--->Mod Hosp " + nombreHospital + "  " + telefonoHospital + "  " + emailHospital + codigoHospitalEditar);
        return "pantallaModifcarEliminarHospital";
    }

    public String modificarDatosDireccionHospital() {
        System.out.println("---->Entro a mod Dir "+longitudX+"  "+latitudY);
        DomicilioHospitalDAO domicilioHospitalDAO = new DomicilioHospitalDAO();
        HospitalDAO hospitalDAO = new HospitalDAO();
        DomicilioHospitales domicilioHospitalTemp = new DomicilioHospitales();

        Iterator<DomicilioHospitales> iteratorDom = hospitalDAO.findById(codigoHospitalEditar).getDomicilioHospitaleses().iterator();

        while (iteratorDom.hasNext()) {
            domicilioHospitalTemp.setId(iteratorDom.next().getId());
        }

        domicilioHospitalTemp.setCalle(calle);
        domicilioHospitalTemp.setCodigoPostal(codigoPostal);
        domicilioHospitalTemp.setColonia(colonia);
        domicilioHospitalTemp.setDelegacion(delegacion);
        domicilioHospitalTemp.setEntidadFederativa(entidadFederativa);
        domicilioHospitalTemp.setNumero(numero);
        domicilioHospitalTemp.setHospitales(hospitalDAO.findById(codigoHospitalEditar));

        if (domicilioHospitalDAO.update(domicilioHospitalTemp)) {
            if (modificarHospitalCoordenadasDeOntologia(hospitalDAO.findById(codigoHospitalEditar).getNombre(), longitudX, latitudY)) {

                tituloAlert = "Domicilio Editado.";
                textoAlert = "Los datos del Domicilio del Hospital han sido actualizados satisfactoriamente.";
                estatusMensaje = "success";
            } else {
                tituloAlert = "Error Datos no Editados.";
                textoAlert = "E2 Ont. La Direccion del Hospital no fue editada.";
                estatusMensaje = "error";
            }
            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);

        } else {
            tituloAlert = "Error Datos no Editados.";
            textoAlert = "La Direccion del Hospital no fue editada.";
            estatusMensaje = "error";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
        }

        System.out.println("--->Mod Dir " + calle + " " + numero + " " + colonia + " " + delegacion + " " + entidadFederativa + " " + codigoPostal);
        return "pantallaModifcarEliminarHospital";
    }

    public String modificarDatosDirectivoHospital() {
        System.out.println("-->Entor a mod directivo");
        DirectivoDAO directivoDAO = new DirectivoDAO();
        HospitalDAO hospitalDAO = new HospitalDAO();
        Directivo directivoTemp = new Directivo();

        Iterator<Directivo> iteratorDirectivo = hospitalDAO.findById(codigoHospitalEditar).getDirectivos().iterator();

        while (iteratorDirectivo.hasNext()) {
            directivoTemp.setId(iteratorDirectivo.next().getId());
        }

        String lada;
        lada = telefonoDirectivo.substring(1, 3) + telefonoDirectivo.substring(4, 6);
        telefonoDirectivo = telefonoDirectivo.substring(7, 12) + telefonoDirectivo.substring(13, telefonoDirectivo.length());

        directivoTemp.setCorreo(emailDirectivo);
        directivoTemp.setNombre(nombreDirectivo);
        directivoTemp.setTelParticular(textoAlert);
        directivoTemp.setTelParticular(telefonoDirectivo);
        directivoTemp.setHospitales(hospitalDAO.findById(codigoHospitalEditar));

        if (directivoDAO.update(directivoTemp)) {
            tituloAlert = "Directivo Editado.";
            textoAlert = "Los datos del Directivo del Hospital han sido satisfactoriamente.";
            estatusMensaje = "success";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);

        } else {
            tituloAlert = "Error Datos no Editados.";
            textoAlert = "El Directivo del Hospital no fue editado.";
            estatusMensaje = "error";

            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
        }

        System.out.println("--->Mod Directivo " + telefonoDirectivo + " " + nombreDirectivo + " " + emailDirectivo);
        return "pantallaModifcarEliminarHospital";
    }

    public String modificarDatosEspecialidadesHospital() {
        System.out.println("--->Mod espec ");
        textoAlert = "";
        Enumeration<String> parametros = request.getParameterNames();
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        HospitalDAO hospitalDAO = new HospitalDAO();
        Boolean exitoQuerys = false;
        ArrayList<Especialidades> listaEspFormulario = new ArrayList<Especialidades>();
        Hospitales hospitalSave = new Hospitales();
        hospitalSave = hospitalDAO.findById(codigoHospitalEditar);
        String nombreHospitalDeBD = hospitalSave.getNombre();

        //------- Se llena una lista con las especialidades del formulario
        while (parametros.hasMoreElements()) {
            String nombreParametro = parametros.nextElement();

            if (nombreParametro.startsWith("checkbox")) {
                int codigoEspecialidadBD = Integer.parseInt(request.getParameter(nombreParametro));
                System.out.println("---> " + nombreParametro + "  " + request.getParameter(nombreParametro));
                Especialidades temp = especialidadDAO.findById(codigoEspecialidadBD);
                listaEspFormulario.add(temp);
            }

        }
        System.out.println("---->E " + hospitalSave.getEspecialidadeses().size());
        Iterator<Especialidades> espHosp = hospitalSave.getEspecialidadeses().iterator();

        ///-----------Eliminamos todos los registros de hospital en especialidades
        if (hospitalDAO.deleteHospitalEspecialidad(codigoHospitalEditar)) {
            exitoQuerys = true;
        } else {
            textoAlert += "E1 ";
            exitoQuerys = false;

        }

        if (exitoQuerys) {
            //------- Guardamos las especialidades del hospital que se eligieron
            for (Especialidades especForm : listaEspFormulario) {
                if (hospitalDAO.addHospitalEspecialidad(codigoHospitalEditar, especForm.getNoEspecialidad())) {
                    exitoQuerys = true;
                } else {
                    textoAlert += "E2 ";
                    exitoQuerys = false;
                    break;
                }
            }
        }

        if (exitoQuerys) {
            if (modificarHospitalEspecialidadesDeOntologia(nombreHospitalDeBD, listaEspFormulario)) {
                exitoQuerys = true;
            } else {
                exitoQuerys = false;
                textoAlert += "E3 Ontologia.";
            }
        }

        //---------- Se eliminan las especialidaes de los hospitales que no se encuentrarn en ea lista del form
        if (exitoQuerys) {
            tituloAlert = "Especialidades Editadas.";
            textoAlert = "Las Especialidades fueron Editadas.";
            estatusMensaje = "success";
        } else {
            tituloAlert = "Error Datos no Editados.";
            textoAlert += "Las Especialidades no fueron editadas.";
            estatusMensaje = "error";

        }

        session.put("tituloAlert", tituloAlert);
        session.put("textoAlert", textoAlert);
        session.put(LLAVE_ESTATUS_ME, estatusMensaje);

        return "pantallaModifcarEliminarHospital";
    }

    public String eliminarHospital() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String codigoHospitalTemp = codigoHospital;
        String nombreHospTemp = hospitalDAO.findById(codigoHospitalTemp).getNombre();
        System.out.println("--->Eliminar hospital " + codigoHospitalTemp + "  " + nombreHospTemp);

        if (usuarioDAO.deleteHospital(hospitalDAO.findById(codigoHospitalTemp).getUsuarios().getNombreUsuario())) {
            if (eliminarHospitalDeOntologia(nombreHospTemp)) {
                tituloAlert = "Hospital Eliminado";
                textoAlert = "Hospital eliminado satisfactoriamente.";
                estatusMensaje = "success";
            } else {
                tituloAlert = "Error Hospital no Eliminado";
                textoAlert = "El Hospital no fue eliminado debido a un problema en la Ontologia.";
                estatusMensaje = "error";

            }

        } else {
            tituloAlert = "Error Hospital no Eliminado";
            textoAlert = "El Hospital no fue eliminado.";
            estatusMensaje = "error";
        }

        return Action.SUCCESS;
    }

    Boolean eliminarHospitalDeOntologia(String nombreHospitalOnt) {
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";

        OWLEliminarIndividuo eliminarIndividuos = new OWLEliminarIndividuo(ONTOLOGIA, BASE_URI);

        nombreHospitalOnt = nombreHospitalOnt.replaceAll("\\s+", "");

        if (eliminarIndividuos.eliminarIndividuosDeNombreInstituto(nombreHospitalOnt)) {
            if (eliminarIndividuos.eliminarIndividuosDeDireccion("Direccion" + nombreHospitalOnt)) {
                return true;
            }
        }

        return false;
    }

    Boolean modificarHospitalEspecialidadesDeOntologia(String nombreHospitalOnt, ArrayList<Especialidades> listaEspecialidades) {
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";

        OWLEliminarIndividuo eliminarIndividuos = new OWLEliminarIndividuo(ONTOLOGIA, BASE_URI);

        String nombreHospitalOntConEspacios = nombreHospitalOnt;
        nombreHospitalOnt = nombreHospitalOnt.replaceAll("\\s+", "");

        if (eliminarIndividuos.eliminarIndividuosDeNombreInstituto(nombreHospitalOnt)) {

            OWLInsercionIndividuo insercionIndividuos = new OWLInsercionIndividuo(ONTOLOGIA, BASE_URI);
            insercionIndividuos.agregarHospital(nombreHospitalOnt);
            insercionIndividuos.agregarNombreHospital(nombreHospitalOnt, nombreHospitalOntConEspacios);

            OWLInsercionRelacion insercionRelaciones = new OWLInsercionRelacion(ONTOLOGIA, BASE_URI);
            insercionRelaciones.agregarRelacionSeUbicaEn(nombreHospitalOnt, "Direccion" + nombreHospitalOnt);

            for (Especialidades especialidadTemp : listaEspecialidades) {
                insercionRelaciones.agregarRelacionSeEspecializaEn(nombreHospitalOnt, especialidadTemp.getNombreEspecialidad());
            }
            return true;
        }

        return false;
    }

    Boolean modificarHospitalCoordenadasDeOntologia(String nombreHospitalOnt, String longitudXOnt, String latitudYOnt) {
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";

        OWLEliminarIndividuo eliminarIndividuos = new OWLEliminarIndividuo(ONTOLOGIA, BASE_URI);

        nombreHospitalOnt = nombreHospitalOnt.replaceAll("\\s+", "");

        if (eliminarIndividuos.eliminarIndividuosDeDireccion("Direccion" + nombreHospitalOnt)) {

            OWLInsercionIndividuo insercionIndividuos = new OWLInsercionIndividuo(ONTOLOGIA, BASE_URI);
            insercionIndividuos.agregarDireccion("Direccion" + nombreHospitalOnt);
            insercionIndividuos.agregarCoordenadas("Direccion" + nombreHospitalOnt, longitudXOnt, latitudYOnt);

            OWLInsercionRelacion insercionRelaciones = new OWLInsercionRelacion(ONTOLOGIA, BASE_URI);
            insercionRelaciones.agregarRelacionSeUbicaEn(nombreHospitalOnt, "Direccion" + nombreHospitalOnt);

            return true;
        }

        return false;
    }

    /**
     * ********************** METODOS PARA SETAR EN FORMULARIOS
     * ********************
     */
    public String recuperarEstatusMEHospital() {
        System.out.println("-->Entro a recuperar estatus ME");
        tituloAlert = "";
        textoAlert = "";
        estatusMensaje = "";

        if (session.get(LLAVE_ESTATUS_ME) != null) {
            tituloAlert = session.get("tituloAlert").toString();
            textoAlert = session.get("textoAlert").toString();
            estatusMensaje = session.get(LLAVE_ESTATUS_ME).toString();
        }

        session.remove(LLAVE_ESTATUS_ME);
        session.remove("tituloAlert");
        session.remove(LLAVE_ESTATUS_ME);
        session.put(LLAVE_ESTATUS_ME, null);

        return SUCCESS;
    }

    public String recuperarDatosFormSesion() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;

        claveUsuario = hospitalDAO.findById(codigoHospitalTemp).getUsuarios().getClave();
        return SUCCESS;
    }

    public String recuperarDatosFormHospital() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;

        Hospitales hospitalTemp = hospitalDAO.findById(codigoHospitalTemp);

        nombreHospital = hospitalTemp.getNombre();
        telefonoHospital = hospitalTemp.getLada() + hospitalTemp.getTelefono();
        emailHospital = hospitalTemp.getEMail();

        return SUCCESS;
    }

    public String recuperarDatosFormDireccion() {

        String ONTOLOGIA = request.getServletContext().getRealPath("/")+"WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";

        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;
        Hospitales hospitalTemp = hospitalDAO.findById(codigoHospitalTemp);
        String nombreHospitalTemp = hospitalTemp.getNombre();

        Iterator<DomicilioHospitales> it = hospitalTemp.getDomicilioHospitaleses().iterator();
        nombreHospitalTemp = nombreHospitalTemp.replaceAll("\\s+", "");
        while (it.hasNext()) {
            DomicilioHospitales domHospTemp = it.next();
            calle = domHospTemp.getCalle();
            numero = domHospTemp.getNumero();
            colonia = domHospTemp.getColonia();
            delegacion = domHospTemp.getDelegacion();
            entidadFederativa = domHospTemp.getEntidadFederativa();
            codigoPostal = domHospTemp.getCodigoPostal();

            OWLConsultas consultor = new OWLConsultas(ONTOLOGIA, BASE_URI);
            consultor.hospitalseUbicaEnDireccion(nombreHospitalTemp);

            consultor.getCoordenadaYDireccion("Direccion" + nombreHospitalTemp);
            latitudY = consultor.getCoordenadaYDireccion("Direccion" + nombreHospitalTemp).get(0);
            longitudX = consultor.getCoordenadaXDireccion("Direccion" + nombreHospitalTemp).get(0);

        }

        return SUCCESS;
    }

    public String recuperarDatosFormDirectivo() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;
        Hospitales hospitalTemp = hospitalDAO.findById(codigoHospitalTemp);

        Iterator<Directivo> it = hospitalTemp.getDirectivos().iterator();

        while (it.hasNext()) {
            Directivo directivoTemp = it.next();

            telefonoDirectivo = directivoTemp.getTelParticular();
            emailDirectivo = directivoTemp.getCorreo();
            nombreDirectivo = directivoTemp.getNombre();

        }

        return SUCCESS;
    }

    public String recuperarDatosFormEspecialidades() {
        System.out.println("--->Entro a recuperarEspecialidades modificar");
        String html = "";
        HospitalDAO hospitalDAO = new HospitalDAO();
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        ArrayList<Especialidades> especialidadesList = (ArrayList<Especialidades>) especialidadDAO.findAll();

        if (especialidadesList == null) {
            return SUCCESS;
        }

        Set<Especialidades> especHasHosp = hospitalDAO.findById(codigoHospital).getEspecialidadeses();

        if (especHasHosp == null) {
            return SUCCESS;
        }

        int contEspec = 0;
        for (Especialidades especialidadTemp : especialidadesList) {
            Boolean especialidadMarcada = false;
            Iterator<Especialidades> iterEspecHosp = especHasHosp.iterator();

            while (iterEspecHosp.hasNext()) {
                Especialidades especTemp = iterEspecHosp.next();
                System.out.println("--->" + especialidadTemp.getNoEspecialidad() + "  " + especTemp.getNoEspecialidad());
                if (especialidadTemp.getNoEspecialidad() == especTemp.getNoEspecialidad()) {
                    html += "<div style=\"margin-bottom:10px;\"; class=\"input-group\">"
                            + "<span class=\"input-group-addon\">"
                            + "<input type=\"checkbox\" checked=\"true\" name=\"checkbox" + contEspec + "\" value=\"" + especialidadTemp.getNoEspecialidad() + "\">"
                            + "</span>"
                            + "<input disabled=\"true\" class=\"form-control\" type=\"text\" value=\"" + especialidadTemp.getNombreEspecialidad() + "\">"
                            + "</div><!-- /input-group -->";
                    especialidadMarcada = true;
                    break;
                }
            }

            if (!especialidadMarcada) {
                html += "<div style=\"margin-bottom:10px;\"; class=\"input-group\">"
                        + "<span class=\"input-group-addon\">"
                        + "<input type=\"checkbox\" name=\"checkbox" + contEspec + "\" value=\"" + especialidadTemp.getNoEspecialidad() + "\">"
                        + "</span>"
                        + "<input disabled=\"true\" class=\"form-control\" type=\"text\" value=\"" + especialidadTemp.getNombreEspecialidad() + "\">"
                        + "</div><!-- /input-group -->";
            }
            contEspec++;
        }

        especialidades = html;

        return SUCCESS;
    }

    /**
     * *************************************** METODOS GRID
     * ********************************
     */
    public String llenarGridHospitalME() {

        System.out.println("\n\n--->Entro a llenar tabla");
        // Obtenemos la tabla desordenada
        obtenerTablaHospitales();
        // Quitamos los registros que no se desplegarán en el grid
        recortarTablaHospitales();

        return Action.SUCCESS;
    }

    private void obtenerTablaHospitales() {
        HospitalDAO hospitalesDAO = new HospitalDAO();
        ArrayList<Hospitales> listaTemp = new ArrayList<Hospitales>();
        ArrayList<Hospitales> listaTempFinal = new ArrayList<Hospitales>();

        // Obtenemos la lista de la sesión
        listaTemp = (ArrayList<Hospitales>) hospitalesDAO.findAll();

        for (Hospitales tempContHosp : listaTemp) {

            listaTempFinal.add(new Hospitales(tempContHosp.getCodigoHospital(), null, tempContHosp.getNombre(), tempContHosp.getTelefono(), tempContHosp.getLada(), tempContHosp.getEMail()));
        }
        gridListaHospitales = listaTempFinal;
        if (gridListaHospitales == null) {
            records = total = 0;
        } else {
            // Obtenemos el total de registros
            records = gridListaHospitales.size();
            // Calculamos el total de páginas necesarias
            total = (int) Math.ceil((double) records / (double) rows);
        }
    }

    private void recortarTablaHospitales() {
        // Calculamos el inicio de los registros a mostrar
        desde = (page * rows) - rows;
        // Calculamos el final de los registros a mostrar
        hasta = page * rows > records ? records - 1 : page * rows - 1;

        Iterator<Hospitales> it = gridListaHospitales.iterator();

        // Quitamos los registros del TreeSet que no se vayan a desplegar en el
        // jQuery grid del jsp
        if (it.hasNext()) {
            it.next();
        }
        for (int cont = 0; it.hasNext(); cont++, it.next()) {
            if ((cont < desde) || (cont > hasta)) {
                it.remove();
            }
        }
    }

    /**
     * ************************************************************************
     */
    public String getCodigoHospital() {
        return codigoHospital;
    }

    public void setCodigoHospital(String codigoHospital) {
        this.codigoHospital = codigoHospital;
    }

    public String getCodigoHospitalEditar() {
        return codigoHospitalEditar;
    }

    public void setCodigoHospitalEditar(String codigoHospitalEditar) {
        this.codigoHospitalEditar = codigoHospitalEditar;
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

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    /**
     * ************************************************************************
     */
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

    /**
     * ****************** Lo siguiente está relacionado al jQuery Grid
     * *************************
     */
    private ArrayList<Hospitales> gridListaHospitales;
    // Indica cuantas filas queremos mostrar (Atributo 'rowNum' en el grid del
    // jsp)
    private int rows;
    // Total de páginas necesarias para mostrar los registros obtenidos
    private int total;
    // Página actual (por default el grid la setea a 1)
    private int page;
    // Indica el inicio del paginado
    private int desde;
    // Indica el final del paginado
    private int hasta;
    // Número total de registros obtenidos
    private int records;
    // Tipo de ordenamiento (asc - desc)
    private String sord;
    // Campo seleccionado para realizar el ordenamiento (atributo 'index' en el
    // grid del jsp)
    private String sidx;
    // Indica que tipo de operación (alta->add, baja->del, cambio->edit) se
    // quiere realizar
    private String oper;
    // Indica el registro que se quiere modificar o borrar (atributo 'key' del
    // jQuery grid del jsp)
    private String id;
    // Resultado de la operación realizada (mensaje q se despliega en el jsp)
    private String operResult;

    /*
     * Aqui vienen los getters y setters del jQuery Grid
     */
    public ArrayList<Hospitales> getGridListaHospitales() {
        return gridListaHospitales;
    }

    public void setGridListaHospitales(ArrayList<Hospitales> gridListaHospitales) {
        this.gridListaHospitales = gridListaHospitales;
    }

    public Integer getRecords() {
        return records;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPage() {
        return this.page;
    }

    public String getOperResult() {
        return operResult;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public void setId(String id) {
        this.id = id;
    }
    /**
     * ********************* Aquí acaba lo relacionado con jQuery grid
     * *************************
     */

}
