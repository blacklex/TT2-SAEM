/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.PeticionesSalientesDAO;
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
public class PeticionesExternasAction implements SessionAware {

    private static final String LISTA_HOSPITALES = "LISTA_HOSPITALES_ME";
    private static final String LLAVE_ESTATUS_ME = "PETICIONES_EXTERNAS_ESTATUS_LLAVE";

   
    //campos del formulario
   
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

    
/************************ METODOS PARA SETAR EN FORMULARIOS *********************/
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
        session.remove(LLAVE_ESTATUS_ME);
        session.put(LLAVE_ESTATUS_ME, null);

        return SUCCESS;
    }
    /**
     * *************************************** METODOS GRID
     * ********************************
     */
    public String llenarGridPeticionesExternas() {

        System.out.println("\n\n--->Entro a llenar tabla Peticiones Externas");
        if(true)
            return Action.SUCCESS;
        // Obtenemos la tabla desordenada
        obteneTablaPeticionesExternas();
        // Quitamos los registros que no se desplegarán en el grid
        recortarTablaPeticionesExternas();

        return Action.SUCCESS;
    }

    private void obteneTablaPeticionesExternas() {
        PeticionesSalientesDAO peticionesSalientesDAO = new PeticionesSalientesDAO();
        ArrayList<PeticionesSalientes> listaTemp = new ArrayList<PeticionesSalientes>();
        ArrayList<PeticionesSalientes> listaTempFinal = new ArrayList<PeticionesSalientes>();

        // Obtenemos la lista de la sesión
        listaTemp = (ArrayList<PeticionesSalientes>) peticionesSalientesDAO.findAll();

        for (PeticionesSalientes tempContHosp : listaTemp) {

            listaTempFinal.add(null);
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