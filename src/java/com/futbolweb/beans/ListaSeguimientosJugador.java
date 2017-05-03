/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.persistence.entities.Seguimiento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Iesua
 */
@Named(value = "listaSeguimientosJugador")
@RequestScoped
public class ListaSeguimientosJugador {
    private List<Seguimiento> listaSeguimientoJugador;

    public List<Seguimiento> getListaSeguimientoJugador() {
        return listaSeguimientoJugador;
    }

    public void setListaSeguimientoJugador(List<Seguimiento> listaSeguimientoJugador) {
        this.listaSeguimientoJugador = listaSeguimientoJugador;
    }
    
    @PostConstruct
    public void init(){
    listaSeguimientoJugador = (List<Seguimiento>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seg");
    }
    /**
     * Creates a new instance of ListaSeguimientosJugador
     */
    public ListaSeguimientosJugador() {
    }
    
}
