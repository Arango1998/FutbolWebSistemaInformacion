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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian Suesca
 */
@Entity
@Table(name = "seguimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s"),
    @NamedQuery(name = "Seguimiento.findByIdSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.idSeguimiento = :idSeguimiento"),
    @NamedQuery(name = "Seguimiento.findByFechaSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.fechaSeguimiento = :fechaSeguimiento"),
    @NamedQuery(name = "Seguimiento.findByResultadoSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.resultadoSeguimiento = :resultadoSeguimiento")})
public class Seguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;
    @Column(name = "fecha_seguimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguimiento;
    @Size(max = 40)
    @Column(name = "resultado_seguimiento")
    private String resultadoSeguimiento;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "id_jugador", referencedColumnName = "id_jugador")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Jugador idJugador;
    @JoinColumn(name = "id_entrenador", referencedColumnName = "id_entrenador")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entrenador idEntrenador;
    @JoinColumn(name = "id_posicion_seguimiento", referencedColumnName = "id_posicion_seguimiento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PosicionSeguimiento idPosicionSeguimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeguimiento", fetch = FetchType.EAGER)
    private List<Valoracion> valoracionList;

    public Seguimiento() {
    }

    public Seguimiento(Integer idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Integer getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Integer idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getResultadoSeguimiento() {
        return resultadoSeguimiento;
    }

    public void setResultadoSeguimiento(String resultadoSeguimiento) {
        this.resultadoSeguimiento = resultadoSeguimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    public Entrenador getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Entrenador idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public PosicionSeguimiento getIdPosicionSeguimiento() {
        return idPosicionSeguimiento;
    }

    public void setIdPosicionSeguimiento(PosicionSeguimiento idPosicionSeguimiento) {
        this.idPosicionSeguimiento = idPosicionSeguimiento;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeguimiento != null ? idSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.idSeguimiento == null && other.idSeguimiento != null) || (this.idSeguimiento != null && !this.idSeguimiento.equals(other.idSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.Seguimiento[ idSeguimiento=" + idSeguimiento + " ]";
    }
    
}
