/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.persistence.entities.Valoracion;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author camila
 */
@Named(value = "desempenioFisicoManagedBean")
@RequestScoped
public class desempenioFisicoManagedBean {

    @Inject
    private ValoracionManagedBean valoracion;

    private int velocidad;
    private int fuerza;
    private int resistencia;
    private int flexibilidad;

    public ValoracionManagedBean getValoracion() {
        return valoracion;
    }

    public void setValoracion(ValoracionManagedBean valoracion) {
        this.valoracion = valoracion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int Fuerza) {
        this.fuerza = Fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int Resistencia) {
        this.resistencia = Resistencia;
    }

    public int getFlexibilidad() {
        return flexibilidad;
    }

    public void setFlexibilidad(int Flexibilidad) {
        this.flexibilidad = Flexibilidad;
    }

    public desempenioFisicoManagedBean() {
    }

    @PostConstruct
    public void init() {
        asignarDesempenioFisico();
    }

    public void asignarDesempenioFisico() {

        List<Valoracion> valoraciones = getValoracion().listarValoracion2();

        for (int i = 0; i < valoraciones.size(); i++) {

            switch (i) {

                case 0:
                    velocidad = valoraciones.get(i).getNota();
                    break;

                case 1:
                    fuerza = valoraciones.get(i).getNota();
                    break;

                case 2:
                    resistencia = valoraciones.get(i).getNota();
                    break;

                default:
                    flexibilidad = valoraciones.get(i).getNota();

            }
            Valoracion v = valoraciones.get(i);

        }
    }

}
