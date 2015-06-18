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
import com.hibernate.model.Discapacidades;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class DiscapacidadDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(Discapacidades transientInstance) {
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
         
        public Boolean delete(Discapacidades transientInstance) {
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
        
        public Boolean update(Discapacidades transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        
			System.out.println("--->Discapacidades actualizadas");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
              
			System.out.println("--->Discapacidades no actualizadas");  
                    return false;
		}finally{s.close();}
	}
         
        public Discapacidades findById(Session s,Long id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			Discapacidades instance = (Discapacidades) s.get(
					Discapacidades.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
         
        public List<Discapacidades> findAll(Session s) {
        try {
            String queryString = "from Discapacidades";
            Query queryObject = s.createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            throw re;
        } finally {
            s.close();
        }
    }
        
      

}
