/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Jugador;
import com.futbolweb.persistence.entities.Seguimiento;
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
    public Seguimiento obtenerResultado(Seguimiento s){
      
        Query query = em.createNativeQuery("SELECT AVG(valoraciones.nota) AS \"promedio fisico\" FROM valoraciones INNER JOIN items ON items.id_items=valoraciones.id_item  INNER JOIN desempenio ON desempenio.id_desempenio=items.id_desempenio WHERE items.id_desempenio=?1  and valoraciones.id_seguimiento=?2;\n" +"",Seguimiento.class);
        query.setParameter(1, 1);
        query.setParameter(2,s);
        List<Seguimiento> lista = query.getResultList();
        Seguimiento segi =  lista.get(0);
        return  segi;
    }
//public Seguimiento obtenerIdSeguimiento(Seguimiento s){
      
  //      Query query = em.createNativeQuery("SELECT s.* FROM seguimientos s WHERE s.id_jugador = ? ",Seguimiento.class);
    //    query.setParameter(1, s.getIdJugador());
      //  List<Seguimiento> lista = query.getResultList();
        //Seguimiento psefo = (Seguimiento) lista;
       // return  psefo;
    //}
    
    
    
   
}
