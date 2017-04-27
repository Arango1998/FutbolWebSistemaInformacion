/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.expochick.business.login.controller;


import com.futbolweb.persistence.entities.Rol;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexandra
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private Usuario usuario;
    private Rol rol;

    @EJB
    private UsuarioFacade usufc;

    public LoginManagedBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @PostConstruct
    public void init() {
        rol = new Rol();
        usuario = new Usuario();
    }

    public String iniciarSesion() {
        try {
            usuario = usufc.iniciarSesion(usuario);
            if (usuario != null) {
                rol = usuario.getIdTipoRol();
                System.out.println(usuario.getIdTipoRol());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", rol); //Objeto de la sesion ROL!
                return "/protegido/iniciousuario.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario y/o Contraseña Incorrectos"));
                usuario = new Usuario();
                rol = new Rol();
                return "";
            }
        } catch (Exception e) {
            System.out.println("NO INGRESO AL SISTEMA");
            return "";
        }
    }

}
