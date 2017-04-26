/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.facades.JugadorFacade;
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
@Named(value = "jugadorManagedBean")
@RequestScoped
public class JugadorManagedBean implements Serializable, InterfaceController<Jugador>{

    private Jugador jugador;
    @EJB
    private JugadorFacade juf;
    
    public JugadorManagedBean() {
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    
    @PostConstruct
    public void init(){
        jugador = new Jugador();
    }
    
    
    public void registrarJugador(){
        juf.create(jugador);
    }
    
    public void eliminarJugador(){
        juf.remove(jugador);
    }
    
    public void modificarJugador(Jugador j){
        juf.edit(j);
    }
    
    public List<Jugador> listarJugador(){
        return juf.findAll();
    }
    
    public String actualizarJugador(Jugador ju){
        ju = jugador;
        return "";
    }

    @Override
    public Jugador getObjectByKey(Integer key) {
        return juf.find(key);
    }
    
    
    
}
