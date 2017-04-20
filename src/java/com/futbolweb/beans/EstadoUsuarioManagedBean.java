/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.EstadoUsuario;
import com.futbolweb.persistence.facades.EstadoUsuarioFacade;
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
@Named(value = "estadoUsuarioManagedBean")
@RequestScoped
public class EstadoUsuarioManagedBean implements Serializable, InterfaceController<EstadoUsuario> {

    private EstadoUsuario estadoUsuario;
    @EJB
    private EstadoUsuarioFacade ef;

    @PostConstruct
    public void init() {
        estadoUsuario = new EstadoUsuario();
    }

    public EstadoUsuarioManagedBean() {
    }

    @Override
    public EstadoUsuario getObjectByKey(Integer key) {
        return ef.find(key);
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public List<EstadoUsuario> listarEstadoUsuario() {
        return ef.findAll();
    }

}
