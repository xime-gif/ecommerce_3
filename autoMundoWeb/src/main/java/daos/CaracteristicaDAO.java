/* Archivo: src/main/java/daos/CaracteristicaDAO.java */
package daos;

import modelos.Caracteristica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * DAO para gestionar las operaciones de la entidad Caracteristica.
 */
public class CaracteristicaDAO extends BaseDAO<Caracteristica, Long> {

    public CaracteristicaDAO() {
        super(Caracteristica.class);
    }

    public Caracteristica buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Caracteristica> query = em.createQuery(
                "SELECT c FROM Caracteristica c WHERE c.nombre = :nombre",
                Caracteristica.class
            );
            query.setParameter("nombre", nombre);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Caracteristica> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Caracteristica> query = em.createQuery(
                "SELECT c FROM Caracteristica c",
                Caracteristica.class
            );
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            em.close();
        }
    }
}
