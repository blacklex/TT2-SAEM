/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.PeticionesSalientesDAO;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.Pacientes;
import com.hibernate.model.PeticionesSalientes;
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author Alejandro
 */
public class PeticionesExternasAction implements SessionAware {

    private static final String LISTA_HOSPITALES = "LISTA_HOSPITALES_ME";
    private static final String LLAVE_ESTATUS_ME = "PETICIONES_EXTERNAS_ESTATUS_LLAVE";

    //campos del formulario
    private String idPeticionesSalientes;

    private String nss;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String unidadMedica;
    private String noConsultorio;
    private String edad;
    private String peso;
    private String altura;
    private String noHistorial;

    private String comentario;
    private String idPeticionesExternasFormPeticion;
    private String tipoDeRespuestaPeticion;
    private String latitudPeticion;
    private String longitudPeticion;
    //fin de campos formularo

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
        return "pantallaPeticionesExternasHospital";
    }

    public String responderPeticionExterna() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        PeticionesSalientesDAO peticionesSalientesDAO = new PeticionesSalientesDAO();
        System.out.println("---> Responder pet sal " + comentario + "  " + idPeticionesExternasFormPeticion + "  " + tipoDeRespuestaPeticion);

        PeticionesSalientes peticion = peticionesSalientesDAO.findById(s, idPeticionesExternasFormPeticion);

        if (peticion == null) {
            System.out.println("-->null");
            tituloAlert = "Error en encontrar la Petición.";
            textoAlert = "No se ha encontrado la información de la petición.";
            estatusMensaje = "error";
            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
            s.close();
            return SUCCESS;
        }

        if (tipoDeRespuestaPeticion.equals("contestarPeticion")) {
            peticion.setEstatus("PA");
            peticion.setComentario("PETICION ACEPTADA: " + comentario);

            if (peticionesSalientesDAO.update(peticion)) {
                System.out.println("-->update");
                tituloAlert = "Petición Contestada.";
                textoAlert = "La petición ha sido contestada exitosamente.";
                estatusMensaje = "success";
            } else {
                System.out.println("-->no update");
                tituloAlert = "Error petición no contestada.";
                textoAlert = "No se contesto la petición.";
                estatusMensaje = "error";
            }

        } else if (tipoDeRespuestaPeticion.equals("rechazarPeticion")) {
            peticion.setEstatus("PR");
            peticion.setComentario("PETICION RECHAZADA: " + comentario);

            if (peticionesSalientesDAO.update(peticion)) {
                System.out.println("-->update");
                tituloAlert = "Petición Rechazada.";
                textoAlert = "La petición ha sido rechazada exitosamente.";
                estatusMensaje = "success";
            } else {
                System.out.println("-->no update");
                tituloAlert = "Error petición no rechazada.";
                textoAlert = "No se rechazo la petición debido a un error interno.";
                estatusMensaje = "error";
            }
        }

        session.put("tituloAlert", tituloAlert);
        session.put("textoAlert", textoAlert);
        session.put(LLAVE_ESTATUS_ME, estatusMensaje);

        s.close();
        return "pantallaPeticionesExternasHospital";
    }

    public String recuperarDatosPaciente() {
        System.out.println("--> recuperarPeticion " + idPeticionesSalientes);
        PeticionesSalientes peticion;
        Pacientes paciente;
        PeticionesSalientesDAO peticionesEntrantesDAO = new PeticionesSalientesDAO();
        Session s = com.hibernate.cfg.HibernateUtil.getSession();

        peticion = peticionesEntrantesDAO.findById(s, idPeticionesSalientes);

        latitudPeticion = peticion.getLatitudPaciente();
        longitudPeticion = peticion.getLongitudPaciente();

        if (peticion == null) {
            tituloAlert = "Error al recuperar datos.";
            textoAlert = "No se recuperarón los datos del paciente.";
            estatusMensaje = "error";
            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
            return SUCCESS;
        }

        paciente = peticion.getPacientes();
        nss = paciente.getNss();
        unidadMedica = paciente.getUnidadMedica();
        nombre = paciente.getNombre();
        apellidoPaterno = paciente.getApellidoPaterno();
        apellidoMaterno = paciente.getApellidoMaterno();
        noConsultorio = paciente.getNoConsultorio();

        if (paciente.getDatosPersonaleses().iterator().hasNext() == false || paciente.getDatosClinicoses().iterator().hasNext() == false) {
            tituloAlert = "Error al recuperar datos.";
            textoAlert = "No se recuperarón los datos personales y/o clinicos del paciente.";
            estatusMensaje = "error";
            session.put("tituloAlert", tituloAlert);
            session.put("textoAlert", textoAlert);
            session.put(LLAVE_ESTATUS_ME, estatusMensaje);
            return SUCCESS;
        }
        DatosPersonales datosPerPaciente = (DatosPersonales) paciente.getDatosPersonaleses().iterator().next();
        DatosClinicos datosClinicosPaciente = (DatosClinicos) paciente.getDatosClinicoses().iterator().next();

        edad = datosPerPaciente.getEdad();
        peso = datosPerPaciente.getPeso();
        altura = datosPerPaciente.getAltura();
        noHistorial = datosClinicosPaciente.getNoHistorial() + "";
        s.close();

        return SUCCESS;
    }

  
    /**
     * ********************** METODOS PARA SETAR EN FORMULARIOS
     * ********************
     */
    public String recuperarEstatusPeticionesExternasHospital() {
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
        session.put(LLAVE_ESTATUS_ME, null);

        return SUCCESS;
    }

    /**
     * *************************************** METODOS GRID
     * ********************************
     */
    public String llenarGridPeticionesExternas() {

        System.out.println("\n\n--->Entro a llenar tabla Peticiones Externas");

        // Obtenemos la tabla desordenada
        obteneTablaPeticionesExternas();
        // Quitamos los registros que no se desplegarán en el grid
        recortarTablaPeticionesExternas();

        return Action.SUCCESS;
    }

    private void obteneTablaPeticionesExternas() {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        PeticionesSalientesDAO peticionesSalientesDAO = new PeticionesSalientesDAO();
        ArrayList<PeticionesSalientes> listaTemp = new ArrayList<PeticionesSalientes>();
        ArrayList<PeticionesSalientes> listaTempFinal = new ArrayList<PeticionesSalientes>();
        String codigoHosptail = (String) session.get("HospitalCodigoHospital");

        if (codigoHosptail == null) {
            return;
        }

        // Obtenemos la lista de la sesión
        listaTemp = (ArrayList<PeticionesSalientes>) peticionesSalientesDAO.findAllByHospital(s, codigoHosptail);

        System.out.println("---> Tam pet Salientes " + listaTemp.size());

        for (PeticionesSalientes tempContHosp : listaTemp) {
            PeticionesSalientes tempPet = new PeticionesSalientes(tempContHosp.getIdPeticionesSalientes(), null, null, tempContHosp.getFechaRegistro(), tempContHosp.getEstatus(), tempContHosp.getLatitudPaciente(), tempContHosp.getLongitudPaciente(), tempContHosp.getPrioridad());
            tempPet.setNombrePaciente(tempContHosp.getPacientes().getNombre());
            tempPet.setApellidoPaciente(tempContHosp.getPacientes().getApellidoPaterno());
            tempPet.setNss(tempContHosp.getPacientes().getNss());

            Iterator<DatosPersonales> iterDatosPerPac = tempContHosp.getPacientes().getDatosPersonaleses().iterator();
            if (iterDatosPerPac.hasNext()) {
                tempPet.setFechaNacimineto(iterDatosPerPac.next().getFechaNacimiento());
            }

            listaTempFinal.add(tempPet);
        }

        gridListaPeticionesExternas = listaTempFinal;

        if (gridListaPeticionesExternas == null) {
            records = total = 0;
        } else {
            // Obtenemos el total de registros
            records = gridListaPeticionesExternas.size();
            // Calculamos el total de páginas necesarias
            total = (int) Math.ceil((double) records / (double) rows);
        }
        s.close();
    }

    private void recortarTablaPeticionesExternas() {
        // Calculamos el inicio de los registros a mostrar
        desde = (page * rows) - rows;
        // Calculamos el final de los registros a mostrar
        hasta = page * rows > records ? records - 1 : page * rows - 1;

        Iterator<PeticionesSalientes> it = gridListaPeticionesExternas.iterator();

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

    public String getIdPeticionesSalientes() {
        return idPeticionesSalientes;
    }

    public void setIdPeticionesSalientes(String idPeticionesSalientes) {
        this.idPeticionesSalientes = idPeticionesSalientes;
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

    public String getNoHistorial() {
        return noHistorial;
    }

    public void setNoHistorial(String noHistorial) {
        this.noHistorial = noHistorial;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdPeticionesExternasFormPeticion() {
        return idPeticionesExternasFormPeticion;
    }

    public void setIdPeticionesExternasFormPeticion(String idPeticionesExternasFormPeticion) {
        this.idPeticionesExternasFormPeticion = idPeticionesExternasFormPeticion;
    }

    public String getTipoDeRespuestaPeticion() {
        return tipoDeRespuestaPeticion;
    }

    public void setTipoDeRespuestaPeticion(String tipoDeRespuestaPeticion) {
        this.tipoDeRespuestaPeticion = tipoDeRespuestaPeticion;
    }

    public String getLatitudPeticion() {
        return latitudPeticion;
    }

    public void setLatitudPeticion(String latitudPeticion) {
        this.latitudPeticion = latitudPeticion;
    }

    public String getLongitudPeticion() {
        return longitudPeticion;
    }

    public void setLongitudPeticion(String longitudPeticion) {
        this.longitudPeticion = longitudPeticion;
    }

    /**
     * ****************** Lo siguiente está relacionado al jQuery Grid
     * *************************
     */
    private ArrayList<PeticionesSalientes> gridListaPeticionesExternas;
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
    public ArrayList<PeticionesSalientes> getGridListaPeticionesExternas() {
        return gridListaPeticionesExternas;
    }

    public void setGridListaPeticionesExternas(ArrayList<PeticionesSalientes> gridListaPeticionesExternas) {
        this.gridListaPeticionesExternas = gridListaPeticionesExternas;
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
