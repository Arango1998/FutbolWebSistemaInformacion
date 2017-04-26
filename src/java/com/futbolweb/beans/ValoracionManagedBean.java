/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Valoracion;
import com.futbolweb.persistence.facades.ValoracionFacade;
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
@Named(value = "valoracionManagedBean")
@RequestScoped
public class ValoracionManagedBean implements Serializable, InterfaceController<Valoracion> {

    private Valoracion valoracion;
    @EJB
    private ValoracionFacade valf;
    
    
    public ValoracionManagedBean() {
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }
    
    
    @PostConstruct
    public void init(){
        valoracion = new Valoracion();
    }
    
    
    public void registrarValoracion(){
        valf.create(valoracion);
    }
    
    public void eliminarValoracion(){
        valf.remove(valoracion);
    }
    
    public void modificarValoracion(Valoracion v){
        valf.edit(v);
    }
    
    public List<Valoracion> listarValoracion(){
        return valf.findAll();
    }
    
    public String actualizarValoracion(Valoracion val){
        val = valoracion;
        return "";
    }

    @Override
    public Valoracion getObjectByKey(Integer key) {
        return valf.find(key);
    }
    
}
