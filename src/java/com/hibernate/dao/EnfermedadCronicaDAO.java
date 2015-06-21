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
import com.hibernate.model.EnfermedadesCronicas;
import org.hibernate.Query;
import org.hibernate.Session;


public class EnfermedadCronicaDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(EnfermedadesCronicas transientInstance) {
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
         
        public Boolean delete(EnfermedadesCronicas transientInstance) {
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
        
        public Boolean update(EnfermedadesCronicas transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        
			System.out.println("--->Enfermedades actualizadas");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    
			System.out.println("--->Enfermedades no actualizadas");  
                    return false;
		}finally{s.close();}
	}
         
        public EnfermedadesCronicas findById(Session s,Long id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			EnfermedadesCronicas instance = (EnfermedadesCronicas) s.get(
					EnfermedadesCronicas.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}

    public boolean deleteEnfermedadPaciente(Long idEnfermedadPaciente) {
        Session s = getSession();
        try {

            s.beginTransaction();
            Query q = s.createQuery("from EnfermedadesCronicas where id = :id");
            q.setParameter("id", idEnfermedadPaciente);
            EnfermedadesCronicas enfermedadPaciente = (EnfermedadesCronicas) q.list().get(0);
            s.delete(enfermedadPaciente);
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
