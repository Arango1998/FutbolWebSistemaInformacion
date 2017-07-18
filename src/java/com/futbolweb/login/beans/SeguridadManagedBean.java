/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Permiso;
import com.futbolweb.persistence.entities.Rol;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.RolFacade;
import com.futbolweb.persistence.facades.UsuarioFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "seguridadManagedBean")
@Dependent
public class SeguridadManagedBean {

      private Usuario us;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB private RolFacade rolF;
    
    
    public SeguridadManagedBean() {
    }

    public Usuario getUsuario() {
        return us;
    }

    public void setUsuario(Usuario usuario) {
        this.us = usuario;
    }
    
     @PostConstruct
    public void init() {
        us = new Usuario();
    }
    
    
    
      public void verificarSesion() {
        try {
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if(usuario == null){
             FacesContext.getCurrentInstance().getExternalContext().redirect("../../permisos.xhtml?faces-redirect=true");
            }
            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
            Usuario user = usuarioFacade.find(Integer.valueOf(usuario.obtenerLlavePrimaria()));
            System.out.println("uuuuuuuuuuuuuuuuuuu");
            if (!verificarPermisos(rolF.find(Integer.valueOf(user.getIdTipoRol().obtenerLlavePrimaria())))) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../../permisos.xhtml?faces-redirect=true");
            }
            
        } catch (Exception e) {
        }
    }
    
    
    private boolean verificarPermisos(Rol r) {
        boolean perm = false;
        System.out.println("Rol: " + r.getNombreRol());
        FacesContext fc = FacesContext.getCurrentInstance();
        String url = "/faces" + fc.getExternalContext().getRequestPathInfo();
        for (Permiso p : r.getPermisoList()) {
            if (p.getNombrePermiso() != null && p.getUrl() != null && p.getUrl().equals(url)) {
                System.out.println(p.getNombrePermiso());
                perm = true;
                break;
            }
        }
        
        return perm;
    }
      
      
         public String cerrarSesion(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuario");
        context.getExternalContext().invalidateSession();
        return "/paginaprincipal.xhtml?faces-redirect=true";
    }
      
      
      
//          public void cerrarSesion() {
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//    }
    
    
}
