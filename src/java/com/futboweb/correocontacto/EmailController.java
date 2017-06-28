/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futboweb.correocontacto;

import com.futbolweb.persistence.entities.Usuario;
import com.futboweb.correocontacto.email.Email;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "emailController")
@RequestScoped
//@ViewScoped
public class EmailController {

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
   
    public EmailController() {
        
        
    }
    
    
    
    public void seleccionarU(Usuario us){
    destinatario= us.getIdAcudiente().getCorreo();
        System.out.println(destinatario);
    }
    
    
    
    public String enviarCorreo(){
    Email e = new Email(asunto, mensaje, destinatario);
    e.enviarEmail();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage("envio de mensaje exitos");
        fc.addMessage(null, m);
        return"";
    }
    
}
