/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.hibernate.dao.HospitalDAO;
import com.hibernate.model.Hospitales;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author sergio
 */
public class BuscarHospitales implements SessionAware {
    
    private Map<String, Object> session = null;
    HttpServletRequest request = ServletActionContext.getRequest();
    
    String latitudUsuario;
    String longitudUsuario;
    String distancia;
    
    public String execute() {
        ArrayList<Hospitales> listaTemp = new ArrayList<Hospitales>();
        String ONTOLOGIA = request.getServletContext().getRealPath("/") + "WEB-INF/serviciomedico.owl";
        String BASE_URI = "http://www.serviciomedico.org/ontologies/2014/serviciomedico";
        
        HospitalDAO hospitalDAO = new HospitalDAO();
        listaTemp = (ArrayList<Hospitales>) hospitalDAO.findAll();
        
        for(Hospitales hospTem : listaTemp){
            System.out.println(hospTem.getNombre());
        }
        
        System.out.println("Latitud: " + Double.parseDouble(latitudUsuario) + "\n" +
                           "Longitud: " + Double.parseDouble(longitudUsuario) + "\n" + 
                           "Distancia: " + Double.parseDouble(distancia));
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(String latitudUsuario) {
        this.latitudUsuario = latitudUsuario;
    }

    public String getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(String longitudUsuario) {
        this.longitudUsuario = longitudUsuario;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
    
    
}
