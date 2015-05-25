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
import com.hibernate.model.Pacientes;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.Query;


public class PacienteDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(Pacientes transientInstance) {
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
         
        public Boolean delete(Pacientes transientInstance) {
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
        
        public Boolean update(Pacientes transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			System.out.println("--->Paciente actualizado");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    s.close();
			System.out.println("--->Paciente no actualizado");  
                    return false;
		}
	}
         
        public Pacientes findById(int id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			Pacientes instance = (Pacientes) getSession().get(
					Pacientes.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
        
        public List<Pacientes> findAll() {
		try {
			String queryString = "from Pacientes";
			Query queryObject = getSession().createQuery(queryString);
                        getSession().close();
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
         
        
      

}
