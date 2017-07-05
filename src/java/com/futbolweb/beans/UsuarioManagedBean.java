/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.beans;

import com.futbolweb.converters.InterfaceController;
import com.futbolweb.login.beans.SessionManagedBean;
import com.futbolweb.persistence.entities.Usuario;
import com.futbolweb.persistence.facades.UsuarioFacade;
import com.futboweb.correocontacto.email.Email;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "usuarioManagedBean")
@RequestScoped
public class UsuarioManagedBean implements Serializable, InterfaceController<Usuario> {

    private Usuario usuario;
    private List<Usuario> listaInvitado;
    @EJB
    private UsuarioFacade uf;
    @Inject
    private EstadoUsuarioManagedBean estadoUsuarioManagedBean;
    @Inject
    private RolManagedBean rolManagedBean;
     @Inject
    private SessionManagedBean sesionM;

    public UsuarioManagedBean() {
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaInvitado = new LinkedList<>();
    }

    @Override
    public Usuario getObjectByKey(Integer key) {
        return uf.find(key);
    }

    public List<Usuario> getListaInvitado() {
        return listaInvitado;
    }

    public void setListaInvitado(List<Usuario> listaInvitado) {
        this.listaInvitado = listaInvitado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoUsuarioManagedBean getEstadoUsuarioManagedBean() {
        return estadoUsuarioManagedBean;
    }

    public void setEstadoUsuarioManagedBean(EstadoUsuarioManagedBean estadoUsuarioManagedBean) {
        this.estadoUsuarioManagedBean = estadoUsuarioManagedBean;
    }

    public RolManagedBean getRolManagedBean() {
        return rolManagedBean;
    }

    public void setRolManagedBean(RolManagedBean rolManagedBean) {
        this.rolManagedBean = rolManagedBean;
    }

    public List<Usuario> listarUsuario() {
        return uf.findAll();
    }

    public List<Usuario> solicitarInvitado() {
        List<Usuario> lUsuario = uf.listarUsuariosInvitados();
        return listaInvitado = lUsuario;

    }

    public SessionManagedBean getSesionM() {
        return sesionM;
    }

    public void setSesionM(SessionManagedBean sesionM) {
        this.sesionM = sesionM;
    }
    
    
    

    public void recorroUsuarioInvitado() {
        for (Usuario a : listaInvitado) {

        }
    }

    public void creaUsuarioInvitado() {
        try {

            usuario.setIdEstado(getEstadoUsuarioManagedBean().getObjectByKey(1));
            usuario.setIdTipoRol(getRolManagedBean().getObjectByKey(5));
            uf.create(usuario);
            if (usuario != null) {
                
            Email envioU;
            envioU = new Email("Registro de usuario exitoso", "Apreciado usuario "+usuario.getPrimerNombre()+" "+usuario.getPrimerApellido()+", Se ha realizado con exito su registro en el sistema Futbol Web."
                    + ""+" Gracias por registrarse en nuestro sistema, se le informa que su rol actual es: '"+usuario.getIdTipoRol().getNombreRol()+"' y su estado actual es:'"+usuario.getIdEstado().getNombreEstado()+"' hasta que el coordinador verifique y actualice sus datos."
                    + ""+" Accederá con su documento:'"+usuario.getDocumento()+"'"
                    + " y su contraseña:'"+usuario.getClave()+"",usuario.getCorreo());
            System.out.println(envioU.toString());
            envioU.enviarEmail2();
            }
        } catch (Exception e) {
        }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario registrado con éxito"));

    }
    
       public void redireccionarUsuariosatisfatorio() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_satisfactorio.xhtml");
        } catch (Exception e) {
        }
    }
    
    
    public void verInvitado(Usuario u){
    
    this.usuario=u;
    }
    
      public void editarUsuarioSession(){
        try {
            usuario.getIdUsuario();
            uf.edit(usuario);
        } catch (Exception e) {
        }
    }
    
    
    
    public void editarInvitados(){
        try {
            usuario.getIdUsuario();
            uf.edit(usuario);
        } catch (Exception e) {
        }
    }
    
        public void eliminarUsuarioInvitadi(Usuario u) {
        uf.remove(u);
    
    }
    
        
          public void redireccionarUsuarioJugadorAcudiente() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_jugador.xhtml");
        } catch (Exception e) {
        }
    }
    
      public void redireccionarUsuarioCordinador() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_usuarios.xhtml");
        } catch (Exception e) {
        }
    }
      
      
    
    
    
    
      public void redireccionarUsuarioInvitado() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_invitados.xhtml");
        } catch (Exception e) {
        }
    }
      
      
          public void redireccionarUsuarioJugador() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_jugadores.xhtml");
        } catch (Exception e) {
        }
    }
    
          
             
          public void redireccionarUsuarioEntrenador() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_entrenadores.xhtml");
        } catch (Exception e) {
        }
    }
          
          
                   
          public void redireccionarUsuarioAcudiente() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_acudientes.xhtml");
        } catch (Exception e) {
        }
    }
    
    

    public void creaUsuario() {
        try {

            uf.create(usuario);
            if (usuario != null) {
                
            Email envioU;
            envioU = new Email("Registro de usuario exitoso", "Apreciado usuario "+usuario.getPrimerNombre()+" "+usuario.getPrimerApellido()+", Se ha realizado con exito su registro en el sistema Futbol Web."
                    + ""+" Gracias por registrarse en nuestro sistema, se le informa que su rol actual es: '"+usuario.getIdTipoRol().getNombreRol()+"' y su estado actual es:'"+usuario.getIdEstado().getNombreEstado()+"'."
                    + ""+" Accederá con su documento:'"+usuario.getDocumento()+"'"
                    + " y su contraseña:'"+usuario.getClave()+"",usuario.getCorreo());
            System.out.println(envioU.toString());
            envioU.enviarEmail2();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario registrado con éxito"));

        } catch (Exception e) {

        }

    }

    public List<Usuario> getInvitado() {

        return this.uf.listaInvitados();

    }

    public List<Usuario> getActivos() {
        return this.uf.listaActivos();
    }
    
     public List<Usuario> getJugadores() {
        return this.uf.listaJugadores();
    }
       public List<Usuario> getEntrenador() {
        return this.uf.listaEntrenadores();
    }
//        public List<Usuario> getJugadoresAcudiente(){
//                       
//            
//          return this.uf.listaJugadoresAcudiente().equals(sesionM.getUsuarioSesion().getIdUsuario());
//        }
//          public List<Usuario> getCoordinadores() {
//        return this.uf.listaCoordinadores();
//    }
//    

    public void redireccionar() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registrar_usuario.xhtml");
        } catch (Exception e) {
        }
    }

    public void redireccionarRegistro() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("registro_usuario.xhtml");
        } catch (Exception e) {
        }
    }
    
    public void envioAdvertencia(Usuario u){
        
              Email envioA;
    envioA = new Email("Advertencia de pago", "Señor/a "+u.getPrimerNombre()+" "+u.getPrimerApellido()+", Se advierte que posee un pago en mora  en el club Expreso Rojo, si no realiza el pago oportuno, se procederá a realizar el bloqueo  del acceso a nuestro sistema, para mas información consultar el control de pagos en nuestro sistema.",u.getCorreo());
            System.out.println(envioA.toString());
    envioA.enviarEmail2();  
            
   
    }
    public void envioBloqueo(Usuario u){
        
              Email envioA;
    envioA = new Email("Advertencia de bloqueo de acceso", "Señor/a "+u.getPrimerNombre()+" "+u.getPrimerApellido()+", Se le notifica que se le ha bloquado el acceso al club Expreso Rojo por moras en los pagos, se le rehabilitará el acceso cuando realice los pagos pendientes.",u.getCorreo());
            System.out.println(envioA.toString());
    envioA.enviarEmail2();  
            
   
    }
   

}
