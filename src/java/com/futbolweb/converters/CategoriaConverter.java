/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.Categoria;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Cristian Suesca
 */
@FacesConverter (forClass = Categoria.class)
public class CategoriaConverter extends AbstractConverter{
    public CategoriaConverter(){
    this.nameManagedBean = "categoriaManagedBean";
    }
    
}
