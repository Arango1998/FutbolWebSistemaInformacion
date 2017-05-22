/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.EncuentroTorneo;
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
public class EncuentroTorneoFacade extends AbstractFacade<EncuentroTorneo> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuentroTorneoFacade() {
        super(EncuentroTorneo.class);
    }

    public List<EncuentroTorneo> listarEncuentro() {

        List<EncuentroTorneo> listaencuentro;
        Query query = em.createQuery("SELECT e FROM EncuentroTorneo e WHERE e.fkIdBarrio.fkIdLocalidad=1");
        listaencuentro = query.getResultList();
        return listaencuentro;
    }
    
    public List<EncuentroTorneo> listarEngativa() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 10).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarUsaquen() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 1).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarChapinero() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 2).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarSantafe() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 3).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarSancristobal() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 4).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarUsme() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 5).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarTunjuelito() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 6).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarBosa() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 7).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarKen() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 8).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarFon() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 9).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarsub() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 11).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarBarr() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 12).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarTeus() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 13).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarMarti() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 14).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarAntoni() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 15).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarPteAranda() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 16).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarCandela() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 17).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarRafaelU() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 18).getResultList();
    
    }

    
    public List<EncuentroTorneo> listarCiudadB() {
    
        return getEntityManager().createNamedQuery("EncuentroTorneo.Localidad", EncuentroTorneo.class).setParameter("fkIdLocalidad", 19).getResultList();
    
    }

}