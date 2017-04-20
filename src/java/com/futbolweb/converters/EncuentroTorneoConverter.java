/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.EncuentroTorneo;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Cristian Suesca
 */
@FacesConverter (forClass = EncuentroTorneo.class)
public class EncuentroTorneoConverter extends AbstractConverter{
     public EncuentroTorneoConverter(){
        this.nameManagedBean = "encuentroTorneoManagedBean";
    }
    
}
