/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luiseduardoramirez
 */
@Entity
@Table(name = "glosario_opcion", catalog = "cuestionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlosarioOpcion.findAll", query = "SELECT g FROM GlosarioOpcion g"),
    @NamedQuery(name = "GlosarioOpcion.findByGlosarioOpcionId", query = "SELECT g FROM GlosarioOpcion g WHERE g.glosarioOpcionId = :glosarioOpcionId"),
    @NamedQuery(name = "GlosarioOpcion.findByGlosarioIdCuestionarioId", 
            query = "SELECT g FROM GlosarioOpcion g WHERE g.glosarioId.glosarioId = :glosarioId AND g.cuestionarioId.cuestionarioId= :cuestionarioId "),
    @NamedQuery(name = "GlosarioOpcion.findByEsCorrecta", query = "SELECT g FROM GlosarioOpcion g WHERE g.esCorrecta = :esCorrecta")})
public class GlosarioOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "glosario_opcion_id", nullable = false)
    private Integer glosarioOpcionId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String opcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_correcta", nullable = false)
    private boolean esCorrecta;
    @JoinColumn(name = "cuestionario_id", referencedColumnName = "cuestionario_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuestionario cuestionarioId;
    @JoinColumn(name = "glosario_id", referencedColumnName = "glosario_id", nullable = false)
    @ManyToOne(optional = false)
    private Glosario glosarioId;

    public GlosarioOpcion() {
    }

    public GlosarioOpcion(Integer glosarioOpcionId) {
        this.glosarioOpcionId = glosarioOpcionId;
    }

    public GlosarioOpcion(Integer glosarioOpcionId, String opcion, boolean esCorrecta) {
        this.glosarioOpcionId = glosarioOpcionId;
        this.opcion = opcion;
        this.esCorrecta = esCorrecta;
    }

    public Integer getGlosarioOpcionId() {
        return glosarioOpcionId;
    }

    public void setGlosarioOpcionId(Integer glosarioOpcionId) {
        this.glosarioOpcionId = glosarioOpcionId;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public boolean getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public Cuestionario getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(Cuestionario cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public Glosario getGlosarioId() {
        return glosarioId;
    }

    public void setGlosarioId(Glosario glosarioId) {
        this.glosarioId = glosarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (glosarioOpcionId != null ? glosarioOpcionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlosarioOpcion)) {
            return false;
        }
        GlosarioOpcion other = (GlosarioOpcion) object;
        if ((this.glosarioOpcionId == null && other.glosarioOpcionId != null) || (this.glosarioOpcionId != null && !this.glosarioOpcionId.equals(other.glosarioOpcionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.nqs.cuestionario.modelo.GlosarioOpcion[ glosarioOpcionId=" + glosarioOpcionId + " ]";
    }
    
}
