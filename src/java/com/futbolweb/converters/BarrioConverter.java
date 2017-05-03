package com.futbolweb.converters;

import com.futbolweb.persistence.entities.Barrio;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Barrio.class)
public class BarrioConverter extends AbstractConverter {

    public BarrioConverter() {

        this.nameManagedBean = "barrioManagedBean";
    }

}
