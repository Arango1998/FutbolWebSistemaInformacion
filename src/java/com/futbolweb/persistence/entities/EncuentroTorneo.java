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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian Suesca
 */
@Entity
@Table(name = "encuentros_torneo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuentroTorneo.findAll", query = "SELECT e FROM EncuentroTorneo e"),
    @NamedQuery(name = "EncuentroTorneo.findByIdEncuentro", query = "SELECT e FROM EncuentroTorneo e WHERE e.idEncuentro = :idEncuentro"),
    @NamedQuery(name = "EncuentroTorneo.findByFechaInicio", query = "SELECT e FROM EncuentroTorneo e WHERE e.fechaInicio = :fechaInicio")})
public class EncuentroTorneo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuentro")
    private Integer idEncuentro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "resultado")
    private String resultado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuentro", fetch = FetchType.EAGER)
    private List<SeguimientoEncuentro> seguimientoEncuentroList;
    @JoinColumn(name = "fk_id_torneo", referencedColumnName = "id_torneo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Torneo fkIdTorneo;
    @JoinColumn(name = "fk_id_barrio", referencedColumnName = "id_barrio")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Barrio fkIdBarrio;

    public EncuentroTorneo() {
    }

    public EncuentroTorneo(Integer idEncuentro) {
        this.idEncuentro = idEncuentro;
    }

    public EncuentroTorneo(Integer idEncuentro, Date fechaInicio, String descripcion, String resultado) {
        this.idEncuentro = idEncuentro;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.resultado = resultado;
    }

    public Integer getIdEncuentro() {
        return idEncuentro;
    }

    public void setIdEncuentro(Integer idEncuentro) {
        this.idEncuentro = idEncuentro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @XmlTransient
    public List<SeguimientoEncuentro> getSeguimientoEncuentroList() {
        return seguimientoEncuentroList;
    }

    public void setSeguimientoEncuentroList(List<SeguimientoEncuentro> seguimientoEncuentroList) {
        this.seguimientoEncuentroList = seguimientoEncuentroList;
    }

    public Torneo getFkIdTorneo() {
        return fkIdTorneo;
    }

    public void setFkIdTorneo(Torneo fkIdTorneo) {
        this.fkIdTorneo = fkIdTorneo;
    }

    public Barrio getFkIdBarrio() {
        return fkIdBarrio;
    }

    public void setFkIdBarrio(Barrio fkIdBarrio) {
        this.fkIdBarrio = fkIdBarrio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuentro != null ? idEncuentro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuentroTorneo)) {
            return false;
        }
        EncuentroTorneo other = (EncuentroTorneo) object;
        if ((this.idEncuentro == null && other.idEncuentro != null) || (this.idEncuentro != null && !this.idEncuentro.equals(other.idEncuentro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.futbolweb.persistence.entities.EncuentroTorneo[ idEncuentro=" + idEncuentro + " ]";
    }
    
}
