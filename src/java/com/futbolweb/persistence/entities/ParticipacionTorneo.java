/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author camila
 */
@Entity
@Table(name = "participaciones_torneo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipacionTorneo.findAll", query = "SELECT p FROM ParticipacionTorneo p"),
    @NamedQuery(name = "ParticipacionTorneo.findByIdParticipacionTorneo", query = "SELECT p FROM ParticipacionTorneo p WHERE p.idParticipacionTorneo = :idParticipacionTorneo"),
    @NamedQuery(name = "ParticipacionTorneo.findByDescripcion", query = "SELECT p FROM ParticipacionTorneo p WHERE p.descripcion = :descripcion")})
public class ParticipacionTorneo implements Serializable, IDTO {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_participacion_torneo")
    private Integer idParticipacionTorneo;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Equipo idEquipo;
    @JoinColumn(name = "id_torneo", referencedColumnName = "id_torneo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Torneo idTorneo;

    public ParticipacionTorneo() {
    }

    public ParticipacionTorneo(Integer idParticipacionTorneo) {
        this.idParticipacionTorneo = idParticipacionTorneo;
    }

    public Integer getIdParticipacionTorneo() {
        return idParticipacionTorneo;
    }

    public void setIdParticipacionTorneo(Integer idParticipacionTorneo) {
        this.idParticipacionTorneo = idParticipacionTorneo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Torneo getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Torneo idTorneo) {
        this.idTorneo = idTorneo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParticipacionTorneo != null ? idParticipacionTorneo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipacionTorneo)) {
            return false;
        }
        ParticipacionTorneo other = (ParticipacionTorneo) object;
        if ((this.idParticipacionTorneo == null && other.idParticipacionTorneo != null) || (this.idParticipacionTorneo != null && !this.idParticipacionTorneo.equals(other.idParticipacionTorneo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.ParticipacionesTorneo[ idParticipacionTorneo=" + idParticipacionTorneo + " ]";
    }

    @Override
    public String obtenerLlavePrimaria() {

        return idParticipacionTorneo.toString();
    }
    
}
