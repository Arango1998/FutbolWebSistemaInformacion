/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.PosicionSeguimiento;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Iesua
 */
@FacesConverter (forClass = PosicionSeguimiento.class)
public class PosicionSeguimientoConverter extends AbstractConverter{
    
    public PosicionSeguimientoConverter(){
        this.nameManagedBean = "posicionSeguimientoManagedBean";
    }
}
