/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian Suesca
 */
@Entity
@Table(name = "desempenio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desempenio.findAll", query = "SELECT d FROM Desempenio d"),
    @NamedQuery(name = "Desempenio.findByIdDesempenio", query = "SELECT d FROM Desempenio d WHERE d.idDesempenio = :idDesempenio"),
    @NamedQuery(name = "Desempenio.findByNombreDesempenio", query = "SELECT d FROM Desempenio d WHERE d.nombreDesempenio = :nombreDesempenio")})
public class Desempenio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_desempenio")
    private Integer idDesempenio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_desempenio")
    private String nombreDesempenio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDesempenio", fetch = FetchType.EAGER)
    private List<Item> itemList;

    public Desempenio() {
    }

    public Desempenio(Integer idDesempenio) {
        this.idDesempenio = idDesempenio;
    }

    public Desempenio(Integer idDesempenio, String nombreDesempenio) {
        this.idDesempenio = idDesempenio;
        this.nombreDesempenio = nombreDesempenio;
    }

    public Integer getIdDesempenio() {
        return idDesempenio;
    }

    public void setIdDesempenio(Integer idDesempenio) {
        this.idDesempenio = idDesempenio;
    }

    public String getNombreDesempenio() {
        return nombreDesempenio;
    }

    public void setNombreDesempenio(String nombreDesempenio) {
        this.nombreDesempenio = nombreDesempenio;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDesempenio != null ? idDesempenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desempenio)) {
            return false;
        }
        Desempenio other = (Desempenio) object;
        if ((this.idDesempenio == null && other.idDesempenio != null) || (this.idDesempenio != null && !this.idDesempenio.equals(other.idDesempenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.Desempenio[ idDesempenio=" + idDesempenio + " ]";
    }
    
}
