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
import com.hibernate.model.Hospitales;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class HospitalDAO extends HibernateUtil {
	
	// property constants


        public Boolean save(Hospitales  transientInstance) {
		try {
			Session s = getSession();
                        s.beginTransaction();
                        s.save(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
			//log.error("save failed", re);  
                    return false;
		}
	}
           
         public Boolean delete(Hospitales transientInstance) {
		try {
			Session s = getSession();
                        s.beginTransaction();
                        s.delete(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
			//log.error("save failed", re);
                    return false;
		}
	}
         
         public Boolean update(Hospitales transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			System.out.println("--->Hospital actualizado");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    s.close();
			System.out.println("--->Hospital no actualizado");  
                    return false;
		}
	}
         
        public Hospitales findById(int id) {
		try {
			Hospitales instance = (Hospitales) getSession().get(
					Hospitales.class, id);
                        getSession().close();
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
         
        public List<Hospitales> findAll() {
		try {
			String queryString = "from Hospitales";
			Query queryObject = getSession().createQuery(queryString);
                        getSession().close();
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
      

}
