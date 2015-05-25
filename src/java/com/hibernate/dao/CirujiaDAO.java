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
import com.hibernate.model.Cirujias;
import org.hibernate.Session;


public class CirujiaDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(Cirujias transientInstance) {
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
         
        public Boolean delete(Cirujias transientInstance) {
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
        
        public Boolean update(Cirujias transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			System.out.println("--->Cirugia actualizada");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    s.close();
			System.out.println("--->Cirugia no actualizada");  
                    return false;
		}
	}
         
        public Cirujias findById(Long id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			Cirujias instance = (Cirujias) getSession().get(
					Cirujias.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
         
        
      

}
