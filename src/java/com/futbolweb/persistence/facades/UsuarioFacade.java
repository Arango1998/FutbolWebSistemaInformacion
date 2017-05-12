/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.facades;

import com.futbolweb.persistence.entities.Rol;
import com.futbolweb.persistence.entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> listarUsuariosInvitados() {
        List<Usuario> listaInvitado;
        Query query = em.createQuery("SELECT u FROM usuario u WHERE u.IdTipoRol=1");
        listaInvitado = query.getResultList();
        return listaInvitado;
    }

    public List<Usuario> listaInvitados() {
        return getEntityManager().createNamedQuery("Usuario.rolInvitado", Usuario.class).setParameter("idTipoRol", 5).getResultList();
    }

    public List<Usuario> listaActivos() {
        return getEntityManager().createNamedQuery("Usuario.rolInvitado", Usuario.class).setParameter("idTipoRol", 4).getResultList();
    }

    public List<Usuario> listaJugadores() {
        return getEntityManager().createNamedQuery("Usuario.rolInvitado", Usuario.class).setParameter("idTipoRol", 3).getResultList();
    }

    public List<Usuario> listaEntrenadores() {
        return getEntityManager().createNamedQuery("Usuario.rolInvitado", Usuario.class).setParameter("idTipoRol", 2).getResultList();
    }

    public List<Usuario> listaCoordinadores() {
        return getEntityManager().createNamedQuery("Usuario.rolInvitado", Usuario.class).setParameter("idTipoRol", 1).getResultList();
    }

    public Usuario iniciarSesion(Usuario user) {
        Usuario usuario = null;
        String rta;
        try {
            rta = "FROM Usuario u WHERE u.documento = ?1 and u.clave = ?2";
            Query query = em.createQuery(rta);
            query.setParameter(1, user.getDocumento());
            query.setParameter(2, user.getClave());
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public Usuario capturarUsuario(Usuario usu) {
        return getEntityManager().createNamedQuery("Usuario.findByDocumento", Usuario.class).setParameter("documento", usu.getDocumento()).getSingleResult();
    }
}
