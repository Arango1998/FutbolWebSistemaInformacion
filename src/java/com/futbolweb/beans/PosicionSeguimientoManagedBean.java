/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.PosicionSeguimiento;
import com.futbolweb.persistence.facades.PosicionSeguimientoFacade;
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
@Named(value = "posicionSeguimientoManagedBean")
@RequestScoped
public class PosicionSeguimientoManagedBean implements Serializable, InterfaceController<PosicionSeguimiento>{

    private PosicionSeguimiento posicionseguimiento;
    @EJB
    private PosicionSeguimientoFacade posegf;
    
    
    public PosicionSeguimientoManagedBean() {
    }

    public PosicionSeguimiento getPosicionseguimiento() {
        return posicionseguimiento;
    }

    public void setPosicionseguimiento(PosicionSeguimiento posicionseguimiento) {
        this.posicionseguimiento = posicionseguimiento;
    }
    
    
    @PostConstruct
    public void init(){
        posicionseguimiento = new PosicionSeguimiento();
    }
    
   
    public void registrarPosicionSeguimiento(){
        posegf.create(posicionseguimiento);
    }
    
    public void eliminarPosicionSeguimiento(){
        posegf.remove(posicionseguimiento);
    }
    
    public void modificarPosicionSeguimiento(PosicionSeguimiento ps){
        posegf.edit(ps);
    }
    
    public List<PosicionSeguimiento> listarPosicionSeguimiento(){
        return posegf.findAll();
    }
    
    public String actualizarPosicionSeguimiento(PosicionSeguimiento pose){
        pose = posicionseguimiento;
        return "";
    }

    @Override
    public PosicionSeguimiento getObjectByKey(Integer key) {
        return posegf.find(key);
    }
    
}
