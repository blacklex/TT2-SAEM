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
import com.hibernate.model.DomicilioAdministradores;
import org.hibernate.Session;

public class DomicilioAdministradorDAO extends HibernateUtil {

	// property constants
    public Boolean save(DomicilioAdministradores transientInstance) {
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

    public Boolean update(DomicilioAdministradores transientInstance) {
        Session s = getSession();
        //log.debug("saving TblAbwUsuario instance");
        try {

            s.beginTransaction();
            s.update(transientInstance);
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

    public Boolean delete(DomicilioAdministradores transientInstance) {
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

    public DomicilioAdministradores findById(Session s, Long id) {
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            DomicilioAdministradores instance = (DomicilioAdministradores) s.get(
                    DomicilioAdministradores.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        } finally {
            s.close();
        }
    }

    public DomicilioAdministradores findByClaveForanea(Session s, String id) {
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            DomicilioAdministradores domAdmins;

            s.beginTransaction();
            domAdmins = (DomicilioAdministradores) s.createQuery("from DomicilioAdministradores where Administradores_tel_particular = '" + id + "'").uniqueResult();
            s.getTransaction().commit();
            return domAdmins;
        } catch (Exception e) {
            return null;
        } finally {
            s.close();
        }
    }

}
