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
import com.hibernate.model.Medicacion;
import org.hibernate.Session;

public class MedicacionDAO extends HibernateUtil {

	// property constants
    public Boolean save(Medicacion transientInstance) {
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

    public Boolean delete(Medicacion transientInstance) {
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

    public Boolean update(Medicacion transientInstance) {
        Session s = getSession();
        try {

            s.beginTransaction();
            s.update(transientInstance);
            s.getTransaction().commit();
            s.close();
            System.out.println("--->Medicacion actualizada");
            return true;
        } catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
            s.close();
            System.out.println("--->Medicacion no actualizada");
            return false;
        } finally {
            s.close();
        }
    }

    public Medicacion findById(Long id) {
        Session s = getSession();
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            Medicacion instance = (Medicacion) s.get(
                    Medicacion.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        } finally {
            s.close();
        }
    }

}
