/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.actionjasper;

import com.hibernate.dao.PeticionesEntrantesDAO;
import com.hibernate.dao.PeticionesSalientesDAO;
import com.hibernate.model.Alergias;
import com.hibernate.model.Cirugias;
import com.hibernate.model.DatosClinicos;
import com.hibernate.model.DatosPersonales;
import com.hibernate.model.Discapacidades;
import com.hibernate.model.EnfermedadesCronicas;
import com.hibernate.model.Medicacion;
import com.hibernate.model.Pacientes;
import com.hibernate.model.PeticionesEntrantes;
import com.hibernate.model.PeticionesSalientes;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

/**
 *
 * @author Alejandro
 */
public class reporteInformacionPaciente extends ActionSupport implements SessionAware, ServletRequestAware {

    private String idPeticion;
    private String tipoPeticion;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String unidadMedica;
    private String noConsultorio;
    private String estadoCivil;
    private Long noHistorial;
    private String altura;
    private String peso;
    private String edad;
    private String curp;
    private String genero;
    private String fuma;
    private String usoAlcohol;
    private String usoDrogas;
    private String nss;
    private String nombrePaciente;
    private Map params = new HashMap();

    private List<EnfermedadesCronicas> enfermedades = new ArrayList<EnfermedadesCronicas>();
    private List<Medicacion> medicacion = new ArrayList<Medicacion>();
    private List<Alergias> alergias = new ArrayList<Alergias>();
    private List<Cirugias> cirugias = new ArrayList<Cirugias>();
    private List<Discapacidades> discapacidades = new ArrayList<Discapacidades>();
    //HttpServletRequest request = ServletActionContext.getRequest();

    private Map<String, Object> session = null;
    private HttpServletRequest request;

    /**
     * List to use as our JasperReports dataSource.
     */
    public InputStream inputStream;

    public String execute() throws Exception {
        Session s = com.hibernate.cfg.HibernateUtil.getSession();
        String rutaReporte = request.getServletContext().getRealPath("/") + "WEB-INF/reportePaciente.jasper";
        Pacientes paciente;
        PeticionesEntrantesDAO peticionesEntrantesDAO = new PeticionesEntrantesDAO();
        PeticionesSalientesDAO peticionesSalientesDAO = new PeticionesSalientesDAO();
        System.out.println("------------------> " + idPeticion + "   " + tipoPeticion);

        if (tipoPeticion.equals("externa")) {
            PeticionesSalientes petSaliente = peticionesSalientesDAO.findById(s, idPeticion);
            paciente = petSaliente.getPacientes();

        } else if (tipoPeticion.equals("entrante")) {
            PeticionesEntrantes petSaliente = peticionesEntrantesDAO.findById(s, idPeticion);
            paciente = petSaliente.getPacientes();
        } else {
            s.close();
            return SUCCESS;
        }

        nss = paciente.getNss();
        nombrePaciente = paciente.getNombre();
        apellidoPaterno = paciente.getApellidoPaterno();
        apellidoMaterno = paciente.getApellidoMaterno();
        unidadMedica = paciente.getUnidadMedica();
        noConsultorio = paciente.getNoConsultorio();

        if (paciente.getDatosPersonaleses().iterator().hasNext()) {

            DatosPersonales datosPersonales = (DatosPersonales) paciente.getDatosPersonaleses().iterator().next();
            altura = datosPersonales.getAltura();
            peso = datosPersonales.getPeso();
            edad = datosPersonales.getEdad();
            curp = datosPersonales.getCurp();
            genero = datosPersonales.getSexo();
            estadoCivil = datosPersonales.getEstadoCivil();

        }

        if (paciente.getDatosClinicoses().iterator().hasNext()) {
            DatosClinicos datosClinicos = (DatosClinicos) paciente.getDatosClinicoses().iterator().next();
            noHistorial = ((DatosClinicos) paciente.getDatosClinicoses().iterator().next()).getNoHistorial();
            if (datosClinicos.isFumador()) {
                fuma = "Si";
            } else {
                fuma = "No";
            }

            if (datosClinicos.isUsoAlcohol()) {
                usoAlcohol = "Si";
            } else {
                usoAlcohol = "No";
            }

            if (datosClinicos.isUsoDrogas()) {
                usoDrogas = "Si";
            } else {
                usoDrogas = "No";
            }

            Iterator iterEnfermedades = datosClinicos.getEnfermedadesCronicases().iterator();
            if (iterEnfermedades != null) {
                while (iterEnfermedades.hasNext()) {
                    enfermedades.add((EnfermedadesCronicas) iterEnfermedades.next());
                }
            }
            Iterator iterAlergias = datosClinicos.getAlergiases().iterator();
            if (iterAlergias != null) {
                while (iterAlergias.hasNext()) {
                    alergias.add((Alergias) iterAlergias.next());
                }
            }
            Iterator iterMedicacion = datosClinicos.getMedicacions().iterator();
            if (iterMedicacion != null) {
                while (iterMedicacion.hasNext()) {
                    medicacion.add((Medicacion) iterMedicacion.next());
                }
            }

            Iterator iterDicapacidades = datosClinicos.getDiscapacidadeses().iterator();
            if (iterDicapacidades != null) {
                while (iterDicapacidades.hasNext()) {
                    Discapacidades discapTemp = (Discapacidades) iterDicapacidades.next();
                    discapacidades.add(new Discapacidades(null, discapTemp.getTipo()));
                }
            }

            Iterator iterCirugias = datosClinicos.getCirugiases().iterator();
            if (iterCirugias != null) {
                while (iterCirugias.hasNext()) {
                    Cirugias cirugiaTemp = (Cirugias) iterCirugias.next();
                    cirugias.add(new Cirugias(null, cirugiaTemp.getTipoCirugia(), cirugiaTemp.getNoCirugia()));
                }
            }

        }

        params.put("noHistorial", noHistorial);
        params.put("nss", nss);
        params.put("nombrePaciente", nombrePaciente);
        params.put("apellidoPaterno", apellidoPaterno);
        params.put("apellidoMaterno", apellidoMaterno);
        params.put("unidadMedica", unidadMedica);
        params.put("noConsultorio", noConsultorio);
        params.put("estadoCivil", estadoCivil);
        params.put("genero", genero);
        params.put("curp", curp);
        params.put("edad", edad);
        params.put("peso", peso);
        params.put("altura", altura);
        params.put("usoDrogas", usoDrogas);
        params.put("usoAlcohol", usoAlcohol);
        params.put("fuma", fuma);

        params.put("enfermedades", enfermedades);
        params.put("medicacion", medicacion);
        params.put("alergias", alergias);
        params.put("cirugias", cirugias);
        params.put("discapacidades", discapacidades);

        try {
            net.sf.jasperreports.engine.JasperReport report = (net.sf.jasperreports.engine.JasperReport) JRLoader.loadObjectFromFile(rutaReporte);
            JasperPrint printer = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
            byte[] bytes = null;
            bytes = JasperExportManager.exportReportToPdf(printer);//JasperRunManager.runReportToPdf(report, params);
            inputStream = new ByteArrayInputStream(bytes);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
        s.close();
        return SUCCESS;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    public String getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }

    public String getTipoPeticion() {
        return tipoPeticion;
    }

    public void setTipoPeticion(String tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

}
