
package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PlantillaController implements Serializable{
    public void verificarSession(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
           Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                context.getExternalContext().redirect("./../permisos.xhtml");
            }
        } catch (Exception e) {
        }
    }
}
