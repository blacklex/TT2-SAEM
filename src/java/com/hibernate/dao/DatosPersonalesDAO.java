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
import com.hibernate.model.DatosPersonales;
import org.hibernate.Session;


public class DatosPersonalesDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(DatosPersonales transientInstance) {
             Session s = getSession();
		//log.debug("saving TblAbwUsuario instance");
		try {
			
                        s.beginTransaction();
                        s.save(transientInstance);
                        s.getTransaction().commit();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
			//log.error("save failed", re);  
                    return false;
		}finally{s.close();}
	}
         
        public Boolean delete(DatosPersonales transientInstance) {
            Session s = getSession();
		//log.debug("saving TblAbwUsuario instance");
		try {
			
                        s.beginTransaction();
                        s.delete(transientInstance);
                        s.getTransaction().commit();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
			//log.error("save failed", re);
                    return false;
		}finally{s.close();}
	}
        
        public Boolean update(DatosPersonales transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        
			System.out.println("--->Datos personales actualizados");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    
			System.out.println("--->Datos personales no actualizados");  
                    return false;
		}finally{s.close();}
	}
         
        public DatosPersonales findById(Session s,Long id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			DatosPersonales instance = (DatosPersonales) s.get(
					DatosPersonales.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
         
        
      

}
