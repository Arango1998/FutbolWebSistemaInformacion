/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.PosicionSeguimiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristian Suesca
 */
@Stateless
public class PosicionSeguimientoFacade extends AbstractFacade<PosicionSeguimiento> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PosicionSeguimientoFacade() {
        super(PosicionSeguimiento.class);
    }
    
    public PosicionSeguimiento obtenerIdPosicion(Jugador j){
      
        Query query = em.createNativeQuery("SELECT p.* FROM posicion_seguimiento p WHERE p.id_jugador = ? ORDER BY p.id_posicion_seguimiento DESC LIMIT 1",PosicionSeguimiento.class);
        query.setParameter(1, j.getIdJugador());
        List<PosicionSeguimiento> lista = query.getResultList();
        PosicionSeguimiento psefo = lista.get(0);
        return  psefo;
    }
    
}
