/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futboweb.correocontacto;

import com.futbolweb.persistence.entities.Jugador;
import com.futboweb.correocontacto.email.Email;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "emailController2")
@ViewScoped
public class EmailController2 implements Serializable {

     private String asunto;
   private String mensaje;
   private String destinatario;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
   
    /**
     * Creates a new instance of EmailController2
     */
    public EmailController2() {
    }
   
   
    public void enviarCorreo(){
    Email e = new Email(asunto, mensaje,destinatario);
        e.enviarEmail();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage("envio de mensaje exitos");
        fc.addMessage(null, m);
        
    }
}
