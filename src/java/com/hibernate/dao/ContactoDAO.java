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
import org.hibernate.Session;

public class ContactoDAO extends HibernateUtil {

	// property constants
    public Boolean save(Contactos transientInstance) {
        //log.debug("saving TblAbwUsuario instance");
        Session s = getSession();
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
        } finally {
            s.close();
        }
    }

    public Boolean update(Contactos transientInstance) {
        Session s = getSession();
        try {

            s.beginTransaction();
            s.update(transientInstance);
            s.getTransaction().commit();
            s.close();
            System.out.println("--->Contacto actualizado");
            return true;
        } catch (RuntimeException re) {
//                    System.out.println(re.getCause().getMessage());
            s.close();
            System.out.println("--->Contacto no actualizado");
            return false;
        } finally {
            s.close();
        }
    }

    public Contactos findById(Long id) {
        Session s = getSession();
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            Contactos instance = (Contactos) s.get(
                    Contactos.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        } finally {
            s.close();
        }
    }

}
