package daos;

import java.util.List;
import jakarta.persistence.EntityManager;
import modelos.ImagenVehiculo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad
 * ImagenVehiculo.
 */
public class ImagenVehiculoDAO extends BaseDAO<ImagenVehiculo, Long> {

    public ImagenVehiculoDAO() {
        super(ImagenVehiculo.class);
    }

    public List<ImagenVehiculo> buscarPorVehiculo(Long idVehiculo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT i FROM ImagenVehiculo i WHERE i.vehiculo.id = :idVehiculo",
                    ImagenVehiculo.class
            )
            .setParameter("idVehiculo", idVehiculo)
            .getResultList();

        } finally {
            em.close();
        }
    }

    public ImagenVehiculo buscarPrincipal(Long idVehiculo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<ImagenVehiculo> lista = em.createQuery(
                    "SELECT i FROM ImagenVehiculo i WHERE i.vehiculo.id = :idVehiculo AND i.esPrincipal = true",
                    ImagenVehiculo.class
            )
            .setParameter("idVehiculo", idVehiculo)
            .getResultList();

            return lista.isEmpty() ? null : lista.get(0);

        } finally {
            em.close();
        }
    }

    /**
     * Insertar una imagen asociada a un vehículo.
     */
    public ImagenVehiculo insertar(ImagenVehiculo img) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(img);
            em.getTransaction().commit();
            return img;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
        }
    }

    /**
     * Eliminar una imagen por ID.
     */
    public boolean eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ImagenVehiculo img = em.find(ImagenVehiculo.class, id);
            if (img == null) return false;

            em.getTransaction().begin();
            em.remove(img);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
        }
    }

    /**
     * Eliminar todas las imágenes relacionadas a un vehículo.
     */
    public void eliminarPorVehiculo(Long idVehiculo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            em.createQuery("DELETE FROM ImagenVehiculo i WHERE i.vehiculo.id = :idVehiculo")
                    .setParameter("idVehiculo", idVehiculo)
                    .executeUpdate();

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
        }
    }
}
