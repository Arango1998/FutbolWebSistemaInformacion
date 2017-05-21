/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "usuarioManagedBean")
@RequestScoped
public class UsuarioManagedBean implements Serializable, InterfaceController<Usuario> {

    private Usuario usuario;
    private List<Usuario> listaInvitado;
    @EJB
    private UsuarioFacade uf;
    @Inject
    private EstadoUsuarioManagedBean estadoUsuarioManagedBean;
    @Inject
    private RolManagedBean rolManagedBean;

    public UsuarioManagedBean() {
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaInvitado = new LinkedList<>();
    }

    @Override
    public Usuario getObjectByKey(Integer key) {
        return uf.find(key);
    }

    public List<Usuario> getListaInvitado() {
        return listaInvitado;
    }

    public void setListaInvitado(List<Usuario> listaInvitado) {
        this.listaInvitado = listaInvitado;
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

    public List<Usuario> listarUsuario() {
        return uf.findAll();
    }

    public List<Usuario> solicitarInvitado() {
        List<Usuario> lUsuario = uf.listarUsuariosInvitados();
        return listaInvitado = lUsuario;

    }
    
    
    

    public void recorroUsuarioInvitado() {
        for (Usuario a : listaInvitado) {

        }
    }

    public void creaUsuarioInvitado() {
        try {

            usuario.setIdEstado(getEstadoUsuarioManagedBean().getObjectByKey(1));
            usuario.setIdTipoRol(getRolManagedBean().getObjectByKey(5));
            uf.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario registrado con éxito"));

        } catch (Exception e) {
        }

    }
    
    
    public void verInvitado(Usuario u){
    
    this.usuario=u;
    }
    
    public void editarInvitados(){
        try {
            usuario.getIdUsuario();
            uf.edit(usuario);
        } catch (Exception e) {
        }
    }
    
        public void eliminarUsuarioInvitadi(Usuario u) {
        uf.remove(u);
    
    }
    
        
          public void redireccionarUsuarioJugadorAcudiente() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_jugador.xhtml");
        } catch (Exception e) {
        }
    }
    
  
    
    
    
    
      public void redireccionarUsuarioInvitado() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_usuarios.xhtml");
        } catch (Exception e) {
        }
    }
    

    public void creaUsuario() {
        try {

            uf.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario registrado con éxito"));

        } catch (Exception e) {

        }

    }

    public List<Usuario> getInvitado() {

        return this.uf.listaInvitados();

    }

    public List<Usuario> getActivos() {
        return this.uf.listaActivos();
    }
    
     public List<Usuario> getJugadores() {
        return this.uf.listaJugadores();
    }
       public List<Usuario> getEntrenador() {
        return this.uf.listaEntrenadores();
    }
       
//          public List<Usuario> getCoordinadores() {
//        return this.uf.listaCoordinadores();
//    }
//    

    public void redireccionar() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registrar_usuario.xhtml");
        } catch (Exception e) {
        }
    }

    public void redireccionarRegistro() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_usuario.xhtml");
        } catch (Exception e) {
        }
    }

}
