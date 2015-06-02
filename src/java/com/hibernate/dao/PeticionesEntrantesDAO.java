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
import com.hibernate.model.PeticionesEntrantes;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class PeticionesEntrantesDAO extends HibernateUtil {

	// property constants
    public Boolean save(PeticionesEntrantes transientInstance) {
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
        } finally {
            getSession().close();
        }
    }

    public Boolean delete(PeticionesEntrantes transientInstance) {
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
        } finally {
            getSession().close();
        }
    }

    public PeticionesEntrantes findById(String id) {
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            PeticionesEntrantes instance = (PeticionesEntrantes) getSession().get(
                    PeticionesEntrantes.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        } finally {
            getSession().close();
        }
    }

    public List<PeticionesEntrantes> findAll() {
        try {
            String queryString = "from PeticionesEntrantes";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            throw re;
        } finally {
            getSession().close();
        }
    }

}
