/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.Desempenio;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Iesua
 */
@FacesConverter (forClass = Desempenio.class)
public class DesempenioConverter extends AbstractConverter{
    
    public DesempenioConverter(){
        this.nameManagedBean = "desempenioManageBean";
    }
}
