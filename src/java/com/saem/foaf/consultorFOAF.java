/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saem.foaf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.FileManager;
import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class consultorFOAF {

    private final Model model = ModelFactory.createDefaultModel();
    private final String BASE_URI = "http://www.saem.com/foaf/pacientes#";
    private String archivoRDF = "";

    private String nombrePersona;

    public consultorFOAF(String nombrePersona, String rutaArchivoRDF) {
        archivoRDF = rutaArchivoRDF;
        this.nombrePersona = nombrePersona;
    }

    public ArrayList<PersonaFOAF> consultarAmigos() {
        FileManager.get().readModel(model, archivoRDF);
        ArrayList<PersonaFOAF> listaPersonas = new ArrayList<PersonaFOAF>();

        Resource person = model.getResource(BASE_URI + nombrePersona);

        // get all objects of foaf:knows where I am the subject
        StmtIterator myFriends = person.listProperties(FOAF.knows);
        while (myFriends.hasNext()) {
            PersonaFOAF personaTemp = new PersonaFOAF();
            Resource friend = myFriends.nextStatement().getResource();

            personaTemp.setApellidosPersona(friend.getProperty(FOAF.family_name).getLiteral().getString());
            personaTemp.setCorreoPersona(friend.getProperty(FOAF.mbox).getResource().toString().split(":")[1]);
            personaTemp.setFacebookPersona(friend.getProperty(FOAF.holdsAccount).getResource().toString());
            personaTemp.setNombrePersona(friend.getProperty(FOAF.name).getLiteral().getString());
            personaTemp.setTelefonoPersona(friend.getProperty(FOAF.phone).getResource().toString().split(":")[1]);

            listaPersonas.add(personaTemp);

        }

        return listaPersonas;
    }

    public PersonaFOAF consultarPersona() {
        FileManager.get().readModel(model, archivoRDF);
        PersonaFOAF personaTemp = new PersonaFOAF();

        Resource person = model.getResource(BASE_URI + nombrePersona);

        personaTemp.setApellidosPersona(person.getProperty(FOAF.family_name).getLiteral().getString());
        personaTemp.setCorreoPersona(person.getProperty(FOAF.mbox).getResource().toString().split(":")[1]);
        personaTemp.setFacebookPersona(person.getProperty(FOAF.holdsAccount).getResource().toString());
        personaTemp.setNombrePersona(person.getProperty(FOAF.name).getLiteral().getString());
        personaTemp.setTelefonoPersona(person.getProperty(FOAF.phone).getResource().toString().split(":")[1]);
        personaTemp.setNombreUsuarioPersona(person.getProperty(FOAF.nick).getLiteral().getString());

        return personaTemp;
    }

    public String getArchivoRDF() {
        return archivoRDF;
    }

    public void setArchivoRDF(String archivoRDF) {
        this.archivoRDF = archivoRDF;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    
    
}
