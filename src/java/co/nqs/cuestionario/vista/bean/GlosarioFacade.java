/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.vista.bean;

import co.nqs.cuestionario.modelo.Glosario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luiseduardoramirez
 */
@Stateless
public class GlosarioFacade extends AbstractFacade<Glosario> {

    @PersistenceContext(unitName = "CuestionarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlosarioFacade() {
        super(Glosario.class);
    }
    
    /**
     * Método para consultar el listado de glosarios por tema y cuestionario
     * @param temaId
     * @param cantidadPalabras
     * @param cuestionarioId
     * @return Objeto Cuestionario
     */
    public List<Glosario> consultarGlosarioPorTema(Integer temaId,
            Integer cantidadPalabras,Integer cuestionarioId){
        List<Glosario> glosarios=new ArrayList<>();
        javax.persistence.Query query = getEntityManager()
                .createNativeQuery(consultaGlosarioNuevo(), Glosario.class);
        query.setParameter(1, temaId);
        query.setParameter(2, cuestionarioId);
        query.setParameter(3,cantidadPalabras);
        glosarios=(List<Glosario>)query.getResultList();
        return glosarios;
    }
    
    /**
     * Método para consultar el listado de glosarios por tema que seran 
     * usados para crear las opciones de respuesta
     * @param temaId
     * @param cantidadPalabras
     * @param glosarioId
     * @return Objeto Cuestionario
     */
    public List<Glosario> consultarGlosarioOpcionesRespuestaMultiple(Integer temaId,
            int cantidadPalabras,Integer glosarioId){
        List<Glosario> glosarios=new ArrayList<>();
        javax.persistence.Query query = getEntityManager()
                .createNativeQuery(consultaGlosarioOpcionesRespuestaMultiple(), Glosario.class);
        query.setParameter(1, temaId);
        query.setParameter(2,glosarioId);
        query.setParameter(3,cantidadPalabras);
        query.setParameter(4,glosarioId);
        glosarios=(List<Glosario>)query.getResultList();
        return glosarios;
    }
    
    /**
     * Método para consultar el objeto glosario que va ser la opción de respuesta
     * para el glosario con opción de respuesta de tipo FALSO - VERDADERO
     * @param temaId
     * @return Objeto Glosario
     */
    public Glosario consultarGlosarioOpcionRespuestaFalsoVerdadero(Integer temaId){
        Glosario glosario=new Glosario();
        javax.persistence.Query query = getEntityManager()
                .createNativeQuery(consultaGlosarioOpcionesRespuestaFalsoVerdadero(),
                        Glosario.class);
        query.setParameter(1, temaId);
        glosario=(Glosario)query.getSingleResult();
        return glosario;
    }
    
    /**
     * Método para crear la consulta sql que nos devuelve
     * el script del del listado de glosarios de acuerdo al
     * tema y cuestionario, adicionalmente se trae los registros
     * de forma aleatoria
     * @return Consulta sql
     */
    private String consultaGlosarioNuevo(){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT * FROM glosario ");
        sql.append("WHERE tema_id =?1 AND glosario_id NOT IN ");
        sql.append("(SELECT glosario_id ");
        sql.append("FROM cuestionario_glosario cg ");
        sql.append("WHERE cg.cuestionario_id=?2) ");
        sql.append("ORDER BY Rand() ");
        sql.append("Limit ?3 ");
        return sql.toString();
    }
    
    /**
     * Método para crear la consulta sql que nos devuelve
     * el script con los glosarios para las opciones de 
     * respuesta. En esta consulta se incluye la respuesta
     * de la pregunta para que se organice aleatoriamente
     * @return Consulta sql
     */
    private String consultaGlosarioOpcionesRespuestaMultiple(){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT * FROM glosario ");
        sql.append("WHERE tema_id =?1  AND glosario_id<>?2 ");
        sql.append("Limit ?3 ");
        sql.append("UNION ALL ");
        sql.append("SELECT * FROM glosario ");
        sql.append("WHERE glosario_id=?4  ");
        sql.append("ORDER BY Rand() ");
        return sql.toString();
    }
    
    /**
     * Método para crear la consulta sql que nos devuelve
     * el script de la opciòn de respuesta para las preguntas
     * que son de tipo respuesta FALSO - VERDADERO
     * @return Consulta sql
     */
    private String consultaGlosarioOpcionesRespuestaFalsoVerdadero(){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT * FROM glosario ");
        sql.append("WHERE tema_id =?1 ");
        sql.append("ORDER BY Rand() ");
        sql.append("Limit 1 ");
        return sql.toString();
    }

    
}
