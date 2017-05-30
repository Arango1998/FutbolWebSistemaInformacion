/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Entrenador;
import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.Seguimiento;
import com.futbolweb.persistence.entities.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
public class SeguimientoFacade extends AbstractFacade<Seguimiento> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoFacade() {
        super(Seguimiento.class);
    }

    public List<Seguimiento> listarSeguimientoEspecifico(Jugador j) {
        List<Seguimiento> lista;
        Query query = em.createQuery("SELECT s FROM Seguimiento s WHERE s.idJugador = ?1");
        query.setParameter(1, j);
        lista = query.getResultList();
        return lista;
    }

    public List<Seguimiento> registrarSeguimiento(Seguimiento s) {
        try {
            s.setFechaSeguimiento(new Date());
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            Query query = getEntityManager().createQuery("SELECT e FROM Entrenador e WHERE e.usuario.idUsuario = :id")
                    .setParameter("id", u.getIdUsuario());
            Entrenador e = (Entrenador) query.getResultList().get(0);
            s.setIdEntrenador(e);
            getEntityManager().persist(s);
            return this.listarSeguimientoEspecifico(s.getIdJugador());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
    
    public Jugador buscar(String cedula) {
        return (Jugador) getEntityManager().createQuery("SELECT j FROM Jugador j WHERE j.idJugador = :d")
                .setParameter("d", Integer.valueOf(cedula))
                .getResultList().get(0);
    }
}
