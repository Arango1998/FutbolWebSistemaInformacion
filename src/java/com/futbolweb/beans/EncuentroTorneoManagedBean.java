/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.EncuentroTorneo;
import com.futbolweb.persistence.facades.EncuentroTorneoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "encuentroTorneoManagedBean")
@RequestScoped
public class EncuentroTorneoManagedBean implements Serializable, InterfaceController<EncuentroTorneo>{

    private EncuentroTorneo encuentroTorneo;
    @EJB
    private EncuentroTorneoFacade etf;
    public EncuentroTorneoManagedBean() {
    }
      @PostConstruct
    public void init(){
        encuentroTorneo = new EncuentroTorneo();
    }

    @Override
    public EncuentroTorneo getObjectByKey(Integer key) {
    return etf.find(key);
    }

    public EncuentroTorneo getEncuentroTorneo() {
        return encuentroTorneo;
    }

    public void setEncuentroTorneo(EncuentroTorneo encuentroTorneo) {
        this.encuentroTorneo = encuentroTorneo;
    }
    
     public List<EncuentroTorneo> listar(){
        return etf.findAll();
    }
      public void crearEncuentro(){
        etf.create(encuentroTorneo);
    }
    
}
