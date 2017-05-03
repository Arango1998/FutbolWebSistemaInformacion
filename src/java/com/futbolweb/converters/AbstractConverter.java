/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.converters;

import com.futbolweb.persistence.entities.IDTO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Cristian Suesca
 */
public abstract class AbstractConverter implements Converter {

    protected String nameManagedBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer llave = Integer.valueOf(value);
            InterfaceController ic = (InterfaceController) context.getApplication().getELResolver().getValue(context.getELContext(), null, nameManagedBean);
            System.out.println(ic.getObjectByKey(llave));
            return ic.getObjectByKey(llave);
        } catch (NumberFormatException e) {
            System.out.println("no puede convertir a objeto");
            return null;
        }

    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if (value instanceof IDTO) {
                IDTO objetoEntidad = (IDTO) value;
                return objetoEntidad.obtenerLlavePrimaria();

            } else {
                System.out.println("No es instancia de IDTO");
                return null;

            }
        } catch (Exception e) {
            System.out.println("no se puede convertir a string");
            return null;
        }

    }

}
