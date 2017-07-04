/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.login.beans.SessionManagedBean;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.Pago;
import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.facades.JugadorFacade;
import com.futbolweb.persistence.facades.PagoFacade;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author APRENDIZ
 */
@Named(value = "pagoManagedBean")
@RequestScoped
public class PagoManagedBean implements InterfaceController<Pago> {

    private Pago pago;
    List<Pago> lista;
    @EJB
    private PagoFacade pagof;

    private Jugador jugador;
    @EJB
    private JugadorFacade jugadorF;
    @Inject
    private SessionManagedBean sesionM;

    @PostConstruct
    public void init() {
        pago = new Pago();
        lista = new LinkedList<>();

    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public List<Pago> getLista() {
        return lista;
    }

    public void setLista(List<Pago> lista) {
        this.lista = lista;
    }

    public SessionManagedBean getSesionM() {
        return sesionM;
    }

    public void setSesionM(SessionManagedBean sesionM) {
        this.sesionM = sesionM;
    }

    public List<Pago> listarPago() {
        return (List<Pago>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pagos");
    }

    public String solicitarJugador(int idJugador) {
          
        Jugador j = jugadorF.find(idJugador);
        List<Pago> lpago = pagof.listarPagoEspecifico(j);
        lista = lpago;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pagos", lista);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jugadorPago", j);
        return "/protegido/coordinador/listapagos.xhtml?faces-redirect=true";

    }

    public void recorroPagos() {
        for (Pago a : lista) {

        }
    }

    public List<Pago> listarTodo() {
        return pagof.findAll();
    }

    public List<Pago> listarPagoPropio() {
        return getSesionM().getUsuarioSesion().getJugador().getPagoList();
    }

    public String actualizarPago(Pago pa) {
        pago = pa;
        return "";
    }

    /**
     * Creates a new instance of PagoManagedBean
     */
    public PagoManagedBean() {

    }

    public String redireccionarPago(Pago p) {
            pago = p;
        
            return  "listapagos.xhtml";
       
    }
    public void verPago(Pago p){
    
    this.pago=p;
    }
    
    public void guardarCambiosPago(Pago p) throws IOException {

        try {
            pagof.edit(pago);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cambio el Pago"));
        } catch (Exception e) {
        }
    }
    
     public String editarEstado(Pago p) {
       pago.setEstado("Pagado");
      pagof.edit(pago);
      return solicitarJugador(p.getFkIdJugador().getIdJugador());
      }

    @Override
    public Pago getObjectByKey(Integer key) {
        return pagof.find(key);
    }

}
