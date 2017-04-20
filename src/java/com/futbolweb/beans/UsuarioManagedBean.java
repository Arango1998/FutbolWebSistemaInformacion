/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.EstadoUsuario;
import com.futbolweb.persistence.entities.Rol;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "usuarioManagedBean")
@RequestScoped
public class UsuarioManagedBean implements Serializable, InterfaceController<Usuario>{

     private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioRFacade;
    @Inject
    private EstadoUsuarioManagedBean estadoUsuarioManagedBean;
    @Inject
    private RolManagedBean rolManagedBean;

    public UsuarioManagedBean() {
    }

   
    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    @Override
    public Usuario getObjectByKey(Integer key) {
        return usuarioRFacade.find(key);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoUsuarioManagedBean getEstadoUsuarioManagedBean() {
        return estadoUsuarioManagedBean;
    }

    public void setEstadoUsuarioManagedBean(EstadoUsuarioManagedBean estadoUsuarioManagedBean) {
        this.estadoUsuarioManagedBean = estadoUsuarioManagedBean;
    }

    public RolManagedBean getRolManagedBean() {
        return rolManagedBean;
    }

    public void setRolManagedBean(RolManagedBean rolManagedBean) {
        this.rolManagedBean = rolManagedBean;
    }
    
       public List<Usuario> listarRegistroUsuarios(){
        return usuarioRFacade.findAll();
    }
       
       
    public void creaUsuarioInvitado() {
        try {
            EstadoUsuario e = new EstadoUsuario();
            e.setIdEstado(1);
            Rol r = new Rol();
            r.setIdTipoRol(5);
            usuario.setIdEstado(e);
            usuario.setIdTipoRol(r);
            usuarioRFacade.create(usuario);
        } catch (Exception e) {
        }

    }
}
