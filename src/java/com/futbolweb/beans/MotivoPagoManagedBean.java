/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.MotivoPago;
import com.futbolweb.persistence.facades.MotivoPagoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Felipe
 */
@Named(value = "motivoPagoManagedBean")
@RequestScoped
public class MotivoPagoManagedBean implements InterfaceController<MotivoPago> {
    
    private MotivoPago mPago;
    @EJB
    private MotivoPagoFacade mPagoF;
    @PostConstruct
    public void init() {
      mPago = new MotivoPago();

    }

    public MotivoPago getmPago() {
        return mPago;
    }

    public void setmPago(MotivoPago mPago) {
        this.mPago = mPago;
    }
    
    /**
     * Creates a new instance of MotivoPagoManagedBean
     */
    public MotivoPagoManagedBean() {
    }

    public List<MotivoPago> listarTodo() {
        return mPagoF.findAll();
    }
    @Override
    public MotivoPago getObjectByKey(Integer key) {
    return mPagoF.find(key);
    }
    
}
