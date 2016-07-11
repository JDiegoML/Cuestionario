/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author luiseduardoramirez
 */
@Entity
@Table(catalog = "cuestionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuestionario.findAll", query = "SELECT c FROM Cuestionario c"),
    @NamedQuery(name = "Cuestionario.findByCuestionarioId", query = "SELECT c FROM Cuestionario c WHERE c.cuestionarioId = :cuestionarioId"),
    @NamedQuery(name = "Cuestionario.findByNombre", query = "SELECT c FROM Cuestionario c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cuestionario.findByCantidadMultiple", query = "SELECT c FROM Cuestionario c WHERE c.cantidadMultiple = :cantidadMultiple"),
    @NamedQuery(name = "Cuestionario.findByCantidadFalsoVerdadero", query = "SELECT c FROM Cuestionario c WHERE c.cantidadFalsoVerdadero = :cantidadFalsoVerdadero"),
    @NamedQuery(name = "Cuestionario.findByCantidadAbierta", query = "SELECT c FROM Cuestionario c WHERE c.cantidadAbierta = :cantidadAbierta")})
public class Cuestionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuestionario_id", nullable = false)
    private Integer cuestionarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(name = "cantidad_multiple")
    private Integer cantidadMultiple;
    @Column(name = "cantidad_falso_verdadero")
    private Integer cantidadFalsoVerdadero;
    @Column(name = "cantidad_abierta")
    private Integer cantidadAbierta;
    @JoinColumn(name = "tema_id", referencedColumnName = "tema_id")
    @ManyToOne
    private Tema temaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuestionario")
    private List<CuestionarioGlosario> cuestionarioGlosarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuestionarioId")
    private List<GlosarioOpcion> glosarioOpcionList;

    public Cuestionario() {
    }

    public Cuestionario(Integer cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public Cuestionario(Integer cuestionarioId, String nombre) {
        this.cuestionarioId = cuestionarioId;
        this.nombre = nombre;
    }

    public Integer getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(Integer cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadMultiple() {
        return cantidadMultiple;
    }

    public void setCantidadMultiple(Integer cantidadMultiple) {
        this.cantidadMultiple = cantidadMultiple;
    }

    public Integer getCantidadFalsoVerdadero() {
        return cantidadFalsoVerdadero;
    }

    public void setCantidadFalsoVerdadero(Integer cantidadFalsoVerdadero) {
        this.cantidadFalsoVerdadero = cantidadFalsoVerdadero;
    }

    public Integer getCantidadAbierta() {
        return cantidadAbierta;
    }

    public void setCantidadAbierta(Integer cantidadAbierta) {
        this.cantidadAbierta = cantidadAbierta;
    }

    public Tema getTemaId() {
        return temaId;
    }

    public void setTemaId(Tema temaId) {
        this.temaId = temaId;
    }

    @XmlTransient
    public List<CuestionarioGlosario> getCuestionarioGlosarioList() {
        return cuestionarioGlosarioList;
    }

    public void setCuestionarioGlosarioList(List<CuestionarioGlosario> cuestionarioGlosarioList) {
        this.cuestionarioGlosarioList = cuestionarioGlosarioList;
    }

    @XmlTransient
    public List<GlosarioOpcion> getGlosarioOpcionList() {
        return glosarioOpcionList;
    }

    public void setGlosarioOpcionList(List<GlosarioOpcion> glosarioOpcionList) {
        this.glosarioOpcionList = glosarioOpcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuestionarioId != null ? cuestionarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuestionario)) {
            return false;
        }
        Cuestionario other = (Cuestionario) object;
        if ((this.cuestionarioId == null && other.cuestionarioId != null) || (this.cuestionarioId != null && !this.cuestionarioId.equals(other.cuestionarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.nqs.cuestionario.modelo.Cuestionario[ cuestionarioId=" + cuestionarioId + " ]";
    }
    
}
