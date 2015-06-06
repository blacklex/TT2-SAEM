/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.HospitalDAO;
import com.hibernate.dao.PeticionesEntrantesDAO;
import com.hibernate.dao.PeticionesSalientesDAO;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.Hospitales;
import com.hibernate.model.PeticionesEntrantes;
import com.hibernate.model.PeticionesSalientes;
import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class PeticionesEntrantesAction implements SessionAware {

    private static final String LISTA_HOSPITALES = "LISTA_HOSPITALES_ME";
    private static final String LLAVE_ESTATUS_ME = "PETICIONES_ENTRANTES_ESTATUS_LLAVE";

    //campos del formulario
    //fin de campos formularo
    private String tituloAlert = "";
    private String textoAlert = "";
    private String estatusMensaje = "";

    private String totalPeticionesEntrantes;
    private String totalPeticionesSalientes;

    HttpServletRequest request = ServletActionContext.getRequest();
    private Map<String, Object> session = null;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute() {
        System.out.println("-->Entro a peticones Entatens execute");
        return "pantallaPeticionesEntrantesHospital";
    }

    /**
     * ********************** METODOS PARA SETAR EN FORMULARIOS
     * ********************
     */
    public String recuperarEstatusPeticionesEntrantesHospital() {
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

    /**
     * *************************************** METODOS GRID
     * ********************************
     */
    public String llenarGridPeticionesEntrantes() {

        System.out.println("\n\n--->Entro a llenar tabla Peticiones Entrantes");

        // Obtenemos la tabla desordenada
        obteneTablaPeticionesEntrantes();
        // Quitamos los registros que no se desplegarán en el grid
        recortarTablaPeticionesEntrantes();

        return Action.SUCCESS;
    }

    private void obteneTablaPeticionesEntrantes() {
        PeticionesEntrantesDAO peticionesEntrantesDAO = new PeticionesEntrantesDAO();
        ArrayList<PeticionesEntrantes> listaTemp = new ArrayList<PeticionesEntrantes>();
        ArrayList<PeticionesEntrantes> listaTempFinal = new ArrayList<PeticionesEntrantes>();
        String codigoHosptail = (String) session.get("HospitalCodigoHospital");

        if (codigoHosptail == null) {
            return;
        }

        // Obtenemos la lista de la sesión
        listaTemp = (ArrayList<PeticionesEntrantes>) peticionesEntrantesDAO.findAllByHospital(codigoHosptail);

        System.out.println("---> Tam pet Entra " + listaTemp.size());

        for (PeticionesEntrantes tempContHosp : listaTemp) {
            PeticionesEntrantes tempPet = new PeticionesEntrantes(tempContHosp.getIdPeticionesEntrantes(), null, null, tempContHosp.getFechaRegistro(), tempContHosp.getEstatus(), tempContHosp.getLatitudPaciente(), tempContHosp.getLongitudPaciente(), tempContHosp.getPrioridad());
            tempPet.setNombrePaciente(tempContHosp.getPacientes().getNombre());
            tempPet.setApellidoPaciente(tempContHosp.getPacientes().getApellidoPaterno());
            tempPet.setNss(tempContHosp.getPacientes().getNss());

            Iterator<DatosPersonales> iterDatosPerPac = tempContHosp.getPacientes().getDatosPersonaleses().iterator();
            if (iterDatosPerPac.hasNext()) {
                tempPet.setFechaNacimineto(iterDatosPerPac.next().getFechaNacimiento());
            }

            listaTempFinal.add(tempPet);
        }
        gridListaPeticionesEntrantes = listaTempFinal;
        if (gridListaPeticionesEntrantes == null) {
            records = total = 0;
        } else {
            // Obtenemos el total de registros
            records = gridListaPeticionesEntrantes.size();
            // Calculamos el total de páginas necesarias
            total = (int) Math.ceil((double) records / (double) rows);
        }
    }

    public String recuperarTotalPeticionesEntrantes() {
        PeticionesEntrantesDAO peticionesEntrantesDAO = new PeticionesEntrantesDAO();
        ArrayList<PeticionesEntrantes> listaTempEnt = new ArrayList<PeticionesEntrantes>();
        
        PeticionesSalientesDAO peticionesSalientesDAO = new PeticionesSalientesDAO();
        ArrayList<PeticionesSalientes> listaTempSal = new ArrayList<PeticionesSalientes>();
        
        String codigoHosptail = (String) session.get("HospitalCodigoHospital");

        listaTempEnt = (ArrayList<PeticionesEntrantes>) peticionesEntrantesDAO.findAllByHospital(codigoHosptail);
        if (listaTempEnt == null) {
            listaTempEnt = new ArrayList<PeticionesEntrantes>();
        }
        
        listaTempSal = (ArrayList<PeticionesSalientes>) peticionesSalientesDAO.findAllByHospital(codigoHosptail);
        if (listaTempSal == null) {
            listaTempSal = new ArrayList<PeticionesSalientes>();
        }

        totalPeticionesEntrantes = listaTempEnt.size() + "";
        totalPeticionesSalientes = listaTempSal.size() + "";

        return SUCCESS;
    }

    private void recortarTablaPeticionesEntrantes() {
        // Calculamos el inicio de los registros a mostrar
        desde = (page * rows) - rows;
        // Calculamos el final de los registros a mostrar
        hasta = page * rows > records ? records - 1 : page * rows - 1;

        Iterator<PeticionesEntrantes> it = gridListaPeticionesEntrantes.iterator();

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

    public String getTotalPeticionesEntrantes() {
        return totalPeticionesEntrantes;
    }

    public void setTotalPeticionesEntrantes(String totalPeticionesEntrantes) {
        this.totalPeticionesEntrantes = totalPeticionesEntrantes;
    }

    public String getTotalPeticionesSalientes() {
        return totalPeticionesSalientes;
    }

    public void setTotalPeticionesSalientes(String totalPeticionesSalientes) {
        this.totalPeticionesSalientes = totalPeticionesSalientes;
    }

    
    /**
     * ****************** Lo siguiente está relacionado al jQuery Grid
     * *************************
     */
    private ArrayList<PeticionesEntrantes> gridListaPeticionesEntrantes;
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
    public ArrayList<PeticionesEntrantes> getGridListaPeticionesEntrantes() {
        return gridListaPeticionesEntrantes;
    }

    public void setGridListaPeticionesEntrantes(ArrayList<PeticionesEntrantes> gridListaPeticionesEntrantes) {
        this.gridListaPeticionesEntrantes = gridListaPeticionesEntrantes;
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
