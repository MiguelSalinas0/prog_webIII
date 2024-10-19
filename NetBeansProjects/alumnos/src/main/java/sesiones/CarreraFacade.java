/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Carrera;
import entidades.Facultad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author elias
 */
@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> {

    @PersistenceContext(unitName = "com.mycompany_alumnos_war_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }
    
    // Método para obtener todas las carreras según la facultad
    public List<Carrera> findCarrerasByFacultad(Facultad facultad) {
        TypedQuery<Carrera> query = em.createQuery(
            "SELECT c FROM Carrera c WHERE c.facultadIdfacultad = :facultad", Carrera.class);
        query.setParameter("facultad", facultad);
        return query.getResultList();
    }
    
}
