/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.vista.bean;

import co.nqs.cuestionario.modelo.CuestionarioGlosario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luiseduardoramirez
 */
@Stateless
public class CuestionarioGlosarioFacade extends AbstractFacade<CuestionarioGlosario> {

    @PersistenceContext(unitName = "CuestionarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuestionarioGlosarioFacade() {
        super(CuestionarioGlosario.class);
    }
    
    /**
     * Método para consultar el listado glosarios que tiene un determinado
     * cuestionario por tipo de pregunta
     * @param cuestionarioId
     * @param tipoPregunta
     * @return Listado de los glosarios del cuestionario
     */
    public List<CuestionarioGlosario> buscarCuestionarioGlosarioPorIdCuestionario(
            Integer cuestionarioId,int tipoPregunta) {
        Query q = getEntityManager().createNamedQuery("CuestionarioGlosario.findByCuestionarioId");
        q.setParameter("cuestionarioId", cuestionarioId);
        q.setParameter("tipoPregunta", tipoPregunta);
        return (List<CuestionarioGlosario>)q.getResultList();
    }
}
