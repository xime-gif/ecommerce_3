package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import modelos.Vehiculo;

public class VehiculoDAO extends BaseDAO<Vehiculo, Long> {

    public VehiculoDAO() {
        super(Vehiculo.class);
    }

    /**
     * Busca vehículos filtrando por el nombre de su marca.
     *
     * @param marca El nombre de la marca a buscar.
     * @return Una lista de vehículos que pertenecen a la marca especificada.
     */
    public List<Vehiculo> buscarPorMarca(String marca) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.modelo.marca.nombre = :marca",
                    Vehiculo.class
            )
                    .setParameter("marca", marca)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Vehiculo> buscarPorCategoria(Long idCategoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.categoria.id = :idCategoria",
                    Vehiculo.class
            )
                    .setParameter("idCategoria", idCategoria)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Vehiculo> buscarPorModelo(String modelo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.modelo.nombre = :modelo",
                    Vehiculo.class
            )
                    .setParameter("modelo", modelo)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Vehiculo> buscarPorRangoPrecio(double min, double max) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.precio BETWEEN :min AND :max",
                    Vehiculo.class
            )
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Vehiculo buscarPorIdConImagenes(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Vehiculo> query = em.createQuery(
                    "SELECT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.id = :id",
                    Vehiculo.class
            );

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Inserta un vehículo en la base de datos.
     *
     * @param vehiculo Vehículo a guardar.
     * @return El vehículo guardado con ID autogenerado.
     */
    public Vehiculo insertar(Vehiculo vehiculo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(vehiculo);
            em.getTransaction().commit();
            return vehiculo;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al insertar vehículo", e);
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un vehículo por ID.
     *
     * @param id ID del vehículo a eliminar.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Vehiculo vehiculo = em.find(Vehiculo.class, id);
            if (vehiculo == null) {
                return false;
            }

            em.getTransaction().begin();
            em.remove(vehiculo);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar vehículo", e);
        } finally {
            em.close();
        }
    }

    public List<Vehiculo> buscarTodosConImagenes() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Vehiculo> vehiculos = em.createQuery(
                    "SELECT DISTINCT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes",
                    Vehiculo.class
            ).getResultList();

            for (Vehiculo v : vehiculos) {
                v.getCaracteristicas().size();
            }

            return vehiculos;
        } finally {
            em.close();
        }
    }

    public Vehiculo actualizar(Vehiculo vehiculo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Vehiculo actualizado = em.merge(vehiculo);
            em.getTransaction().commit();
            return actualizado;
        } finally {
            em.close();
        }
    }

}
