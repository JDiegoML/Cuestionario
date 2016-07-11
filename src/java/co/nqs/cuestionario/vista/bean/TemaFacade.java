/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.nqs.cuestionario.vista.bean;

import co.nqs.cuestionario.modelo.Tema;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luiseduardoramirez
 */
@Stateless
public class TemaFacade extends AbstractFacade<Tema> {

    @PersistenceContext(unitName = "CuestionarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TemaFacade() {
        super(Tema.class);
    }
    
}
