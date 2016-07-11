/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luiseduardoramirez
 */
@Entity
@Table(name = "cuestionario_glosario", catalog = "cuestionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuestionarioGlosario.findAll", query = "SELECT c FROM CuestionarioGlosario c"),
    @NamedQuery(name = "CuestionarioGlosario.findByCuestionarioId", query = "SELECT c FROM CuestionarioGlosario c WHERE c.cuestionarioGlosarioPK.cuestionarioId = :cuestionarioId AND c.tipoPregunta = :tipoPregunta"),
    @NamedQuery(name = "CuestionarioGlosario.findByGlosarioId", query = "SELECT c FROM CuestionarioGlosario c WHERE c.cuestionarioGlosarioPK.glosarioId = :glosarioId"),
    @NamedQuery(name = "CuestionarioGlosario.findByTipoPregunta", query = "SELECT c FROM CuestionarioGlosario c WHERE c.tipoPregunta = :tipoPregunta")})
public class CuestionarioGlosario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuestionarioGlosarioPK cuestionarioGlosarioPK;
    @Column(name = "tipo_pregunta")
    private Integer tipoPregunta;
    @JoinColumn(name = "cuestionario_id", referencedColumnName = "cuestionario_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuestionario cuestionario;
    @JoinColumn(name = "glosario_id", referencedColumnName = "glosario_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Glosario glosario;

    public CuestionarioGlosario() {
    }

    public CuestionarioGlosario(CuestionarioGlosarioPK cuestionarioGlosarioPK) {
        this.cuestionarioGlosarioPK = cuestionarioGlosarioPK;
    }

    public CuestionarioGlosario(int cuestionarioId, int glosarioId) {
        this.cuestionarioGlosarioPK = new CuestionarioGlosarioPK(cuestionarioId, glosarioId);
    }

    public CuestionarioGlosarioPK getCuestionarioGlosarioPK() {
        return cuestionarioGlosarioPK;
    }

    public void setCuestionarioGlosarioPK(CuestionarioGlosarioPK cuestionarioGlosarioPK) {
        this.cuestionarioGlosarioPK = cuestionarioGlosarioPK;
    }

    public Integer getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(Integer tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Glosario getGlosario() {
        return glosario;
    }

    public void setGlosario(Glosario glosario) {
        this.glosario = glosario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuestionarioGlosarioPK != null ? cuestionarioGlosarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuestionarioGlosario)) {
            return false;
        }
        CuestionarioGlosario other = (CuestionarioGlosario) object;
        if ((this.cuestionarioGlosarioPK == null && other.cuestionarioGlosarioPK != null) || (this.cuestionarioGlosarioPK != null && !this.cuestionarioGlosarioPK.equals(other.cuestionarioGlosarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.nqs.cuestionario.modelo.CuestionarioGlosario[ cuestionarioGlosarioPK=" + cuestionarioGlosarioPK + " ]";
    }
    
}
