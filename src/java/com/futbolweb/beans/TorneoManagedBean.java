package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Torneo;
import com.futbolweb.persistence.facades.TorneoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "torneoManagedBean")
@RequestScoped
public class TorneoManagedBean implements Serializable, InterfaceController<Torneo> {

    private Torneo torneo;
    @EJB
    private TorneoFacade tf;

    public TorneoManagedBean() {
    }

    @PostConstruct
    public void init() {
        torneo = new Torneo();
    }

    @Override
    public Torneo getObjectByKey(Integer key) {
        return tf.find(key);
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public List<Torneo> listar() {
        return tf.findAll();
    }
    
    
    


    public void create() {
        try {
            if (torneo.getFechaInicio().before(torneo.getFechaFin())) {
                tf.create(torneo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Torneo  registrado con éxito"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La fecha final debe ser posterior a la fecha inicial"));
            }
        } catch (Exception e) {

        }

    }

    public void eliminarTorneo(Torneo t) {

        try {
            tf.remove(t);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro eliminado"));

        } catch (Exception e) {
        }

    }

    public void redireccionar() {

        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_torneo.xhtml");
        } catch (Exception e) {
        }
    }

    public String redireccionarTorneo(Torneo t) {

        torneo = t;
        return "registro_torneo.xhtml";
    }

    public void guardarCambiosTorneo(Torneo t) throws IOException {

        try {
            tf.edit(torneo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se modificó el registro"));
        } catch (Exception e) {
        }

    }

}
