/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Rol;
import com.futbolweb.persistence.facades.RolFacade;
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
@Named(value = "rolManagedBean")
@RequestScoped
public class RolManagedBean implements Serializable, InterfaceController<Rol> {

    private Rol rol;
    @EJB
    private RolFacade rf;

    public RolManagedBean() {
    }

    @PostConstruct
    public void init() {
        rol = new Rol();
    }

    @Override
    public Rol getObjectByKey(Integer key) {
        return rf.find(key);
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> listarRolUsuario() {
        return rf.findAll();
    }

}
