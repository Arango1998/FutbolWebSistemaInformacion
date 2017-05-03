/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.facades.JugadorFacade;
import com.futbolweb.persistence.facades.SeguimientoFacade;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Iesua
 */
@Named(value = "seguimientoManagedBean")
@RequestScoped
public class SeguimientoManagedBean implements Serializable,InterfaceController<Seguimiento> {

    private Seguimiento seguimiento;
    List<Seguimiento>lista;
    @EJB
    private SeguimientoFacade segf;
    
    
    private Jugador jugador;
    @EJB
    private JugadorFacade jugadorF;
    
    
    public SeguimientoManagedBean() {
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public List<Seguimiento> getLista() {
        return lista;
    }

    public void setLista(List<Seguimiento> lista) {
        this.lista = lista;
    }
    
    
    
    
    
    @PostConstruct
    public void init(){
        seguimiento = new Seguimiento();
        lista= new LinkedList<>();
    }
    
    public String solicitarJugador(List<Seguimiento> lseguimiento){
        try{
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seg", lseguimiento);
        
        return "/protegido/entrenador/listadoseguimiento.xhtml?faces-redirect=true";
        }catch(Exception e){
        }
        
        return "";
        
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
