/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Permiso;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "sessionManagedBean")
@SessionScoped
public class SessionManagedBean implements Serializable {
    
    
    
     private Usuario usuarioSesion;
     @EJB
     private UsuarioFacade uf;

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }
    
    public SessionManagedBean() {
    }
    
    
    
    @PostConstruct
    public void init() {
        usuarioSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    
      public List<Permiso> permisos(){
        return usuarioSesion.getIdTipoRol().getPermisoList();
    }
      
      public void capturarUsuarioEditar(){
      uf.edit(usuarioSesion);
      
      }
    
}
