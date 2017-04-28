/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.Pago;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristian Suesca
 */
@Stateless
public class PagoFacade extends AbstractFacade<Pago> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }
    
    public List<Pago> listarPagoEspecifico(Jugador j){
     List<Pago> lista;
    Query query = em.createQuery ("SELECT p FROM Pago p WHERE p.fkIdJugador = ?1");
    query.setParameter(1, j);
    lista = query.getResultList();
    return lista;
    }
}
