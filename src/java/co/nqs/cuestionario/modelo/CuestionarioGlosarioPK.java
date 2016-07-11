/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author luiseduardoramirez
 */
@Embeddable
public class CuestionarioGlosarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cuestionario_id", nullable = false)
    private int cuestionarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "glosario_id", nullable = false)
    private int glosarioId;

    public CuestionarioGlosarioPK() {
    }

    public CuestionarioGlosarioPK(int cuestionarioId, int glosarioId) {
        this.cuestionarioId = cuestionarioId;
        this.glosarioId = glosarioId;
    }

    public int getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(int cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public int getGlosarioId() {
        return glosarioId;
    }

    public void setGlosarioId(int glosarioId) {
        this.glosarioId = glosarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cuestionarioId;
        hash += (int) glosarioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuestionarioGlosarioPK)) {
            return false;
        }
        CuestionarioGlosarioPK other = (CuestionarioGlosarioPK) object;
        if (this.cuestionarioId != other.cuestionarioId) {
            return false;
        }
        if (this.glosarioId != other.glosarioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.nqs.cuestionario.modelo.CuestionarioGlosarioPK[ cuestionarioId=" + cuestionarioId + ", glosarioId=" + glosarioId + " ]";
    }
    
}
