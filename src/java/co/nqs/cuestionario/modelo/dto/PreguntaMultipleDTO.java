/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo.dto;

import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import co.nqs.cuestionario.modelo.GlosarioOpcion;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author luiseduardoramirez
 */
public class PreguntaMultipleDTO implements Serializable{
    
    private CuestionarioGlosario cuestionarioGlosario;
    private List<GlosarioOpcion> listadoRespuestas;

    public PreguntaMultipleDTO() {
    }
    
    public PreguntaMultipleDTO(CuestionarioGlosario cuestionarioGlosario, List<GlosarioOpcion> listadoRespuestas) {
        this.cuestionarioGlosario = cuestionarioGlosario;
        this.listadoRespuestas = listadoRespuestas;
    }

    public CuestionarioGlosario getCuestionarioGlosario() {
        return cuestionarioGlosario;
    }

    public void setCuestionarioGlosario(CuestionarioGlosario cuestionarioGlosario) {
        this.cuestionarioGlosario = cuestionarioGlosario;
    }

    public List<GlosarioOpcion> getListadoRespuestas() {
        return listadoRespuestas;
    }

    public void setListadoRespuestas(List<GlosarioOpcion> listadoRespuestas) {
        this.listadoRespuestas = listadoRespuestas;
    }
}
