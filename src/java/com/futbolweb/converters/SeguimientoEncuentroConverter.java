/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.SeguimientoEncuentro;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Iesua
 */
@FacesConverter(forClass = SeguimientoEncuentro.class)
public class SeguimientoEncuentroConverter extends AbstractConverter{
    
    public SeguimientoEncuentroConverter(){
        this.nameManagedBean = "seguimientoEncuentroManagedBean";
    }
}
