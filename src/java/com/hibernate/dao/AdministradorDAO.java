/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate.dao;

/**
 *
 * @author Alejandro
 */


import com.hibernate.cfg.HibernateUtil;
import com.hibernate.model.Administradores;
import org.hibernate.Session;


public class AdministradorDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(Administradores transientInstance) {
		//log.debug("saving TblAbwUsuario instance");
		try {
			Session s = getSession();
                        s.beginTransaction();
                        s.save(transientInstance);
                        s.getTransaction().commit();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
			//log.error("save failed", re);  
                    return false;
		}
	}
         
        public Boolean delete(Administradores transientInstance) {
		//log.debug("saving TblAbwUsuario instance");
		try {
			Session s = getSession();
                        s.beginTransaction();
                        s.delete(transientInstance);
                        s.getTransaction().commit();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
			//log.error("save failed", re);
                    return false;
		}
	}
         
        public Administradores findById(String id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			Administradores instance = (Administradores) getSession().get(
					Administradores.class, id);
                        System.out.println(instance.getTelParticular()+" "+instance.getUsuarios().getNombreUsuario());
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
         
        
      

}
