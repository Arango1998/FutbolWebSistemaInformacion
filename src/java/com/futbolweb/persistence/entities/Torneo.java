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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "torneos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Torneo.findAll", query = "SELECT t FROM Torneo t"),
    @NamedQuery(name = "Torneo.findByIdTorneo", query = "SELECT t FROM Torneo t WHERE t.idTorneo = :idTorneo"),
    @NamedQuery(name = "Torneo.findByNombreTorneo", query = "SELECT t FROM Torneo t WHERE t.nombreTorneo = :nombreTorneo"),
    @NamedQuery(name = "Torneo.findByOrganizador", query = "SELECT t FROM Torneo t WHERE t.organizador = :organizador"),
    @NamedQuery(name = "Torneo.findByFechaInicio", query = "SELECT t FROM Torneo t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Torneo.findByFechaFin", query = "SELECT t FROM Torneo t WHERE t.fechaFin = :fechaFin")})
public class Torneo implements Serializable, IDTO {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTorneo", fetch = FetchType.EAGER)
    private List<ParticipacionTorneo> participacionesTorneoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_torneo")
    private Integer idTorneo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_torneo")
    private String nombreTorneo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "organizador")
    private String organizador;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "datos_contacto_organizador")
    private String datosContactoOrganizador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @ManyToMany(mappedBy = "torneoList", fetch = FetchType.EAGER)
    private List<Equipo> equipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTorneo", fetch = FetchType.EAGER)
    private List<EncuentroTorneo> encuentroTorneoList;

    public Torneo() {
    }

    public Torneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public Torneo(Integer idTorneo, String nombreTorneo, String organizador, String datosContactoOrganizador, Date fechaInicio, Date fechaFin) {
        this.idTorneo = idTorneo;
        this.nombreTorneo = nombreTorneo;
        this.organizador = organizador;
        this.datosContactoOrganizador = datosContactoOrganizador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getDatosContactoOrganizador() {
        return datosContactoOrganizador;
    }

    public void setDatosContactoOrganizador(String datosContactoOrganizador) {
        this.datosContactoOrganizador = datosContactoOrganizador;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @XmlTransient
    public List<EncuentroTorneo> getEncuentroTorneoList() {
        return encuentroTorneoList;
    }

    public void setEncuentroTorneoList(List<EncuentroTorneo> encuentroTorneoList) {
        this.encuentroTorneoList = encuentroTorneoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTorneo != null ? idTorneo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Torneo)) {
            return false;
        }
        Torneo other = (Torneo) object;
        if ((this.idTorneo == null && other.idTorneo != null) || (this.idTorneo != null && !this.idTorneo.equals(other.idTorneo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.Torneo[ idTorneo=" + idTorneo + " ]";
    }

    @Override
    public String obtenerLlavePrimaria() {
        return idTorneo.toString();
    }

    @XmlTransient
    public List<ParticipacionTorneo> getParticipacionesTorneoList() {
        return participacionesTorneoList;
    }

    public void setParticipacionesTorneoList(List<ParticipacionTorneo> participacionesTorneoList) {
        this.participacionesTorneoList = participacionesTorneoList;
    }

}
