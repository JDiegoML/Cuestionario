/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.vista.bean;

import co.nqs.cuestionario.modelo.GlosarioOpcion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luiseduardoramirez
 */
@Stateless
public class GlosarioOpcionFacade extends AbstractFacade<GlosarioOpcion> {

    @PersistenceContext(unitName = "CuestionarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlosarioOpcionFacade() {
        super(GlosarioOpcion.class);
    }
    
    /**
     * Método para consultar el listado opciones de respuesta que tiene cada glosario
     * de acuerdo al número de cuestionario ingresado y el glosario
     * cuestionario
     * @param cuestionarioId
     * @param glosarioId
     * @return Listado de los glosarios del cuestionario
     */
    public List<GlosarioOpcion> buscarListaGlosarioOpcionPorIdCuestionarioIdGlosario(
            Integer cuestionarioId,Integer glosarioId) {
        Query q = getEntityManager().createNamedQuery("GlosarioOpcion.findByGlosarioIdCuestionarioId");
        q.setParameter("cuestionarioId", cuestionarioId);
        q.setParameter("glosarioId", glosarioId);
        return (List<GlosarioOpcion>)q.getResultList();
    }
    
    /**
     * Método para consultar el listado opciones de respuesta que tiene cada glosario
     * de acuerdo al número de cuestionario ingresado y el glosario
     * cuestionario
     * @param cuestionarioId
     * @param glosarioId
     * @return Opcion respuesta cuestionario
     */
    public GlosarioOpcion buscarGlosarioOpcionPorIdCuestionarioIdGlosario(
            Integer cuestionarioId,Integer glosarioId) {
        try{
            Query q = getEntityManager().createNamedQuery("GlosarioOpcion.findByGlosarioIdCuestionarioId");
            q.setParameter("cuestionarioId", cuestionarioId);
            q.setParameter("glosarioId", glosarioId);
            List<GlosarioOpcion> listado=q.getResultList();
            if(!listado.isEmpty()){
                return (GlosarioOpcion)q.getResultList().get(0);
            }else{
                return new GlosarioOpcion();
            }
        }catch(NoResultException e){
            return new GlosarioOpcion();
        }
    }
}
