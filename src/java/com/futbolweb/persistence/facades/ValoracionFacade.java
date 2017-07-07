/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.entities.Valoracion;
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
public class ValoracionFacade extends AbstractFacade<Valoracion> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoracionFacade() {
        super(Valoracion.class);
    }
     public List<Valoracion> listarSeguimientoEspecifico(Seguimiento s) {
        List<Valoracion> lista;
        Query query = em.createQuery("SELECT v FROM Valoracion v WHERE v.idSeguimiento = ?1");
        query.setParameter(1, s);
        lista = query.getResultList();
        return lista;
    }
   
}
