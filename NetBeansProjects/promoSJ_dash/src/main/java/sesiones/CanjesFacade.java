/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Canjes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author elias
 */
@Stateless
public class CanjesFacade extends AbstractFacade<Canjes> {

    @PersistenceContext(unitName = "com.mycompany_promoSJ_dash_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CanjesFacade() {
        super(Canjes.class);
    }
    
}
