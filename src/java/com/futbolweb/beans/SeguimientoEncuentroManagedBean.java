/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.SeguimientoEncuentro;
import com.futbolweb.persistence.facades.SeguimientoEncuentroFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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

    public SeguimientoEncuentroManagedBean() {
    }

    public List<SeguimientoEncuentro> getLista() {
        return lista;
    }

    public void setLista(List<SeguimientoEncuentro> lista) {
        this.lista = lista;
    }

    public BarChartModel getGraf() {
        return graf;
    }

    public void setGraf(BarChartModel graf) {
        this.graf = graf;
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

}
