/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.usuarios.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.login.beans.SessionManagedBean;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Felipe
 */
@Named(value = "listadoAcudientesManagedBean")
@RequestScoped
public class ListadoAcudientesManagedBean implements Serializable, InterfaceController<Usuario> {

     private Usuario usuario;
     @EJB
     private UsuarioFacade usuarioF;
     @Inject
     private SessionManagedBean usuarioM;
    /**
     * Creates a new instance of ListadoAcudientesManagedBean
     */
    public ListadoAcudientesManagedBean() {
    }

    @Override
    public Usuario getObjectByKey(Integer key) {
        return usuarioF.find(key);
    }
    
}
