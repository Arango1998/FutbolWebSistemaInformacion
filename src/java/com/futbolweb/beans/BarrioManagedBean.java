/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Barrio;
import com.futbolweb.persistence.facades.BarrioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author camila
 */
@Named(value = "barrioManagedBean")
@RequestScoped
public class BarrioManagedBean implements Serializable, InterfaceController<Barrio> {

    private Barrio barrio;
    @EJB
    private BarrioFacade bf;

    public BarrioManagedBean() {
    }

    @PostConstruct
    public void init() {
        barrio = new Barrio();
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public List<Barrio> listar() {
        return bf.findAll();
    }

    public void crearBarrio() {
        bf.create(barrio);

    }

    public void redireccionar() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("listar_barrio.xhtml");
        } catch (Exception e) {
        }
    }
    
     public void guardarCambiosBarrio(Barrio b) throws IOException {

        try {
            bf.edit(barrio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cambio Barrio"));
        } catch (Exception e) {
        }

    }

    public String redireccionarBarrio(Barrio b) {

        barrio = b;
        return "listar_barrio.xhtml";
    }

    @Override
    public Barrio getObjectByKey(Integer key) {
        return bf.find(key);
    }

}
