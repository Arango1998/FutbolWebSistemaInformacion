package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Permiso;
import com.futbolweb.persistence.entities.Rol;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.RolFacade;
import com.futbolweb.persistence.facades.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped

public class PlantillaController implements Serializable {
    
    private Usuario us;
    private UsuarioFacade usuf;
    private RolFacade rolF;
    
    public Usuario getUs() {
        return us;
    }
    
    public void setUs(Usuario us) {
        this.us = us;
    }
    
    @PostConstruct
    public void init() {
        us = new Usuario();
    }
    
    public void verificarSession() {
        try {
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
            Usuario user = usuf.find(Integer.valueOf(usuario.obtenerLlavePrimaria()));
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
}
