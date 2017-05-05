/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String documento;
    private String clave;
    
    @EJB 
    private UsuarioSessionBean usuarioSessionBean;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @PostConstruct
    public void init() {
        clave = "";
        documento = "";
    }
    
    
    
        public String iniciarSesion() {
        try {
            Object object = usuarioSessionBean.autenticarSesionUsuario(documento, clave);
            FacesContext context = FacesContext.getCurrentInstance();
            if (object instanceof Integer) {
                switch ((Integer) object) {
                    case 1:
                        context.addMessage(null, new FacesMessage("error 1"));
                        break;
                    case 2:
                        context.addMessage(null, new FacesMessage("No existe usuario"));
                        break;
                    case 3:
                        context.addMessage(null, new FacesMessage("Contraseña incorrecta"));
                        break;
                    case 4:
                        context.addMessage(null, new FacesMessage("Usuario De estado  inactivo"));
                        break;
                }
            } else if (object instanceof Usuario) {
                context.getExternalContext().getSessionMap().put("usuario", object);
                return "/protegido/iniciousuario.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
        }
        return null;        
    }
    
    
    
    
    
    public LoginManagedBean() {
    }
    
}
