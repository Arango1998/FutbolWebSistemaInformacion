/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Rol;
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
    private Usuario usuario;
    private Rol rol;

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
        clave = "";
        documento = "";
        rol = new Rol();
        usuario = new Usuario();
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
                        context.addMessage(null, new FacesMessage("Usuario en estado ´Inactivo´"));
                        break;
                }
            } else if (object instanceof Usuario) {
                rol = ((Usuario) object).getIdTipoRol();
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
