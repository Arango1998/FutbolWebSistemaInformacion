/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Equipo;
import com.futbolweb.persistence.facades.EquipoFacade;
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
 * @author Cristian Suesca
 */
@Named(value = "equipoManagedBean")
@RequestScoped
public class EquipoManagedBean implements Serializable, InterfaceController<Equipo> {

    private Equipo equipo;
    @EJB
    private EquipoFacade ef;

    public EquipoManagedBean() {
    }
@PostConstruct
public void init(){
equipo = new Equipo();
}
    @Override
    public Equipo getObjectByKey(Integer key) {
        return ef.find(key);
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    public List<Equipo> listarEquipos() {
        return ef.findAll();
    }
    
    public void createEquipo() {
        try {

           ef.create(equipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Torneo registrado con éxito"));

        } catch (Exception e) {

        }

    }

}
