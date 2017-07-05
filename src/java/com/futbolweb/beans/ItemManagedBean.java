/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Item;
import com.futbolweb.persistence.facades.ItemFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Iesua
 */
@Named(value = "itemManagedBean")
@RequestScoped
public class ItemManagedBean implements Serializable, InterfaceController<Item> {

    private Item item;
    @EJB
    private ItemFacade itf;
    
    
    public ItemManagedBean() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    
    @PostConstruct
    public void init(){
        item = new Item();
    }
    
    
    public void registrarItem(){
        try {
            itf.create(item);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarItem(){
        itf.remove(item);
    }
    
    public void modificarItem(Item i){
        itf.edit(i);
    }
    
    public List<Item> listarItem(){
        return itf.findAll();
    }
    
    public String actualizarItem(Item it){
        it = item;
        return "";
    }

    @Override
    public Item getObjectByKey(Integer key) {
        return itf.find(key);
    }
    
}
