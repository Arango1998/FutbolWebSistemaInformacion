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
 * @author Felipe
 */
@Entity
@Table(name = "motivos_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoPago.findAll", query = "SELECT m FROM MotivoPago m"),
    @NamedQuery(name = "MotivoPago.findByIdMotivoPago", query = "SELECT m FROM MotivoPago m WHERE m.idMotivoPago = :idMotivoPago"),
    @NamedQuery(name = "MotivoPago.findByNombre", query = "SELECT m FROM MotivoPago m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MotivoPago.findByMonto", query = "SELECT m FROM MotivoPago m WHERE m.monto = :monto")})
public class MotivoPago implements Serializable, IDTO {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_motivo_pago")
    private Integer idMotivoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkMotivoPago", fetch = FetchType.LAZY)
    private List<Pago> pagoList;

    public MotivoPago() {
    }

    public MotivoPago(Integer idMotivoPago) {
        this.idMotivoPago = idMotivoPago;
    }

    public MotivoPago(Integer idMotivoPago, String nombre, int monto) {
        this.idMotivoPago = idMotivoPago;
        this.nombre = nombre;
        this.monto = monto;
    }

    public Integer getIdMotivoPago() {
        return idMotivoPago;
    }

    public void setIdMotivoPago(Integer idMotivoPago) {
        this.idMotivoPago = idMotivoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotivoPago != null ? idMotivoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoPago)) {
            return false;
        }
        MotivoPago other = (MotivoPago) object;
        if ((this.idMotivoPago == null && other.idMotivoPago != null) || (this.idMotivoPago != null && !this.idMotivoPago.equals(other.idMotivoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.MotivoPago[ idMotivoPago=" + idMotivoPago + " ]";
    }

    @Override
    public String obtenerLlavePrimaria() {
    return idMotivoPago.toString();
    }
    
}
