/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.UsuarioDAO;
import com.hibernate.model.Usuarios;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gabriela
 */
public class GridRegistroPaciente implements SessionAware{
    protected static final String LISTA_GRID_MODEL = "listaGridModel";
    //HttpServletRequest request = ServletActionContext.getRequest();
    private Map<String, Object> session = null;
     //Your result List
    private List<Usuarios> gridModel;
    
    private UsuarioDAO usuarios = new UsuarioDAO();

    //get how many rows we want to have into the grid - rowNum attribute in the grid
    private Integer rows = 0;

    //Get the requested page. By default grid sets this to 1.
    private Integer page = 0;

    // sorting order - asc or desc
    private String sord;

    // get index row - i.e. user click to sort.
    private String sidx;

    // Search Field
    private String searchField;

    // The Search String
    private String searchString;

    // he Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;

    // Your Total Pages
    private Integer total = 0;

    // All Record
    private Integer records = 0;
    
     /*Campos para obtener los valores de la tabla del grid, deben llamarce igual a los nombre de las columnas (name)*/
    private String nombreUsuario;
    private String tipoUsuario;
    private String clave;
    private Date fechaRegistro;

    //Variables para la parte de los botones por default del grid oper para saber
    //que operacion es y id para saber q registro del grid se modifica o elimina 
    private String oper;
    private String id;
    //Todos con sus sets y gets para que puedan ser accesibles
   
    //Esta variable se ocupa para el resultado de cada operacion (debe tener su set y get)
    //con ella cada que se ejecuta un ajax podemos recuperar el resultado de la operacion
    //con $.parseJSON(request.responseText).resutaldoOper; ya que es de tipo json
    private String resultadoOper;

    


    public void llenarGrid(){
         
         ArrayList<Usuarios> listaTemp = new ArrayList<Usuarios>();
        ArrayList<Usuarios> listaTempFinal = new ArrayList<Usuarios>();

        if (session.get(LISTA_GRID_MODEL) != null) {
                listaTemp = (ArrayList<Usuarios>) session.get(LISTA_GRID_MODEL);
                System.out.println("----> "+listaTemp.size());
                session.remove(LISTA_GRID_MODEL);
            } else {
                return;
                // Obtenemos la lista de la sesión
                //listaTemp = (ArrayList<Usuarios>) usuarios.listarPacientes(0, 0);
            }
        

        for (Usuarios tempContUsuario : listaTemp) {

            listaTempFinal.add(new Usuarios(tempContUsuario.getNombreUsuario(), tempContUsuario.getTipoUsuario(), "", tempContUsuario.getFechaRegistro()));
        }
        gridModel = listaTempFinal;
        if (gridModel == null) {
            records = total = 0;
        } else {
            // Obtenemos el total de registros
            records = gridModel.size();
            // Calculamos el total de páginas necesarias
            total = (int) Math.ceil((double) records / (double) rows);
        }
    }
    
    public String execute() {
//        if(session.get(LISTA_GRID_MODEL)!=null){
//            if(((List<Usuarios>)session.get(LISTA_GRID_MODEL)).size()>0){
//                gridModel=(List<Usuarios>)session.get(LISTA_GRID_MODEL);
//                return SUCCESS;
//            }
//        }
        llenarGrid();
        return SUCCESS;
    } 

  //Getters and Setters for Attributes
    
    public List<Usuarios> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<Usuarios> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultadoOper() {
        return resultadoOper;
    }

    public void setResultadoOper(String resultadoOper) {
        this.resultadoOper = resultadoOper;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }
}
