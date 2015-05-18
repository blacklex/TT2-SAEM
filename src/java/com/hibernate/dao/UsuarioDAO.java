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
import com.hibernate.model.Administradores;
import com.hibernate.model.Hospitales;
import com.hibernate.model.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;


public class UsuarioDAO extends HibernateUtil {
	
	// property constants

         public Boolean save(Usuarios transientInstance) {
		//log.debug("saving TblAbwUsuario instance");
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
         
         public Boolean delete(Usuarios transientInstance) {
		//log.debug("saving TblAbwUsuario instance");
		try {
			Session s = getSession();
                        s.beginTransaction();
                        s.delete(transientInstance);
                        s.getTransaction().commit();
                        s.close();
                        System.out.println("--->Usuario eliminado");
			//log.debug("save successful");
                        return true;
		} catch (RuntimeException re) {
                    System.out.println("---->Error no se elimino el usuario");
			//log.error("save failed", re);
                    return false;
		}
	}
         
         public Boolean update(Usuarios transientInstance) {
		Session s = getSession();
		try {
			
                        s.beginTransaction();
                        s.update(transientInstance);
                        s.getTransaction().commit();
                        s.close();
			System.out.println("--->Usuario actualizado");
                        return true;
		} catch (RuntimeException re) {
                    s.close();
			System.out.println("--->Usuario no actualizado");  
                    return false;
		}
	}
         
        public Usuarios findById(String id) {
		//log.debug("getting TblAbwUsuario instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) getSession().get(
					Usuarios.class, id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
        
        public Boolean deleteHospital(String nombreUsuario){
           Session s = getSession();
            try {
                
                s.beginTransaction();
                Query q = s.createQuery("from Usuarios where nombre_usuario = :nombre_usuario");
		q.setParameter("nombre_usuario", nombreUsuario);
                
                Usuarios instance = (Usuarios) q.list().get(0);
                s.delete(instance);
                s.getTransaction().commit();
                s.close();
			return true;
		} catch (RuntimeException re) {
                    s.close();
			return false;
		}
        }
         
        
      

}
