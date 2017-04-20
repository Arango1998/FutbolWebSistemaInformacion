/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.pagos.beans;


import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Pago;
import com.futbolweb.persistence.facades.PagoFacade;
import com.futboweb.correocontacto.email.Email;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Felipe
 */
@Named(value = "registroPagoManagedBean")
@RequestScoped
public class RegistroPagoManagedBean implements InterfaceController<Pago> {

    private Pago pago;
     @EJB 
    private PagoFacade pagof;
   
    
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





    
    
    public void registrarPago(){
    Email envioC;
    envioC = new Email("Novedad de pago Expreso Rojo", "Se le notifíca que se le ha generado un nuevo registro de pago en el club Expreso Rojo, para mas información consultar el control de pagos en nuestro sistema :", pago.getFkIdJugador().getUsuario().getCorreo());
    envioC.enviarEmail();
    pagof.create(pago);
    }
    /**
     * Creates a new instance of RegistroPagoManagedBean
     */
    public RegistroPagoManagedBean() {
    }

    @Override
    public Pago getObjectByKey(Integer key) {
       return pagof.find(key);
    }
    
}
