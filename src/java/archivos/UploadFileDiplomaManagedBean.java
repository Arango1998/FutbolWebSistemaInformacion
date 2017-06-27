/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import com.futbolweb.beans.EntrenadorManagedBean;
import com.futbolweb.persistence.entities.Entrenador;
import com.futbolweb.persistence.facades.EntrenadorFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Cristian Suesca
 */
@Named(value = "uploadFileDiplomaManagedBean")
@SessionScoped
public class UploadFileDiplomaManagedBean implements Serializable{

     private Part file;
    private String nombre;
    private String pathReal;
    
    @EJB
    private EntrenadorFacade entrenadorFacade;
    private Entrenador entrenador;
   
    
    public UploadFileDiplomaManagedBean() {
        entrenador = new Entrenador();
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

   
    
    @PostConstruct
    public void init() {
        entrenador = new Entrenador();
    }
    
    @PreDestroy
    public void eliminar(){
        System.out.println("oiyuuuuuuuuuuuuuuuuuuuuu");
    }
    
        public String uploadFileDiploma(){
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("diplomas_entrenadores");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\diplomas_entrenadores\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "diplomas_entrenadores/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
            //EntrenadorManagedBean e=new EntrenadorManagedBean();
            //e.registrarEntrenador(pathReal);
            
            //Metodo de registrar Producto
            System.out.println("MEEEEEEEEEEE");
            entrenador.setDiploma(pathReal);
            System.out.println("hhhhhhhhhhhhhhhhhh");
            entrenadorFacade.create(entrenador);
            System.out.println("kkkkkkkkkkkkkkkkkkk");
            //return "productos.xhtml?faces-redirect=true";//return
             
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

   
