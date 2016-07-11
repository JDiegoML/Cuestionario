/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.vista.bean;

import co.nqs.cuestionario.modelo.Cuestionario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luiseduardoramirez
 */
@Stateless
public class CuestionarioFacade extends AbstractFacade<Cuestionario> {

    @PersistenceContext(unitName = "CuestionarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuestionarioFacade() {
        super(Cuestionario.class);
    }
    
    /**
     * Método para consultar el objeto cuestionario que fue el último 
     * que se creo en la base de datos
     * @return Objeto Cuestionario
     */
    public Cuestionario consultarUltimoCuestionario(){
        Cuestionario cuestionario=new Cuestionario();
        javax.persistence.Query query = getEntityManager()
                .createNativeQuery(consultaSQLUltimoCuestionario(), Cuestionario.class);
        cuestionario=(Cuestionario)query.getSingleResult();
        return cuestionario;
    }
    
    /**
     * Método para crear la consulta sql que nos devuelve
     * el script del ultimo cuestionario que se creo en la
     * base de datos
     * @return Consulta sql
     */
    public String consultaSQLUltimoCuestionario(){
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT * FROM cuestionario ");
        sql.append("WHERE cuestionario_id IN ");
        sql.append("(SELECT MAX(cuestionario_id) ");
        sql.append("FROM cuestionario)");
        return sql.toString();
    }
}
