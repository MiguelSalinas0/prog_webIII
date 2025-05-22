/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Comercios;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author elias
 */
@Stateless
public class ComerciosFacade extends AbstractFacade<Comercios> {

    @PersistenceContext(unitName = "com.mycompany_promoSJ_dash_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComerciosFacade() {
        super(Comercios.class);
    }

    // Método para iniciar sesión (buscar comercio por email y password)
//    public Comercios login(String email, String password) {
//        try {
//            return em.createQuery("SELECT c FROM Comercios c WHERE c.email = :email AND c.password = :password", Comercios.class)
//                    .setParameter("email", email)
//                    .setParameter("password", password)
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return null;  // Si no hay coincidencias, retornamos null
//        }
//    }
    public Comercios login(String email, String password) {
        try {
            return em.createQuery(
                    "SELECT c FROM Comercios c WHERE c.idUsuario.email = :email AND c.idUsuario.password = :password",
                    Comercios.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
