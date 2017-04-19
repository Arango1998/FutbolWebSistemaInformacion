/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futbolweb.persistence.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian Suesca
 */
@Entity
@Table(name = "horarios_club")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorarioClub.findAll", query = "SELECT h FROM HorarioClub h"),
    @NamedQuery(name = "HorarioClub.findByIdHorario", query = "SELECT h FROM HorarioClub h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "HorarioClub.findByHoraInicio", query = "SELECT h FROM HorarioClub h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "HorarioClub.findByHoraFin", query = "SELECT h FROM HorarioClub h WHERE h.horaFin = :horaFin")})
public class HorarioClub implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario")
    private Integer idHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    @JoinColumn(name = "fk_id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Equipo fkIdEquipo;

    public HorarioClub() {
    }

    public HorarioClub(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public HorarioClub(Integer idHorario, Date horaInicio, Date horaFin) {
        this.idHorario = idHorario;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Equipo getFkIdEquipo() {
        return fkIdEquipo;
    }

    public void setFkIdEquipo(Equipo fkIdEquipo) {
        this.fkIdEquipo = fkIdEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioClub)) {
            return false;
        }
        HorarioClub other = (HorarioClub) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.HorarioClub[ idHorario=" + idHorario + " ]";
    }
    
}
