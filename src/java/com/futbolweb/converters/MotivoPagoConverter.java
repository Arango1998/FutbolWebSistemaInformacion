/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.MotivoPago;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Felipe
 */
@FacesConverter (forClass = MotivoPago.class)
public class MotivoPagoConverter extends AbstractConverter{
    public MotivoPagoConverter(){
        this.nameManagedBean="motivoPagoManagedBean";
    }
}
