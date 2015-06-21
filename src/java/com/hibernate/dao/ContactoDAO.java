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
import com.hibernate.model.Contactos;
import org.hibernate.Query;
import org.hibernate.Session;


public class ContactoDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(Contactos transientInstance) {
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
         
        public Boolean delete(Contactos transientInstance) {
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
        
        public Boolean update(Contactos transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        
			System.out.println("--->Contacto actualizado");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    
			System.out.println("--->Contacto no actualizado");  
                    return false;
		}finally{s.close();}
	}
         
        public Contactos findById(Session s,Long id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			Contactos instance = (Contactos) s.get(
					Contactos.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
        
        public boolean deleteContactoPaciente(Long idContactoPaciente) {
        Session s = getSession();
        try {

            s.beginTransaction();
            Query q = s.createQuery("from Contactos where id = :id");
            q.setParameter("id", idContactoPaciente);
            Contactos contactoPaciente = (Contactos) q.list().get(0);
            s.delete(contactoPaciente);
            s.getTransaction().commit();
            //log.debug("save successful");
            return true;
        } catch (RuntimeException re) {
            //log.error("save failed", re);
            return false;
        } finally {
            s.close();
        }
    }
         
        
      

}
