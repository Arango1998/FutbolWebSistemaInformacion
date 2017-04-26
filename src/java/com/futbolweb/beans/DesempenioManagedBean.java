/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Desempenio;
import com.futbolweb.persistence.facades.DesempenioFacade;
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
@Named(value = "desempenioManagedBean")
@RequestScoped
public class DesempenioManagedBean implements Serializable, InterfaceController<Desempenio> {

    private Desempenio desempenio;
    @EJB
    private DesempenioFacade desf;
    
    public DesempenioManagedBean() {
    }

    public Desempenio getDesempenio() {
        return desempenio;
    }

    public void setDesempenio(Desempenio desempenio) {
        this.desempenio = desempenio;
    }
    
    
    @PostConstruct
    public void init(){
        desempenio = new Desempenio();
    }
    
    public void registrarDesempenio(){
        desf.create(desempenio);
    }
    
    public void eliminarDesempenio(){
        desf.remove(desempenio);
    }
    
    public void modificarDesempenio(Desempenio d){
        desf.edit(d);
    }
    
    public List<Desempenio> listarDesempenio(){
        return desf.findAll();
    }
    
    public String actualizarDesempenio(Desempenio des){
        des = desempenio;
        return "";
    }

    @Override
    public Desempenio getObjectByKey(Integer key) {
        return desf.find(key);
    }
    
}










