package daos;

import java.util.List;
import jakarta.persistence.EntityManager;
import modelos.Modelo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Modelo.
 */
public class ModeloDAO extends BaseDAO<Modelo, Long> {

    public ModeloDAO() {
        super(Modelo.class);
    }
    
    /**
     * Devuelve todos los modelos pertenecientes a una marca específica.
     */
    public List<Modelo> buscarPorMarca(Long idMarca) {

        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.createQuery(
                    "SELECT m FROM Modelo m WHERE m.marca.id = :idMarca",
                    Modelo.class
            )
            .setParameter("idMarca", idMarca)
            .getResultList();

        } finally {
            em.close();
        }
    }
}
