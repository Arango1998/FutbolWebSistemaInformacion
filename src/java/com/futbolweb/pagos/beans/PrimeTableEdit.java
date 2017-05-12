/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.pagos.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Pago;
import com.futbolweb.persistence.facades.PagoFacade;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Felipe
 */
@Named(value = "primeTableEdit")
@ViewScoped
public class PrimeTableEdit implements Serializable, InterfaceController<Pago> {

    private Pago pago;
    @EJB
    private PagoFacade pagof;
    
    
   
     @PostConstruct
    public void init(){
    pago = new Pago();
    
    }
    public void onRowEdit(RowEditEvent event, Pago leerPago) {
        FacesMessage msg = new FacesMessage("Pago editado", ((Pago) event.getObject()).obtenerLlavePrimaria());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        pago = leerPago;
        Date fecha= new Date();
        pago.setFechaPago(fecha);
       pagof.edit(pago);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada", ((Pago) event.getObject()).obtenerLlavePrimaria());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    
    /**
     * Creates a new instance of PrimeTableEdit
     */
    public PrimeTableEdit() {
    }

    @Override
    public Pago getObjectByKey(Integer key) {
        return pagof.find(key);
    }
    
}
