/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.controller;


import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Alexandra
 */
@Named(value = "seguridadManagedBean")
@RequestScoped
public class SeguridadManagedBean {

    private Usuario usuario;

    @EJB
    private UsuarioFacade usufc;

    public SeguridadManagedBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public void verificarSesion() {
        try {
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if (user == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../faces/permisos.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {

        }
    }

    public String cerrarSesion(){
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "/faces/index.xhtml?faces-redirect=true";
   }
    
}
