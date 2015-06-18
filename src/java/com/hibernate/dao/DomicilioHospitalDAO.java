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
import com.hibernate.model.DomicilioHospitales;
import org.hibernate.Session;

public class DomicilioHospitalDAO extends HibernateUtil {

	// property constants
    public Boolean save(DomicilioHospitales transientInstance) {
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
        } finally {
            s.close();
        }
    }

    public Boolean delete(DomicilioHospitales transientInstance) {
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
        } finally {
            s.close();
        }
    }

    public Boolean update(DomicilioHospitales transientInstance) {
        Session s = getSession();
        try {

            s.beginTransaction();
            s.update(transientInstance);
            s.getTransaction().commit();
           
            System.out.println("--->Domicikio Hospital actualizado");
            return true;
        } catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
           
            System.out.println("--->Domicilio Hospital no actualizado");
            return false;
        } finally {
            s.close();
        }
    }

    public DomicilioHospitales findById(Session s,Long id) {
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            DomicilioHospitales instance = (DomicilioHospitales)s.get(
                    DomicilioHospitales.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        } finally {
            s.close();
        }
    }

}
