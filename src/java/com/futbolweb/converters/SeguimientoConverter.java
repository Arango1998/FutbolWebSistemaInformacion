/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.Seguimiento;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Iesua
 */
@FacesConverter(forClass = Seguimiento.class)
public class SeguimientoConverter extends AbstractConverter{
    
    public SeguimientoConverter(){
        this.nameManagedBean = "seguimientoManagedBean";
    }
}
