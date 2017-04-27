/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Localidad;
import com.futbolweb.persistence.facades.LocalidadFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author camila
 */
@Named(value = "localidadManagedBean")
@RequestScoped
public class LocalidadManagedBean implements Serializable, InterfaceController<Localidad> {

    private Localidad localidad;
    @EJB
    private LocalidadFacade lf;

    public LocalidadManagedBean() {
    }

    public void init() {

        localidad = new Localidad();

    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public List<Localidad> listarLocalidad() {

        return lf.findAll();

    }

    @Override
    public Localidad getObjectByKey(Integer key) {
        return lf.find(key);

    }

}
