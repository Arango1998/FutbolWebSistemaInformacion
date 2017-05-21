/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.login.beans.SessionManagedBean;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.SeguimientoEncuentro;
import com.futbolweb.persistence.facades.SeguimientoEncuentroFacade;
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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Iesua
 */
@Named(value = "seguimientoEncuentroManagedBean")
@RequestScoped
public class SeguimientoEncuentroManagedBean implements Serializable, InterfaceController<SeguimientoEncuentro> {

    
    private List<SeguimientoEncuentro> lista;
    private BarChartModel graf;
    private SeguimientoEncuentro seguimientoencuentro;
    @EJB
    private SeguimientoEncuentroFacade segef;
    @Inject
    private SessionManagedBean sesionM;

    public SeguimientoEncuentroManagedBean() {
    }

    public List<SeguimientoEncuentro> getLista() {
        return lista;
    }

    public void setLista(List<SeguimientoEncuentro> lista) {
        this.lista = lista;
    }

    public SessionManagedBean getSesionM() {
        return sesionM;
    }

    public void setSesionM(SessionManagedBean sesionM) {
        this.sesionM = sesionM;
    }
    
    
    public SeguimientoEncuentro getSeguimientoencuentro() {
        return seguimientoencuentro;
    }

    public void setSeguimientoencuentro(SeguimientoEncuentro seguimientoencuentro) {
        this.seguimientoencuentro = seguimientoencuentro;
    }

    @PostConstruct
    public void init() {
        seguimientoencuentro = new SeguimientoEncuentro();

    }

    public void registrarSeguimientoEncuentro() {
        segef.create(seguimientoencuentro);
    }

    public void eliminarSeguimientoEncuentro(SeguimientoEncuentro segui) {
        segef.remove(segui);
    }

    public void modificarSeguimientoEncuentro(SeguimientoEncuentro se) {
        segef.edit(se);
    }

    public List<SeguimientoEncuentro> listarSeguimientoEncuentro() {
        return segef.findAll();
    }

    public String actualizarSeguimientoEncuentro(SeguimientoEncuentro sege) {
        sege = seguimientoencuentro;
        return "";
    }

    public void graficar() {
        graf=new BarChartModel();
        for (int i = 0; i < lista.size(); i++) {
            ChartSeries serie=new BarChartSeries();
            serie.setLabel(lista.get(i).obtenerLlavePrimaria());
            serie.set(lista.get(i).getIdJugador(), lista.get(i).getGoles());
            graf.addSeries(serie);
        }
     
        graf.setTitle("Goles por jugador");
        graf.setLegendPosition("ne");
        Axis xAxis = graf.getAxis(AxisType.X);
        xAxis.setLabel("Jugadores");
        Axis yAxis = graf.getAxis(AxisType.Y);
        yAxis.setLabel("Goles");
        yAxis.setMin(0);
        yAxis.setMax(15);
    }

    @Override
    public SeguimientoEncuentro getObjectByKey(Integer key) {
        return segef.find(key);
    }

    public void redireccionar() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registrarseguimientoencuentro.xhtml");
        } catch (Exception e) {
        }
    }
    
     public void guardarCambiosEncuentro(SeguimientoEncuentro s) throws IOException {

        try {
            segef.edit(seguimientoencuentro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cambio Barrio"));
        } catch (Exception e) {
        }

    }

    public String redireccionarEncuentro(SeguimientoEncuentro s) {

        seguimientoencuentro = s;
        return "registrarseguimientoencuentro.xhtml";
    }

    public List<SeguimientoEncuentro> listarPropioSeguimiento(){
    
    return getSesionM().getUsuarioSesion().getJugador().getSeguimientoEncuentroList();
    }

}
