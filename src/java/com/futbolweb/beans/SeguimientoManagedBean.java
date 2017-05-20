/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.login.beans.SessionManagedBean;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.facades.JugadorFacade;
import com.futbolweb.persistence.facades.SeguimientoFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
    @Inject
    private SessionManagedBean sessionMB;
    
    

    public SessionManagedBean getSessionMB() {
        return sessionMB;
    }

    public void setSessionMB(SessionManagedBean sessionMB) {
        this.sessionMB = sessionMB;
    }
    
    
    
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
    
    public List<Seguimiento> listarSeguimientoo() {
        return (List<Seguimiento>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seguimientos");
    }
    
    public String solicitarJugador(int idJugador) {

        Jugador j = jugadorF.find(idJugador);
        List<Seguimiento> lseguimiento = segf.listarSeguimientoEspecifico(j);
        lista = lseguimiento;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguimientos", lista);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jugadorSeguimiento", j);
        return "/protegido/entrenador/listajugadoresseguimiento.xhtml?faces-redirect=true";

    }
    
    public void registrarSeguimiento(Seguimiento s){
        
        seguimiento.setIdJugador();
        seguimiento.setIdEntrenador();
        Date fecha= new Date();
        seguimiento.setIdPosicionSeguimiento();
        segf.create(seguimiento);
    }
    
    public void eliminarSeguimiento(Seguimiento ser){
        segf.remove(ser);
    }
    
    public void modificarSeguimiento(Seguimiento s){
        segf.edit(s);
    }
    
    public List<Seguimiento> listarSeguimiento(){
        return segf.findAll();
    }
    
    public String actualizarSeguimiento(Seguimiento se){
        se = seguimiento;
        return "listajugadoresseguimiento.xhtml";
    }
    
    public List<Seguimiento> listarSeguimientoPropio(){
        return getSessionMB().getUsuarioSesion().getJugador().getSeguimientoList();
    }

    @Override
    public Seguimiento getObjectByKey(Integer key) {
        return segf.find(key);
    }
}
