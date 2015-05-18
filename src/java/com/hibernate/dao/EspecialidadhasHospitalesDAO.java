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
import com.hibernate.model.EspecialidadesHasHospitales;
import org.hibernate.Session;


public class EspecialidadhasHospitalesDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(EspecialidadesHasHospitales transientInstance) {
             Session s = getSession();
		//log.debug("saving TblAbwUsuario instance");
		try {
			
                        s.beginTransaction();
                        s.save(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
                    s.close();
			//log.error("save failed", re);  
                    return false;
		}
	}
         
        public Boolean delete(EspecialidadesHasHospitales transientInstance) {
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
         
        public EspecialidadesHasHospitales findById(int id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			EspecialidadesHasHospitales instance = (EspecialidadesHasHospitales) getSession().get(
					EspecialidadesHasHospitales.class, id);
                        getSession().close();
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
        
        public Boolean update(EspecialidadesHasHospitales transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			System.out.println("--->relacion Especialidad Hospital actualizado");
                        return true;
		} catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
                    s.close();
			System.out.println("--->relacion Especialidad Hospital no actualizado");  
                    return false;
		}
	}
       
         
        
      

}
