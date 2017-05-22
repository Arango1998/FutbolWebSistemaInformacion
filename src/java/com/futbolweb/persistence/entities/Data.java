/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.entities;

import java.util.logging.Logger;

/**
 *
 * @author camila
 */
public class Data {
    
    private String label;   
    private Integer value;
    private static final Logger LOG = Logger.getLogger(Data.class.getName());

    public Data(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    
    
    
    
}
