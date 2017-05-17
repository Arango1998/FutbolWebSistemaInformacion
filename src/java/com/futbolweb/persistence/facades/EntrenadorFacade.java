/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Entrenador;
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
public class EntrenadorFacade extends AbstractFacade<Entrenador> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrenadorFacade() {
        super(Entrenador.class);
    }
    
    
    public List<Entrenador> listarEntenadorEspecifico(Entrenador e){
    List<Entrenador> lista;
    Query query = em.createQuery ("SELECT e FROM Entrenador e WHERE e.idEntrenador = ?1");
    query.setParameter(1, e);
    lista = query.getResultList();
    return lista;
    }
    
}
