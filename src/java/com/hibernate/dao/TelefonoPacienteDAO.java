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
import com.hibernate.model.TelefonosPacientes;
import org.hibernate.Session;


public class TelefonoPacienteDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(TelefonosPacientes transientInstance) {
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
         
        public Boolean delete(TelefonosPacientes transientInstance) {
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
        
        public Boolean update(TelefonosPacientes transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			System.out.println("--->Telefonos actualizados");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    s.close();
			System.out.println("--->Telefonos no actualizados");  
                    return false;
		}finally{s.close();}
	}
         
        public TelefonosPacientes findById(Long id) {
            Session s = getSession();
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			TelefonosPacientes instance = (TelefonosPacientes) s.get(
					TelefonosPacientes.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}finally{s.close();}
	}
         
        
      

}
