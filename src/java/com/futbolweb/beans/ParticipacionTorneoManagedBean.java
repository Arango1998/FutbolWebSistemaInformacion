/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.login.beans.SessionManagedBean;
import com.futbolweb.persistence.entities.ParticipacionTorneo;
import com.futbolweb.persistence.facades.ParticipacionTorneoFacade;
import java.io.IOException;
import java.io.Serializable;
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
 * @author camila
 */
@Named(value = "participacionTorneoManagedBean")
@RequestScoped
public class ParticipacionTorneoManagedBean implements Serializable, InterfaceController<ParticipacionTorneo> {

    private ParticipacionTorneo participacionTorneo;
    @EJB
    private ParticipacionTorneoFacade ptfacade;
    @Inject
    private SessionManagedBean sesionM;

    public SessionManagedBean getSesionM() {
        return sesionM;
    }

    public void setSesionM(SessionManagedBean sesionM) {
        this.sesionM = sesionM;
    }

    public ParticipacionTorneoManagedBean() {
    }

    @PostConstruct
    public void init() {

        participacionTorneo = new ParticipacionTorneo();

    }

    @Override
    public ParticipacionTorneo getObjectByKey(Integer key) {

        return ptfacade.find(key);

    }

    public ParticipacionTorneo getParticipacionTorneo() {
        return participacionTorneo;
    }

    public void setParticipacionTorneo(ParticipacionTorneo participacionTorneo) {
        this.participacionTorneo = participacionTorneo;
    }

    public List<ParticipacionTorneo> listar() {

        return ptfacade.findAll();

    }

    public void create() {

        ptfacade.create(participacionTorneo);

    }

    public void eliminar(ParticipacionTorneo p) {

        ptfacade.remove(p);

    }

    public void guardarCambiosParticipacion(ParticipacionTorneo p) throws IOException {
        try {
            ptfacade.edit(participacionTorneo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modificó el registro"));
        } catch (Exception e) {
        }
    }

    public String redireccionarParticipacionTorneo(ParticipacionTorneo p) {

        participacionTorneo = p;
        return "registro_participacion_torneo.xhtml";

    }
    
    public List<ParticipacionTorneo> listarPropiaParticipacionTorneo(){
    
    return getSesionM().getUsuarioSesion().getJugador().getFkIdEquipo().getParticipacionesTorneoList();
            
    
    }

}
