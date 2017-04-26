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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Iesua
 */
@Named(value = "entrenadorManagedBean")
@RequestScoped
public class EntrenadorManagedBean implements Serializable, InterfaceController<Entrenador>{

    private Entrenador entrenador;
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
    
    
    @PostConstruct
    public void init(){
        entrenador = new Entrenador();
    }
    
    
    public void registrarEntrenador(){
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

    @Override
    public Entrenador getObjectByKey(Integer key) {
        return enf.find(key);
    }
    
}
