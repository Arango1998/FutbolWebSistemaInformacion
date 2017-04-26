/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.facades.SeguimientoFacade;
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
@Named(value = "seguimientoManagedBean")
@RequestScoped
public class SeguimientoManagedBean implements Serializable,InterfaceController<Seguimiento> {

    private Seguimiento seguimiento;
    @EJB
    private SeguimientoFacade segf;
    
    
    public SeguimientoManagedBean() {
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }
    
    
    
    @PostConstruct
    public void init(){
        seguimiento = new Seguimiento();
    }
    
    public void registrarSeguimiento(){
        segf.create(seguimiento);
    }
    
    public void eliminarSeguimiento(){
        segf.remove(seguimiento);
    }
    
    public void modificarSeguimiento(Seguimiento s){
        segf.edit(s);
    }
    
    public List<Seguimiento> listarSeguimiento(){
        return segf.findAll();
    }
    
    public String actualizarSeguimiento(Seguimiento se){
        se = seguimiento;
        return "";
    }

    @Override
    public Seguimiento getObjectByKey(Integer key) {
        return segf.find(key);
    }
}
