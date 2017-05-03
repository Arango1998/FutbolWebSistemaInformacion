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
  private JugadorFacade jugadorEJB;
    public JugadorManagedBean() {
    }
    
       @PostConstruct
    public void init(){
        jugador = new Jugador();
    }

     @Override
    public Jugador getObjectByKey(Integer key) {
    return jugadorEJB.find(key);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public List<Jugador> listarJugadores(){
        return jugadorEJB.findAll();
    }
      
       
    public void creaJugador(){
        try {
            jugadorEJB.create(jugador);
        } catch (Exception e) {
        }
    }


 
    
    
    
}
