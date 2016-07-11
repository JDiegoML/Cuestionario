/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.modelo.dto;

import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import co.nqs.cuestionario.modelo.GlosarioOpcion;
import java.io.Serializable;

/**
 *
 * @author luiseduardoramirez
 */
public class PreguntaDTO implements Serializable{
    
    private CuestionarioGlosario cuestionarioGlosario;
    private GlosarioOpcion respuesta;
    private Boolean seleccion;

    public PreguntaDTO() {
    }
    
    public PreguntaDTO(CuestionarioGlosario cuestionarioGlosario, GlosarioOpcion respuesta) {
        this.cuestionarioGlosario = cuestionarioGlosario;
        this.respuesta = respuesta;
    }

    public CuestionarioGlosario getCuestionarioGlosario() {
        return cuestionarioGlosario;
    }

    public void setCuestionarioGlosario(CuestionarioGlosario cuestionarioGlosario) {
        this.cuestionarioGlosario = cuestionarioGlosario;
    }

    public GlosarioOpcion getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(GlosarioOpcion respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Boolean seleccion) {
        this.seleccion = seleccion;
    }

    
    
    
    
}
