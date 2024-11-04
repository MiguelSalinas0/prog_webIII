/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.EstadisticasPromociones;
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
public class EstadisticasPromocionesFacade extends AbstractFacade<EstadisticasPromociones> {

    @PersistenceContext(unitName = "com.mycompany_promoSJ_dash_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadisticasPromocionesFacade() {
        super(EstadisticasPromociones.class);
    }

    // Total de canjes y visitas por promoción del comercio   
    public List<Object[]> obtenerTotalDeCanjesPorPromocion(Integer idComercio) {
        String sql = "SELECT p.id_promocion, p.titulo, ep.cantidad_canjes, ep.visitas "
                + "FROM estadisticas_promociones ep "
                + "JOIN promociones p ON p.id_promocion = ep.id_promocion "
                + "WHERE p.id_comercio = ?";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idComercio);

        return query.getResultList();
    }

    // Canjes por estado de promociones del comercio
    public List<Object[]> obtenerCanjesPorEstado(Integer idComercio) {
        String sql = "SELECT c.estado, COUNT(*) AS total "
                + "FROM canjes c "
                + "JOIN promociones p ON c.id_promocion = p.id_promocion "
                + "WHERE p.id_comercio = ? "
                + "GROUP BY c.estado";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idComercio);

        return query.getResultList();
    }

    // Promociones por cantidad de canjes (ranking)
    public List<Object[]> obtenerRanking(Integer idComercio) {
        String sql = "SELECT p.id_promocion, p.titulo, ep.cantidad_canjes "
                + "FROM estadisticas_promociones ep "
                + "JOIN promociones p ON p.id_promocion = ep.id_promocion "
                + "WHERE p.id_comercio = ? "
                + "ORDER BY ep.cantidad_canjes DESC";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idComercio);

        return query.getResultList();
    }

    // Promociones efectivas por fecha
    public List<Object[]> obtenerPorFecha(Integer idComercio) {
        String sql = "SELECT YEAR(c.fecha_canje) AS anio, MONTH(c.fecha_canje) AS mes, COUNT(*) AS total_canjes "
                + "FROM canjes c "
                + "JOIN promociones p ON c.id_promocion = p.id_promocion "
                + "WHERE c.estado = 'efectivo' AND p.id_comercio = ? "
                + "GROUP BY YEAR(c.fecha_canje), MONTH(c.fecha_canje) "
                + "ORDER BY anio, mes; ";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idComercio);

        return query.getResultList();
    }
}
