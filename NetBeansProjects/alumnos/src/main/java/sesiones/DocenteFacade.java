/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Docente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author elias
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> {

    @PersistenceContext(unitName = "com.mycompany_alumnos_war_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }

    // Listado de alumnos que han rendido las materias que dicta
//    public List<Alumno> findAlumnosByDocente(int idDocente) {
//        return em.createQuery(
//                "SELECT m.nombre AS materia, a.nombre AS alumno "
//                + "FROM Materia m "
//                + "JOIN materia_has_docente md ON m.idmateria = md.materia_idmateria "
//                + "JOIN Docente d ON md.docente_iddocente = d.iddocente "
//                + "JOIN MateriaHaasAlumno ma ON m.idmateria = ma.materia_idmateria "
//                + "JOIN Alumno a ON ma.alumno_idalumno = a.idalumno "
//                + "WHERE d.iddocente = :iddocente", Alumno.class)
//                .setParameter("iddocente", idDocente)
//                .getResultList();
//    }
    public List<Object> findAlumnosByDocente(int docenteId) {
        String sql = "SELECT m.nombre AS materia, a.nombre AS alumno, ma.nota AS nota "
                + "FROM materia m "
                + "JOIN materia_has_docente md ON m.idmateria = md.materia_idmateria "
                + "JOIN docente d ON md.docente_iddocente = d.iddocente "
                + "JOIN materia_has_alumno ma ON m.idmateria = ma.materia_idmateria "
                + "JOIN alumno a ON ma.alumno_idalumno = a.idalumno "
                + "WHERE d.iddocente = " + docenteId;

        Query query = em.createNativeQuery(sql);
        return query.getResultList();
    }

}
