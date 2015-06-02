/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class ConsultarHospital implements SessionAware {

    private static final String LISTA_HOSPITALES = "LISTA_HOSPITALES_CONSULTAR";
    private static final String LLAVE_ESTATUS_ME = "CONSULTARHOSPITAL_ESTATUS_LLAVE";

    private String codigoHospital;
    //campos del formulario
    private String nombreUsuario;
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
    private String filtroBusquedaHospital;
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
        return "pantallaConsultarHospitales";
    }
    
    public String pantallaConsultarInformacionHospital() {
        return "pantallaConsultarInformacionHospital";
    }

    /**
     * ********************** METODOS PARA SETAR EN FORMULARIOS
     * ********************
     */
    public String recuperarEstatusConsultarHospital() {
        System.out.println("-->Entro a recuperar estatus Consultar");
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

    public String recuperarDatosConsultaSesion() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;

        nombreUsuario = hospitalDAO.findById(codigoHospitalTemp).getUsuarios().getNombreUsuario();
        claveUsuario = hospitalDAO.findById(codigoHospitalTemp).getUsuarios().getClave();
        return SUCCESS;
    }

    public String recuperarDatosConsultaHospital() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;

        Hospitales hospitalTemp = hospitalDAO.findById(codigoHospitalTemp);

        nombreHospital = hospitalTemp.getNombre();
        telefonoHospital = hospitalTemp.getLada() + hospitalTemp.getTelefono();
        emailHospital = hospitalTemp.getEMail();

        return SUCCESS;
    }

    public String recuperarDatosConsultaDireccion() {
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";

        HospitalDAO hospitalDAO = new HospitalDAO();
        String codigoHospitalTemp = codigoHospital;
        Hospitales hospitalTemp = hospitalDAO.findById(codigoHospitalTemp);

        Iterator<DomicilioHospitales> it = hospitalTemp.getDomicilioHospitaleses().iterator();
        String nombreHospitalTemp = hospitalTemp.getNombre();
        while (it.hasNext()) {
            DomicilioHospitales domHospTemp = it.next();
            calle = domHospTemp.getCalle();
            numero = domHospTemp.getNumero();
            colonia = domHospTemp.getColonia();
            delegacion = domHospTemp.getDelegacion();
            entidadFederativa = domHospTemp.getEntidadFederativa();
            codigoPostal = domHospTemp.getCodigoPostal();

            nombreHospitalTemp = nombreHospitalTemp.replaceAll("\\s+", "");
            OWLConsultas consultor = new OWLConsultas(ONTOLOGIA, BASE_URI);
            consultor.hospitalseUbicaEnDireccion(nombreHospitalTemp);
            System.out.println("--->" + ONTOLOGIA);
            consultor.getCoordenadaYDireccion("Direccion" + nombreHospitalTemp);
            latitudY = consultor.getCoordenadaYDireccion("Direccion" + nombreHospitalTemp).get(0);
            longitudX = consultor.getCoordenadaXDireccion("Direccion" + nombreHospitalTemp).get(0);
        }

        return SUCCESS;
    }

    public String recuperarDatosConsultaDirectivo() {
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

    public String recuperarDatosEspecialidades() {
        HospitalDAO hospitalDAO = new HospitalDAO();
        System.out.println("--->Entro a recuperarEspecialidades Consulta");
        String html = "";

        Set<Especialidades> especialidadesHospial = hospitalDAO.findById(codigoHospital).getEspecialidadeses();
        if (especialidadesHospial == null) {
            return SUCCESS;
        }
        System.out.println("--->" + especialidadesHospial.size());

        Iterator<Especialidades> iterEspecHosp = especialidadesHospial.iterator();

        int contEspec = 0;
        while (iterEspecHosp.hasNext()) {
            Especialidades especialidadTemp = iterEspecHosp.next();

            html += "<div style=\"margin-bottom:10px;\"; class=\"input-group\">"
                    + "<span class=\"input-group-addon\">"
                    + "<input type=\"checkbox\" checked=\"true\" disabled=\"true\" name=\"checkbox" + contEspec + "\" value=\"" + especialidadTemp.getNoEspecialidad() + "\">"
                    + "</span>"
                    + "<input disabled=\"true\" class=\"form-control\" type=\"text\" value=\"" + especialidadTemp.getNombreEspecialidad() + "\">"
                    + "</div><!-- /input-group -->";
            contEspec++;

        }
        especialidades = html;

        return SUCCESS;
    }

    public String recuperarCodigoHospital(){
        String idUsuario = (String)session.get("nombreUsuario");
        System.out.println("----> "+idUsuario);
        Usuarios usuarioTemp = new UsuarioDAO().findById(idUsuario);
        Iterator<Hospitales> iterHospitales = usuarioTemp.getHospitaleses().iterator();
        
        while(iterHospitales.hasNext()){
            Hospitales hospitalTemp = iterHospitales.next();
            codigoHospital = hospitalTemp.getCodigoHospital();
            System.out.println("--->Cod "+codigoHospital);
        }
        return SUCCESS;
    }
    /**
     * *************************************** METODOS GRID
     * ********************************
     */
    public String llenarGridHospitalConsultar() {

        System.out.println("\n\n--->Entro a llenar tabla Consultar");
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
        System.out.println("--->Filtro " + filtroBusquedaHospital);
        gridListaConsultaHospitales = new ArrayList<Hospitales>();

        if (filtroBusquedaHospital == null) {
            if (session.get(LISTA_HOSPITALES) != null) {
                listaTemp = (ArrayList<Hospitales>) session.get(LISTA_HOSPITALES);
                session.remove(LISTA_HOSPITALES);
            } else {
                return;
            }
        } else {

            if (filtroBusquedaHospital.length() > 0) {
                listaTemp = (ArrayList<Hospitales>) hospitalesDAO.findHospitalLike(filtroBusquedaHospital);

                System.out.println("--->Entro a filtro mayor " + listaTemp.size());
            } else {
                // Obtenemos la lista de la sesión
                listaTemp = (ArrayList<Hospitales>) hospitalesDAO.findAll();
            }
            session.put(LISTA_HOSPITALES, listaTemp);
        }

        for (Hospitales tempContHosp : listaTemp) {
            listaTempFinal.add(new Hospitales(tempContHosp.getCodigoHospital(), null, tempContHosp.getNombre(), tempContHosp.getTelefono(), tempContHosp.getLada(), tempContHosp.getEMail()));
        }

        gridListaConsultaHospitales = listaTempFinal;

        if (gridListaConsultaHospitales == null) {
            records = total = 0;
        } else {
            // Obtenemos el total de registros
            records = gridListaConsultaHospitales.size();
            // Calculamos el total de páginas necesarias
            total = (int) Math.ceil((double) records / (double) rows);
        }
    }

    private void recortarTablaHospitales() {
        // Calculamos el inicio de los registros a mostrar
        desde = (page * rows) - rows;
        // Calculamos el final de los registros a mostrar
        hasta = page * rows > records ? records - 1 : page * rows - 1;

        Iterator<Hospitales> it = gridListaConsultaHospitales.iterator();

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

    public String getFiltroBusquedaHospital() {
        return filtroBusquedaHospital;
    }

    public void setFiltroBusquedaHospital(String filtroBusquedaHospital) {
        this.filtroBusquedaHospital = filtroBusquedaHospital;
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
    private ArrayList<Hospitales> gridListaConsultaHospitales;
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
    public ArrayList<Hospitales> getGridListaConsultaHospitales() {
        return gridListaConsultaHospitales;
    }

    public void setGridListaConsultaHospitales(ArrayList<Hospitales> gridListaConsultaHospitales) {
        this.gridListaConsultaHospitales = gridListaConsultaHospitales;
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
