/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Promociones;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author elias
 */
@Stateless
public class PromocionesFacade extends AbstractFacade<Promociones> {

    @PersistenceContext(unitName = "com.mycompany_promoSJ_dash_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PromocionesFacade() {
        super(Promociones.class);
    }

    // Trae las promociones de un comercio
    public List<Promociones> findPromocionesByComercio(int comercioId) {
        return em.createQuery("SELECT p FROM Promociones p WHERE p.idComercio.idComercio = :comercioId", Promociones.class)
                .setParameter("comercioId", comercioId)
                .getResultList();
    }

}
