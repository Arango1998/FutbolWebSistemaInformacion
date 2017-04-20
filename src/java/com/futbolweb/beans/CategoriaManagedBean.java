/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Categoria;
import com.futbolweb.persistence.facades.CategoriaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "categoriaManagedBean")
@RequestScoped
public class CategoriaManagedBean implements Serializable, InterfaceController<Categoria>{

     @EJB
    private CategoriaFacade categoriaEJB;
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    @PostConstruct
    public void init() {
        categoria = new Categoria();
    }

    public List<Categoria> listCategorias() {
        return categoriaEJB.findAll();
    }
    
    public void crearCatagoria(){
    
        try {
            categoriaEJB.create(categoria);
        } catch (Exception e) {
        }
    }

    public CategoriaManagedBean() {
    }

   @Override
    public Categoria getObjectByKey(Integer key) {
    return categoriaEJB.find(key);
    }
    
}
