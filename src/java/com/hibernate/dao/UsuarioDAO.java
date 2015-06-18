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
import static com.hibernate.cfg.HibernateUtil.getSession;
import com.hibernate.model.Usuarios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class UsuarioDAO extends HibernateUtil {

	// property constants
    public Boolean save(Usuarios transientInstance) {
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

    public Boolean delete(Usuarios transientInstance) {
        Session s = getSession();
        //log.debug("saving TblAbwUsuario instance");
        try {

            s.beginTransaction();
            s.delete(transientInstance);
            s.getTransaction().commit();

            System.out.println("--->Usuario eliminado");
            //log.debug("save successful");
            return true;
        } catch (RuntimeException re) {
            System.out.println("---->Error no se elimino el usuario");
            //log.error("save failed", re);
            return false;
        } finally {
            s.close();
        }
    }

    public Boolean update(Usuarios transientInstance) {
        Session s = getSession();
        try {

            s.beginTransaction();
            s.update(transientInstance);
            s.getTransaction().commit();

            System.out.println("--->Usuario actualizado");
            return true;
        } catch (RuntimeException re) {

            System.out.println("--->Usuario no actualizado");
            return false;
        } finally {
            s.close();
        }
    }

    public Usuarios findById(Session s, String id) {
        //log.debug("getting TblAbwUsuario instance with id: " + id);
        try {
            Usuarios instance = (Usuarios) s.get(
                    Usuarios.class, id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        }
    }

    public Boolean deleteHospital(String nombreUsuario) {
        Session s = getSession();
        try {

            s.beginTransaction();
            Query q = s.createQuery("from Usuarios where nombre_usuario = :nombre_usuario");
            q.setParameter("nombre_usuario", nombreUsuario);

            Usuarios instance = (Usuarios) q.list().get(0);
            s.delete(instance);
            s.getTransaction().commit();

            return true;
        } catch (RuntimeException re) {

            return false;
        } finally {
            s.close();
        }
    }

    public boolean deleteAdmin(String nombreUsuarioEdit) {
        Session s = getSession();
        try {

            s.beginTransaction();
            Query q = s.createQuery("from Usuarios where nombre_usuario = :nombre_usuario");
            q.setParameter("nombre_usuario", nombreUsuarioEdit);
            Usuarios usuario = (Usuarios) q.list().get(0);
            s.delete(usuario);
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

    public boolean deletePaciente(String nombreUsuarioEdit) {
        Session s = getSession();
        try {

            s.beginTransaction();
            Query q = s.createQuery("from Usuarios where nombre_usuario = :nombre_usuario");
            q.setParameter("nombre_usuario", nombreUsuarioEdit);
            Usuarios usuario = (Usuarios) q.list().get(0);
            s.delete(usuario);
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

    public List<Usuarios> listarById(Session s, String id) {
        
        try {
            String queryString = "from Usuarios where nombreUsuario='" + id + "'";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            throw re;
        }
//        try {
//            System.err.println("Bandera 1");
//
//            System.err.println("Bandera 2");
//            s.beginTransaction();
//            System.err.println("Bandera 3");
//            List<Usuarios> listUsuarios = (List<Usuarios>) s.createQuery("from Usuarios where nombreUsuario='" + id + "'").list();
//            System.err.println("Bandera 4");
//            s.getTransaction().commit();
//            System.err.println("Bandera 5");
//            return listUsuarios;
//
//        } catch (Exception e) {
//            return null;
//        }

    }

    public List<Usuarios> listar(Session s, int from, int to) {
        try {
            String queryString = "from Usuarios where tipoUsuario='Administrador'";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            throw re;
        }
//        try {
//            System.err.println("Bandera 1");
//
//            System.err.println("Bandera 2");
//            s.beginTransaction();
//            System.err.println("Bandera 3");
//            List<Usuarios> listUsuarios = (List<Usuarios>) s.createQuery("from Usuarios where tipoUsuario='Administrador'").list();
//            System.err.println("Bandera 4");
//            s.getTransaction().commit();
//            System.err.println("Bandera 5");
//            return listUsuarios;
//
//        } catch (Exception e) {
//            return null;
//        }

    }

    public List<Usuarios> findUsuariosLike(Session s, String flitroNombreUsuario) {
        try {
            String queryString = "from Usuarios where tipoUsuario='Administrador'and lower(nombreUsuario) LIKE (:searchKeyword)";
            Query queryObject = s.createQuery(queryString);
            queryObject.setParameter("searchKeyword", "%" + flitroNombreUsuario + "%");
            return queryObject.list();

        } catch (RuntimeException re) {
            throw re;
        }

    }

    public List<Usuarios> findPacientesLike(Session s, String flitroNombreUsuario) {
        try {
            String queryString = "from Usuarios where tipoUsuario='Paciente'and lower(nombreUsuario) LIKE (:searchKeyword)";
            Query queryObject = s.createQuery(queryString);
            queryObject.setParameter("searchKeyword", "%" + flitroNombreUsuario + "%");
            return queryObject.list();

        } catch (RuntimeException re) {
            throw re;
        }

    }

    public List<Usuarios> findPacientesPorHospitalLike(Session s, String flitroNombreUsuario, String codigoHospital) {
        try {
            List<Usuarios> usuarioslike = null;
            String queryString = "SELECT {u.*} FROM Usuarios u, Pacientes p where u.nombre_usuario = p.Usuarios_nombre_usuario and u.nombre_usuario LIKE '%" + flitroNombreUsuario + "%' and p.Hospitales_codigo_hospital = '" + codigoHospital + "'";
            SQLQuery query = s.createSQLQuery(queryString);
            query.addEntity("u", Usuarios.class);
            usuarioslike = query.list();

            return usuarioslike;

        } catch (RuntimeException re) {
            throw re;
        }

    }

    public List<Usuarios> listarPacientes(Session s, int from, int to) {
        try {
            String queryString = "from Usuarios where tipoUsuario='Paciente'";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            throw re;
        }
//        try {
//            System.err.println("Bandera 1");
//
//            System.err.println("Bandera 2");
//            s.beginTransaction();
//            System.err.println("Bandera 3");
//            List<Usuarios> listUsuarios = (List<Usuarios>) s.createQuery("from Usuarios where tipoUsuario='Paciente'").list();
//            System.err.println("Bandera 4");
//            s.getTransaction().commit();
//            System.err.println("Bandera 5");
//            return listUsuarios;
//
//        } catch (Exception e) {
//            return null;
//        }

    }

    public List<Usuarios> listarPacientesPorHospital(Session s, int from, int to, String codigoHospital) {
        try {
            List<Usuarios> usuarios = null;
            String queryString = "SELECT {u.*} FROM Usuarios u, Pacientes p where u.nombre_usuario = p.Usuarios_nombre_usuario and p.Hospitales_codigo_hospital = '" + codigoHospital + "'";
            SQLQuery query = s.createSQLQuery(queryString);
            query.addEntity("u", Usuarios.class);
            usuarios = query.list();

            return usuarios;

        } catch (RuntimeException re) {
            throw re;
        }

    }

}
