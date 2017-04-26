/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.SeguimientoEncuentro;
import com.futbolweb.persistence.facades.SeguimientoEncuentroFacade;
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
@Named(value = "seguimientoEncuentroManagedBean")
@RequestScoped
public class SeguimientoEncuentroManagedBean implements Serializable, InterfaceController<SeguimientoEncuentro> {

    private SeguimientoEncuentro seguimientoencuentro;
    @EJB
    private SeguimientoEncuentroFacade segef;
    
    
    
    public SeguimientoEncuentroManagedBean() {
    }

    public SeguimientoEncuentro getSeguimientoencuentro() {
        return seguimientoencuentro;
    }

    public void setSeguimientoencuentro(SeguimientoEncuentro seguimientoencuentro) {
        this.seguimientoencuentro = seguimientoencuentro;
    }
    
    
    @PostConstruct
    public void init(){
        seguimientoencuentro = new SeguimientoEncuentro();
    }
    
    
    public void registrarSeguimientoEncuentro(){
        segef.create(seguimientoencuentro);
    }
    
    public void eliminarSeguimientoEncuentro(){
        segef.remove(seguimientoencuentro);
    }
    
    public void modificarSeguimientoEncuentro(SeguimientoEncuentro se){
        segef.edit(se);
    }
    
    public List<SeguimientoEncuentro> listarSeguimientoEncuentro(){
        return segef.findAll();
    }
    
    public String actualizarSeguimientoEncuentro(SeguimientoEncuentro sege){
        sege = seguimientoencuentro;
        return "";
    }

    @Override
    public SeguimientoEncuentro getObjectByKey(Integer key) {
        return segef.find(key);
    }
}
    


