/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actions;

import com.opensymphony.xwork2.Action;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class LogOut implements SessionAware{
   private SessionMap<String, Object> session = null;
    
    
    public String execute() {
       System.out.println("\n\nEntro Invalidar sesion" );    
       session.clear();
       session.invalidate(); 
       
       return Action.SUCCESS;
    }

  
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap)map;
    }
  
}
