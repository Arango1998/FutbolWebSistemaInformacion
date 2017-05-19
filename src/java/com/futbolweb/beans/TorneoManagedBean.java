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
            
            tf.create(torneo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Torneo registrado con éxito"));
            
        } catch (Exception e) {
            
        }
        
    }

    public void redireccionar() {
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_torneo.xhtml");
        } catch (Exception e) {
        }
    }
    
    public void guardarCambiosTorneo(Torneo t) throws IOException {

        try {
            tf.edit(torneo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cambio Barrio"));
        } catch (Exception e) {
        }

    }

    public String redireccionarTorneo(Torneo t) {

        torneo = t;
        return "registro_torneo.xhtml";
    }
   
    
    
    }

