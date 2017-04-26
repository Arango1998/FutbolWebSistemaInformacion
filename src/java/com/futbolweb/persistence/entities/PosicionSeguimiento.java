/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian Suesca
 */
@Entity
@Table(name = "posicion_seguimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosicionSeguimiento.findAll", query = "SELECT p FROM PosicionSeguimiento p"),
    @NamedQuery(name = "PosicionSeguimiento.findByIdPosicionSeguimiento", query = "SELECT p FROM PosicionSeguimiento p WHERE p.idPosicionSeguimiento = :idPosicionSeguimiento"),
    @NamedQuery(name = "PosicionSeguimiento.findByFecha", query = "SELECT p FROM PosicionSeguimiento p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PosicionSeguimiento.findByPosicion", query = "SELECT p FROM PosicionSeguimiento p WHERE p.posicion = :posicion")})
public class PosicionSeguimiento implements Serializable, IDTO {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_posicion_seguimiento")
    private Integer idPosicionSeguimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "posicion")
    private String posicion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPosicionSeguimiento", fetch = FetchType.EAGER)
    private List<Seguimiento> seguimientoList;
    @JoinColumn(name = "id_jugador", referencedColumnName = "id_jugador")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Jugador idJugador;

    public PosicionSeguimiento() {
    }

    public PosicionSeguimiento(Integer idPosicionSeguimiento) {
        this.idPosicionSeguimiento = idPosicionSeguimiento;
    }

    public PosicionSeguimiento(Integer idPosicionSeguimiento, Date fecha, String posicion) {
        this.idPosicionSeguimiento = idPosicionSeguimiento;
        this.fecha = fecha;
        this.posicion = posicion;
    }

    public Integer getIdPosicionSeguimiento() {
        return idPosicionSeguimiento;
    }

    public void setIdPosicionSeguimiento(Integer idPosicionSeguimiento) {
        this.idPosicionSeguimiento = idPosicionSeguimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @XmlTransient
    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPosicionSeguimiento != null ? idPosicionSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionSeguimiento)) {
            return false;
        }
        PosicionSeguimiento other = (PosicionSeguimiento) object;
        if ((this.idPosicionSeguimiento == null && other.idPosicionSeguimiento != null) || (this.idPosicionSeguimiento != null && !this.idPosicionSeguimiento.equals(other.idPosicionSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.PosicionSeguimiento[ idPosicionSeguimiento=" + idPosicionSeguimiento + " ]";
    }

    @Override
    public String obtenerLlavePrimaria() {
        return idPosicionSeguimiento.toString();
    }
    
}
