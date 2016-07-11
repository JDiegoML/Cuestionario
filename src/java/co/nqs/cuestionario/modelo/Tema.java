/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Tema.findAll", query = "SELECT t FROM Tema t"),
    @NamedQuery(name = "Tema.findByTemaId", query = "SELECT t FROM Tema t WHERE t.temaId = :temaId"),
    @NamedQuery(name = "Tema.findByNombre", query = "SELECT t FROM Tema t WHERE t.nombre = :nombre")})
public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tema_id", nullable = false)
    private Integer temaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String descripcion;
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id", nullable = false)
    @ManyToOne(optional = false)
    private Curso cursoId;
    @OneToMany(mappedBy = "temaId")
    private List<Glosario> glosarioList;
    @OneToMany(mappedBy = "temaId")
    private List<Cuestionario> cuestionarioList;

    public Tema() {
    }

    public Tema(Integer temaId) {
        this.temaId = temaId;
    }

    public Tema(Integer temaId, String nombre, String descripcion) {
        this.temaId = temaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getTemaId() {
        return temaId;
    }

    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
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

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    @XmlTransient
    public List<Glosario> getGlosarioList() {
        return glosarioList;
    }

    public void setGlosarioList(List<Glosario> glosarioList) {
        this.glosarioList = glosarioList;
    }

    @XmlTransient
    public List<Cuestionario> getCuestionarioList() {
        return cuestionarioList;
    }

    public void setCuestionarioList(List<Cuestionario> cuestionarioList) {
        this.cuestionarioList = cuestionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temaId != null ? temaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tema)) {
            return false;
        }
        Tema other = (Tema) object;
        if ((this.temaId == null && other.temaId != null) || (this.temaId != null && !this.temaId.equals(other.temaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(temaId!=null){
            return nombre.trim();
        }else{
            return "";
        }
    }
    
}
