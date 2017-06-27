/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Entrenador;
import com.futbolweb.persistence.facades.EntrenadorFacade;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Iesua
 */
@Named(value = "entrenadorManagedBean")
@RequestScoped
public class EntrenadorManagedBean implements Serializable, InterfaceController<Entrenador>{

    private Entrenador entrenador;
    
    List<Entrenador> lista;
    @EJB
    private EntrenadorFacade enf;
    
    public EntrenadorManagedBean() {
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Entrenador> getLista() {
        return lista;
    }

    public void setLista(List<Entrenador> lista) {
        this.lista = lista;
    }
    
    
    
    
    
    
    @PostConstruct
    public void init(){
        entrenador = new Entrenador();
        lista = new LinkedList<>();
    }
    
    
    public void registrarEntrenador(String pathReal){
        entrenador.setDiploma(pathReal);
        enf.create(entrenador);
    }
    
    public void eliminarEntrenador(){
        enf.remove(entrenador);
    }
    
    public void modificarEntrenador(Entrenador e){
        enf.edit(e);
    }
    
    public List<Entrenador> listarEntrenador(){
        return enf.findAll();
    }
    
    public String actualizarEntrenador (Entrenador en){
        en = entrenador;
        return "";
    }
    
    public String seleccionarEntrenadorEspecifico(int idEntrenador){
    Entrenador e = enf.find(idEntrenador);
    List<Entrenador> lentrenador = enf.listarEntenadorEspecifico(e);
    lista = lentrenador;
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("entrenador", lista);
    return "/faces/protegido/coordinador/entrenador_especifico.xhtml";
    }
    
    public List<Entrenador>listarEntrenadorEspecifico(){
    return (List<Entrenador>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entrenador");
    }
    

    @Override
    public Entrenador getObjectByKey(Integer key) {
        return enf.find(key);
    }
    
}
