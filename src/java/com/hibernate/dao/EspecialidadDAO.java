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
import com.hibernate.model.Especialidades;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class EspecialidadDAO extends HibernateUtil {

    // property constants
    public Boolean save(Especialidades transientInstance) {
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
            s.close();
        }
    }

    public Boolean delete(Especialidades transientInstance) {
        //log.debug("saving TblAbwUsuario instance");
        Session s = getSession();
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

    public Especialidades findById(int id) {
        Session s = getSession();
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            Especialidades instance = (Especialidades) s.get(
                    Especialidades.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        } finally {
            s.close();
        }
    }

    public List<Especialidades> findAll() {
        Session s = getSession();
        try {
            String queryString = "from Especialidades";
            Query queryObject = s.createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            throw re;
        } finally {
            s.close();
        }
    }

}
