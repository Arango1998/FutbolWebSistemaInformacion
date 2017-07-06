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
import com.futbolweb.persistence.facades.EntrenadorFacade;
import com.futbolweb.persistence.facades.JugadorFacade;
import com.futbolweb.persistence.facades.PosicionSeguimientoFacade;
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
public class SeguimientoManagedBean implements Serializable, InterfaceController<Seguimiento> {

    private Seguimiento seguimiento;
    private String cedula;

    private Jugador js;
    
    

    public Jugador getJs() {
        js.getFkIdEquipo().getIdEntrenador().getExperiencia();
        return js;
    }

    public void setJs(Jugador js) {
        this.js = js;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
   
    
    private List<Seguimiento> seguimientoDelJugador;
    List<Seguimiento> lista;
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
    @EJB
    private PosicionSeguimientoFacade psef;
    @EJB
    private EntrenadorFacade ef;

    public EntrenadorFacade getEf() {
        return ef;
    }

    public void setEf(EntrenadorFacade ef) {
        this.ef = ef;
    }

    public PosicionSeguimientoFacade getPsef() {
        return psef;
    }

    public void setPsef(PosicionSeguimientoFacade psef) {
        this.psef = psef;
    }

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

    public List<Seguimiento> list() {

        return segf.findAll();
    }

    public List<Seguimiento> getSeguimientoDelJugador() {
        return seguimientoDelJugador;
    }

    public void setSeguimientoDelJugador(List<Seguimiento> seguimientoDelJugador) {
        this.seguimientoDelJugador = seguimientoDelJugador;
    }

    @PostConstruct
    public void init() {
        seguimiento = new Seguimiento();
        cedula = new String();
        lista = new LinkedList<>();
        js = new Jugador();
        seguimientoDelJugador = getListaJuagador();
    }

    public void listarSeguimientoo() {
        seguimientoDelJugador = (List<Seguimiento>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seguimientos");
    }

    public List<Seguimiento> getListaJuagador(){
        return (List<Seguimiento>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seguimientos");
    }
    
    public String solicitarJugador(int idJugador) {
         Jugador j = jugadorF.find(idJugador);
        List<Seguimiento> lseguimiento = segf.listarSeguimientoEspecifico(j);
        lista = lseguimiento;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguimientos", lista);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jseg", j);
        return "/protegido/entrenador/listajugadoresseguimiento.xhtml?faces-redirect=true";
    }

    
   // public void registrarSeguimiento(){
       // Seguimiento se = this.getListaJuagador().get(this.getListaJuagador().size()-1);
  //      seguimientoDelJugador = segf.registrarSeguimiento(se);
    //}
    
    public String registrarSeguimiento(){
        
    Jugador ju =  (Jugador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jseg");
    seguimiento.setIdJugador(ju);
        System.out.println(seguimiento.getIdJugador());
    seguimiento.setFechaSeguimiento(new Date());
        System.out.println(seguimiento.getFechaSeguimiento());
    seguimiento.setIdEntrenador(ef.obtenerIdEntrenador());
        System.out.println(seguimiento.getIdEntrenador());
    seguimiento.setIdPosicionSeguimiento(psef.obtenerIdPosicion());
        System.out.println(seguimiento.getIdPosicionSeguimiento());
    segf.create(seguimiento);
    return solicitarJugador(seguimiento.getIdJugador().getIdJugador());
    }
    
    public String eliminarSeguimiento(Seguimiento ser) {
        segf.remove(ser);
        return solicitarJugador(ser.getIdJugador().getIdJugador());
    }

    public void modificarSeguimiento(Seguimiento s) {
        segf.edit(s);
    }

    public List<Seguimiento> listarSeguimiento() {
        return segf.findAll();
    }

    public String actualizarSeguimiento(Seguimiento se) {
        se = seguimiento;
        return "listajugadoresseguimiento.xhtml";
    }

    public List<Seguimiento> listarSeguimientoPropio() {
        return getSessionMB().getUsuarioSesion().getJugador().getSeguimientoList();
    }

    @Override
    public Seguimiento getObjectByKey(Integer key) {
        return segf.find(key);
    }
}