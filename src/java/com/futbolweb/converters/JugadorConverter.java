/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.Jugador;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Iesua
 */
@FacesConverter(forClass = Jugador.class)
public class JugadorConverter extends AbstractConverter{
    
    public JugadorConverter (){
        this.nameManagedBean = "jugadorManagedBean";
    }
}
