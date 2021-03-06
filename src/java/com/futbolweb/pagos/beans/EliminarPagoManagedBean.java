/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.pagos.beans;

import com.futbolweb.beans.PagoManagedBean;
import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Pago;
import com.futbolweb.persistence.facades.PagoFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Felipe
 */
@Named(value = "eliminarPagoManagedBean")
@RequestScoped
public class EliminarPagoManagedBean implements InterfaceController<Pago>{

     private Pago pago;
    @EJB 
    private PagoFacade pagof;
    @Inject
    private PagoManagedBean pagoM;
    
    @PostConstruct
    public void init(){
    pago = new Pago();
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    
    public String eliminarPago(Pago p){
    pagof.remove(p);
    
    return pagoM.solicitarJugador(p.getFkIdJugador().getIdJugador());
    }
    /**
     * Creates a new instance of EliminarPagoManagedBean
     */
    public EliminarPagoManagedBean() {
    }

    @Override
    public Pago getObjectByKey(Integer key) {
        return pagof.find(key);
    }
    
}
