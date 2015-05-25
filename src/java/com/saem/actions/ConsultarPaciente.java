/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.PacienteDAO;
import com.hibernate.model.Contactos;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.DomicilioPacientes;
import com.hibernate.model.TelefonosPacientes;
import com.hibernate.model.Pacientes;
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.persistencia.owl.OWLConsultas;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
/**
 *
 * @author gabriela
 */
public class ConsultarPaciente {
    private static final String LISTA_PACIENTES = "LISTA_PACIENTES_ME";
    private static final String LLAVE_ESTATUS_ME = "CONSULTARPACIENTE_ESTATUS_LLAVE";

    private String nssP;
    //campos del formulario
    private String nombreUsuario;
    private String claveUsuario;

    private String nss;
    private String nombre;
    private String apellidoPaternoPaciente;
    private String apellidoMaternoPaciente;
    private String unidadMedicaPaciente;
    private String noConsultorio;

    private String latitudY ;
    private String longitudX ;
    private String calle;
    private String numero;
    private String colonia;
    private String delegacion;
    private String entidadFederativa;
    private String codigoPostal;

    private String telefonoFijo;
    private String telefonoParticular;
    
    private String estadoCivil ;
    private String curp ;
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
    
    private String nombreC ;
    private String apellidoPaternoC ;
    private String apellidoMaternoC;
    private String parentesco;
    private String celular;
    private String facebookC;
    private String correoC;
    
    private Boolean usoDrogas;
    private Boolean usoAlcohol;
    private Boolean fumador;
    
    //fin de campos formularo

    private String tituloAlert = "";
    private String textoAlert = "";
    private String estatusMensaje = "";

    HttpServletRequest request = ServletActionContext.getRequest();
    private Map<String, Object> session = null;

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute() {
        return "pantallaConsultarPacientes";
    }

    
/************************ METODOS PARA SETAR EN FORMULARIOS *********************/
    public String recuperarEstatusConsultarPaciente() {
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
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);
        
        nombreUsuario = pacienteDAO.findById(nssPTemp).getUsuarios().getNombreUsuario();
        claveUsuario = pacienteDAO.findById(nssPTemp).getUsuarios().getClave();
        return SUCCESS;
    }

    public String recuperarDatosConsultaPaciente() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);

        Pacientes pacienteTemp = pacienteDAO.findById(nssPTemp);
        
        nss = pacienteTemp.getNss();
        nombre = pacienteTemp.getNombre();
        apellidoPaternoPaciente = pacienteTemp.getApellidoPaterno();
        apellidoMaternoPaciente = pacienteTemp.getApellidoMaterno();
        unidadMedicaPaciente = pacienteTemp.getUnidadMedica();
        noConsultorio = pacienteTemp.getNoConsultorio();

        return SUCCESS;
    }

    public String recuperarDatosConsultaDireccion() {
        //String ONTOLOGIA = request.getServletContext().getRealPath("/")+"WEB-INF/serviciomedico.owl";
        //String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
            
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);
        Pacientes pacienteTemp = pacienteDAO.findById(nssPTemp);

        Iterator<DomicilioPacientes> it = pacienteTemp.getDomicilioPacienteses().iterator();
        //String nombrePacienteTemp = pacienteTemp.getNombre();
        while (it.hasNext()) {
            DomicilioPacientes domPacientTemp = it.next();
            calle = domPacientTemp.getCalle();
            //numero = domPacientTemp.getNumero();
            colonia = domPacientTemp.getColonia();
            delegacion = domPacientTemp.getDelegacion();
            entidadFederativa = domPacientTemp.getEntidadFederativa();
            codigoPostal = domPacientTemp.getCodigoPostal();
            
            /*OWLConsultas consultor = new OWLConsultas(ONTOLOGIA, BASE_URI);
            consultor.pacienteseUbicaEnDireccion(nombrePacienteTemp);
       
            consultor.getCoordenadaYDireccion("Direccion"+nombrePacienteTemp);
            latitudY = consultor.getCoordenadaYDireccion("Direccion"+nombrePacienteTemp).get(0);
            longitudX = consultor.getCoordenadaXDireccion("Direccion"+nombrePacienteTemp).get(0);*/
        }

        return SUCCESS;
    }
    
    public String recuperarDatosConsultaTelefono() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);
        Pacientes pacienteTemp = pacienteDAO.findById(nssPTemp);

        Iterator<TelefonosPacientes> it = pacienteTemp.getTelefonosPacienteses().iterator();

        while (it.hasNext()) {
            TelefonosPacientes telefonoTemp = it.next();
            
            telefonoFijo = telefonoTemp.getTelefonoFijo();
            telefonoParticular = telefonoTemp.getTelefonoParticular();
            
        }

        return SUCCESS;
    }
    
    public String recuperarDatosPersonalesConsulta() {
        String ONTOLOGIA = request.getServletContext().getRealPath("/")+"WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
            
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);
        Pacientes pacienteTemp = pacienteDAO.findById(nssPTemp);

        Iterator<DatosPersonales> it = pacienteTemp.getDatosPersonaleses().iterator();
        //String datosPersonalesTemp = pacienteTemp.getNombre();
        while (it.hasNext()) {
            DatosPersonales datPerPacientTemp = it.next();
            estadoCivil = datPerPacientTemp.getEstadoCivil();
            curp = datPerPacientTemp.getCurp();
            sexo = datPerPacientTemp.getSexo();
            fechaNacimiento = datPerPacientTemp.getFechaNacimiento();
            edad = datPerPacientTemp.getEdad();
            peso = datPerPacientTemp.getPeso();
            altura = datPerPacientTemp.getAltura();
            talla = datPerPacientTemp.getTalla();
            telCasa = datPerPacientTemp.getTelCasa();
            telCel = datPerPacientTemp.getTelCel();
            correo = datPerPacientTemp.getCorreo();
            facebook = datPerPacientTemp.getFacebook();
        }

        return SUCCESS;
    }
    
    public String recuperarDatosContactosConsulta() {
        String ONTOLOGIA = request.getServletContext().getRealPath("/")+"WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
            
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);
        Pacientes pacienteTemp = pacienteDAO.findById(nssPTemp);

        Iterator<Contactos> it = pacienteTemp.getContactoses().iterator();
        //String datosContactosTemp = pacienteTemp.getNombre();
        while (it.hasNext()) {
            Contactos datContactPacientTemp = it.next();
            nombreC = datContactPacientTemp.getNombreC();
            apellidoPaternoC = datContactPacientTemp.getApellidoPaternoC();
            apellidoMaternoC = datContactPacientTemp.getApellidoMaternoC();
            parentesco = datContactPacientTemp.getParentesco();
            celular = datContactPacientTemp.getCelular();
            facebookC = datContactPacientTemp.getFacebookC();
            correoC = datContactPacientTemp.getCorreoC();
        }

        return SUCCESS;
    }
    
    public String recuperarDatosClinicosConsulta() {
        String ONTOLOGIA = request.getServletContext().getRealPath("/")+"WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
            
        PacienteDAO pacienteDAO = new PacienteDAO();
        int nssPTemp = Integer.parseInt(nssP);
        Pacientes pacienteTemp = pacienteDAO.findById(nssPTemp);

        Iterator<DatosClinicos> it = pacienteTemp.getDatosClinicoses().iterator();
        //String datosClinicosTemp = pacienteTemp.getNombre();
        while (it.hasNext()) {
            DatosClinicos datClinicPacientTemp = it.next();
            usoDrogas = datClinicPacientTemp.isUsoDrogas();
            usoAlcohol = datClinicPacientTemp.isUsoAlcohol();
            fumador = datClinicPacientTemp.isFumador();
        }

        return SUCCESS;
    }

    /**
     * *************************************** METODOS GRID
     * ********************************
     */
    public String llenarGridPacienteConsultar() {

        System.out.println("\n\n--->Entro a llenar tabla Consultar");
        // Obtenemos la tabla desordenada
        obtenerTablaPacientes();
        // Quitamos los registros que no se desplegarán en el grid
        recortarTablaPacientes();

        return Action.SUCCESS;
    }

    private void obtenerTablaPacientes() {
        PacienteDAO pacientesDAO = new PacienteDAO();
        ArrayList<Pacientes> listaTemp = new ArrayList<Pacientes>();
        ArrayList<Pacientes> listaTempFinal = new ArrayList<Pacientes>();

        // Obtenemos la lista de la sesión
        listaTemp = (ArrayList<Pacientes>) pacientesDAO.findAll();

        for (Pacientes tempContPaciente : listaTemp) {

            listaTempFinal.add(new Pacientes(tempContPaciente.getNss(), null, null, tempContPaciente.getNombre(), tempContPaciente.getApellidoPaterno(), tempContPaciente.getApellidoMaterno(), tempContPaciente.getUnidadMedica(), tempContPaciente.getNoConsultorio(), null, null, null, null, null, null ));
        }
        gridListaConsultaPacientes = listaTempFinal;
        if (gridListaConsultaPacientes == null) {
            records = total = 0;
        } else {
            // Obtenemos el total de registros
            records = gridListaConsultaPacientes.size();
            // Calculamos el total de páginas necesarias
            total = (int) Math.ceil((double) records / (double) rows);
        }
    }

    private void recortarTablaPacientes() {
        // Calculamos el inicio de los registros a mostrar
        desde = (page * rows) - rows;
        // Calculamos el final de los registros a mostrar
        hasta = page * rows > records ? records - 1 : page * rows - 1;

        Iterator<Pacientes> it = gridListaConsultaPacientes.iterator();

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
    public String getNss() {
        return this.nss;
    }
    
    public void setNss(String nss) {
        this.nss = nss;
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

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaternoPaciente;
    }
    
    public void setApellidoPaterno(String apellidoPaternoPaciente) {
        this.apellidoPaternoPaciente = apellidoPaternoPaciente;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaternoPaciente;
    }
    
    public void setApellidoMaterno(String apellidoMaternoPaciente) {
        this.apellidoMaternoPaciente = apellidoMaternoPaciente;
    }
    
    public String getUnidadMedica() {
        return this.unidadMedicaPaciente;
    }
    
    public void setUnidadMedica(String unidadMedica) {
        this.unidadMedicaPaciente = unidadMedica;
    }
    
    public String getNoConsultorio() {
        return this.noConsultorio;
    }
    
    public void setNoConsultorio(String noConsultorio) {
        this.noConsultorio = noConsultorio;
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
        return this.apellidoPaternoC;
    }
    
    public void setApellidoPaternoC(String apellidoPaternoC) {
        this.apellidoPaternoC = apellidoPaternoC;
    }
    public String getApellidoMaternoC() {
        return this.apellidoMaternoC;
    }
    
    public void setApellidoMaternoC(String apellidoMaternoC) {
        this.apellidoMaternoC = apellidoMaternoC;
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
        return this.facebookC;
    }
    
    public void setFacebookC(String facebookC) {
        this.facebookC = facebookC;
    }
    public String getCorreoC() {
        return this.correoC;
    }
    
    public void setCorreoC(String correoC) {
        this.correoC = correoC;
    }
    
    public boolean isUsoDrogas() {
        return this.usoDrogas;
    }
    
    public void setUsoDrogas(boolean usoDrogas) {
        this.usoDrogas = usoDrogas;
    }
    public boolean isUsoAlcohol() {
        return this.usoAlcohol;
    }
    
    public void setUsoAlcohol(boolean usoAlcohol) {
        this.usoAlcohol = usoAlcohol;
    }
    public boolean isFumador() {
        return this.fumador;
    }
    
    public void setFumador(boolean fumador) {
        this.fumador = fumador;
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
    private ArrayList<Pacientes> gridListaConsultaPacientes;
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
    public ArrayList<Pacientes> getGridListaConsultaPacientes() {
        return gridListaConsultaPacientes;
    }

    public void setGridListaConsultaPacientes(ArrayList<Pacientes> gridListaConsultaPacientes) {
        this.gridListaConsultaPacientes = gridListaConsultaPacientes;
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
}
