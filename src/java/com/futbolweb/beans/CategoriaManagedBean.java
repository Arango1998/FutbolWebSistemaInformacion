/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Categoria;
import com.futbolweb.persistence.facades.CategoriaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "categoriaManagedBean")
@RequestScoped
public class CategoriaManagedBean implements Serializable, InterfaceController<Categoria> {

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

    public void crearCatagoria() {

        try {
            categoriaEJB.create(categoria);
        } catch (Exception e) {
        }
    }

    public void redireccionar() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("crear_categoria.xhtml");
        } catch (Exception e) {
        }
    }
    
       public void guardarCambiosCategoria(Categoria ca) throws IOException{
   
        try {
             categoriaEJB.edit(categoria);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cambio Categoria"));
        } catch (Exception e) {
        }
    
    }
    
    public String redireccionarCategoria(Categoria ca) {

    categoria=ca;
    return "crear_categoria.xhtml";
    }
   
    
    
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Categoria) event.getObject()).getNombreCategoria());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Categoria) event.getObject()).obtenerLlavePrimaria());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
    
    
    
    

    public CategoriaManagedBean() {
    }

    @Override
    public Categoria getObjectByKey(Integer key) {
        return categoriaEJB.find(key);
    }

}
