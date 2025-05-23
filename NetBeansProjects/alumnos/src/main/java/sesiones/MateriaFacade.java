/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Materia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author elias
 */
@Stateless
public class MateriaFacade extends AbstractFacade<Materia> {

    @PersistenceContext(unitName = "com.mycompany_alumnos_war_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaFacade() {
        super(Materia.class);
    }
    
}
