/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.login.beans;

import com.futbolweb.persistence.entities.Usuario;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cristian Suesca
 */
@Stateful
public class UsuarioSessionBean {
    
     @PersistenceContext(unitName = "web_futbol_sistema_informacionPU")
    private EntityManager em;
       protected EntityManager getEntityManager() {
        return em;
    }
       
          public UsuarioSessionBean() {
    }
          
          
           public Object autenticarSesionUsuario(String documento, String clave) {
        try {
            Usuario usuario = getEntityManager().createNamedQuery("Usuario.findByDocumento", Usuario.class).setParameter("documento", documento).getSingleResult();
            if (usuario != null) {
                if (usuario.getClave().equals(clave)) {
                  //falta captura usuario
                    if (!usuario.getIdEstado().getIdEstado().equals(1)) {
                        System.out.println("Activo");
                        return usuario;
                        //Consulta o proceso que cambie el estado al loguarse
                    } else {
                        System.out.println("Estado de Usuario 'Inactivo'");
                        return 4;
                    }
                }else {
                    return 3;
                }
            }else{
                return 2;
            }
        } catch (Exception e) {
            System.out.println("Error!");
            return 1;
        }
    }
    
}
