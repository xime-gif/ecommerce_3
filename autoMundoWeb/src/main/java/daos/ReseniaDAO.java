package daos;

import jakarta.persistence.EntityManager;
import java.util.List;
import modelos.Resenia;
import modelos.Vehiculo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Resenia.
 */
public class ReseniaDAO extends BaseDAO<Resenia, Long> {

    public ReseniaDAO() {
        super(Resenia.class);
    }

    /**
     * Obtiene todas las reseñas asociadas a un vehículo específico.
     *
     * @param vehiculo El vehículo del cual se quieren obtener las reseñas.
     * @return Una lista de reseñas del vehículo.
     */
    public List<Resenia> buscarPorVehiculo(Vehiculo vehiculo) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM Resenia r WHERE r.vehiculo = :vehiculo",
                    Resenia.class
            )
            .setParameter("vehiculo", vehiculo)
            .getResultList();

        } finally {
            em.close();
        }
    }
}
    