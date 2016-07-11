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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Glosario.findAll", query = "SELECT g FROM Glosario g"),
    @NamedQuery(name = "Glosario.findByGlosarioId", query = "SELECT g FROM Glosario g WHERE g.glosarioId = :glosarioId"),
    @NamedQuery(name = "Glosario.findByNombre", query = "SELECT g FROM Glosario g WHERE g.nombre = :nombre")})
public class Glosario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "glosario_id", nullable = false)
    private Integer glosarioId;
    @Size(max = 50)
    @Column(length = 50)
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String descripcion;
    @JoinColumn(name = "tema_id", referencedColumnName = "tema_id")
    @ManyToOne
    private Tema temaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glosario")
    private List<CuestionarioGlosario> cuestionarioGlosarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glosarioId")
    private List<GlosarioOpcion> glosarioOpcionList;

    public Glosario() {
    }

    public Glosario(Integer glosarioId) {
        this.glosarioId = glosarioId;
    }

    public Integer getGlosarioId() {
        return glosarioId;
    }

    public void setGlosarioId(Integer glosarioId) {
        this.glosarioId = glosarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (glosarioId != null ? glosarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Glosario)) {
            return false;
        }
        Glosario other = (Glosario) object;
        if ((this.glosarioId == null && other.glosarioId != null) || (this.glosarioId != null && !this.glosarioId.equals(other.glosarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.nqs.cuestionario.modelo.Glosario[ glosarioId=" + glosarioId + " ]";
    }
    
}
