/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Equipo;
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
public class EquipoFacade extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    public List<Equipo> listarCategoria() {

        List<Equipo> listacategorias;
        Query query = em.createQuery("SELECT e FROM equipos e WHERE e.fkIdCategoria=1");
        listacategorias = query.getResultList();
        return listacategorias;

    }

    public List<Equipo> listarJuvenil() {

        return getEntityManager().createNamedQuery("Equipo.Categoria", Equipo.class).setParameter("fkIdCategoria", 2).getResultList();

    }
    
    public List<Equipo> listarElite() {

        return getEntityManager().createNamedQuery("Equipo.Categoria", Equipo.class).setParameter("fkIdCategoria", 3).getResultList();

    }
    
    public List<Equipo> listarSuperior() {

        return getEntityManager().createNamedQuery("Equipo.Categoria", Equipo.class).setParameter("fkIdCategoria", 6).getResultList();

    }
    
     public List<Equipo> listarPreJuvenil() {

        return getEntityManager().createNamedQuery("Equipo.Categoria", Equipo.class).setParameter("fkIdCategoria", 1).getResultList();

    }
 
    public List<Equipo> listarInfantil() {

        return getEntityManager().createNamedQuery("Equipo.Categoria", Equipo.class).setParameter("fkIdCategoria", 7).getResultList();

    }
 
    
}
