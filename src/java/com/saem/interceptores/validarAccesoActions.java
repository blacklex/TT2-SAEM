/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.interceptores;

import static com.opensymphony.xwork2.Action.LOGIN;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Alejandro
 */
public class validarAccesoActions implements Interceptor {

    private Map<String, Object> session;
    private ArrayList<String> listaActionMenuAdmin = new ArrayList<String>();
    private ArrayList<String> listaActionMenuPaciente = new ArrayList<String>();
    private ArrayList<String> listaActionMenuHospital = new ArrayList<String>();
    private HttpServletRequest request = ServletActionContext.getRequest();

    public validarAccesoActions() {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        session = ActionContext.getContext().getSession();

        if (session == null) {
            return LOGIN;
        }
        String nombreActionActual = actionInvocation.getProxy().getActionName();

        if (nombreActionActual.equals("Login")) {
            return actionInvocation.invoke();
        }

        if (session.get("tipoUsuario") == null || session.get("grantAccess") == null) {
            return LOGIN;
        }

        String tipoUsuario = session.get("tipoUsuario").toString();
        String grantAccess = session.get("grantAccess").toString();

        if (!grantAccess.equals("grantAccess")) {
            return LOGIN;
        }

        if (tipoUsuario.equals("Paciente")) {

            for (String opcionAdmin : listaActionMenuAdmin) {
                if (opcionAdmin.equals(nombreActionActual)) {
                    return LOGIN;
                }
            }

            for (String opcionHospital : listaActionMenuHospital) {
                if (opcionHospital.equals(nombreActionActual)) {
                    return LOGIN;
                }
            }
            return actionInvocation.invoke();
        }

        if (tipoUsuario.equals("Hospital")) {

            for (String opcionAdmin : listaActionMenuAdmin) {
                if (opcionAdmin.equals(nombreActionActual)) {
                    return LOGIN;
                }
            }

            for (String opcionPaciente : listaActionMenuPaciente) {
                if (opcionPaciente.equals(nombreActionActual)) {
                    return LOGIN;
                }
            }
            return actionInvocation.invoke();
        }

        if (tipoUsuario.equals("Administrador")) {

            for (String opcionPaciente : listaActionMenuPaciente) {
                if (opcionPaciente.equals(nombreActionActual)) {
                    return LOGIN;
                }
            }

            for (String opcionHospital : listaActionMenuHospital) {
                if (opcionHospital.equals(nombreActionActual)) {
                    return LOGIN;
                }
            }
            return actionInvocation.invoke();

        }

        return LOGIN;
    }

    @Override
    public void destroy() {
        System.out.println("--->Destruyendo");
    }

    @Override
    public void init() {
        System.out.println("--->Inicicando");

        listaActionMenuAdmin.add("pantallaAltaAdministrador");
        listaActionMenuAdmin.add("pantallaModificarEliminarAdministrador");
        listaActionMenuAdmin.add("pantallaConsultarAdministrador");
        listaActionMenuAdmin.add("pantallaRegistroHospital");
        listaActionMenuAdmin.add("pantallaModificarEliminarHospital");
        listaActionMenuAdmin.add("pantallaConsultarHospitales");
        listaActionMenuAdmin.add("pantallaAltaPaciente");
        listaActionMenuAdmin.add("pantallaModificarEliminarPaciente");
        listaActionMenuAdmin.add("pantallaConsultarPacientes");

        listaActionMenuHospital.add("pantallaAltaPacientePorHospital");
        listaActionMenuHospital.add("pantallaModificarEliminarPacientePorHospital");
        listaActionMenuHospital.add("pantallaConsultarPacientesPorHospital");
        listaActionMenuHospital.add("pantallaPeticionesEntrantesHospital");
        listaActionMenuHospital.add("pantallaPeticionesExternasHospital");
        listaActionMenuHospital.add("pantallaConsultarInformacionHospital");

        listaActionMenuPaciente.add("pantallaMapaPaciente");
        listaActionMenuPaciente.add("pantallaConsultarMiInformacion");
    }

}
