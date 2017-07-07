/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.entities.Valoracion;
import com.futbolweb.persistence.facades.SeguimientoFacade;
import com.futbolweb.persistence.facades.ValoracionFacade;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Iesua
 */
@Named(value = "valoracionManagedBean")
@SessionScoped
public class ValoracionManagedBean implements Serializable, InterfaceController<Valoracion> {

    private Valoracion valoracion;
    private Seguimiento seguimiento;
    private int nota;
    List<Valoracion> lista;
    @EJB
    private ValoracionFacade valf;
    @EJB
    private SeguimientoFacade seguimientof;
    
    
    public ValoracionManagedBean() {
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    @PostConstruct
    public void init(){
        valoracion = new Valoracion();
        seguimiento = new Seguimiento();
        lista = new LinkedList<>();
    }
    public List<Valoracion> listarValoracion2() {
        return valf.listarSeguimientoEspecifico(seguimiento);
    }
    public String solicitarSeguimiento(Seguimiento s) {
        seguimiento = s;
        return "/protegido/entrenador/valoraciondesempenio.xhtml?faces-redirect=true";
    }
    
    public void registrarValoracion(){
        valf.create(valoracion);
    }
    
    public void eliminarValoracion(){
        valf.remove(valoracion);
    }
    
    public void modificarValoracion(Valoracion v){
        v.setNota(nota);
        valf.edit(v);
        nota=0;
    }
    
    public List<Valoracion> listarValoracion(){
        return valf.findAll();
    }
    
    public String actualizarValoracion(Valoracion val){
        val = valoracion;
        return "";
    }

    @Override
    public Valoracion getObjectByKey(Integer key) {
        return valf.find(key);
    }
    
}
