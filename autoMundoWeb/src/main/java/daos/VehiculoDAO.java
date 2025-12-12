package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelos.Vehiculo;

public class VehiculoDAO extends BaseDAO<Vehiculo, Long> {

    public VehiculoDAO() {
        super(Vehiculo.class);
    }

    public Vehiculo buscarPorIdConImagenes(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Vehiculo> query = em.createQuery(
                    "SELECT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.id = :id",
                    Vehiculo.class
            );
            query.setParameter("id", id);
            Vehiculo v = query.getSingleResult();

            v.getCaracteristicas().size();

            return v;

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
    
    public List<Vehiculo> filtrar(String modelo, String marca, Long categoria, Double min, Double max) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            StringBuilder jpql = new StringBuilder(
                "SELECT DISTINCT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE 1=1"
            );

            Map<String, Object> params = new HashMap<>();
            
            if (modelo != null && !modelo.isBlank()) {
                jpql.append(" AND LOWER(v.nombre) LIKE :texto");
                params.put("texto", "%" + modelo.toLowerCase() + "%");
            }

            if (marca != null && !marca.isBlank()) {
                jpql.append(" AND v.modelo.marca.nombre = :marca");
                params.put("marca", marca);
            }

            if (categoria != null) {
                jpql.append(" AND v.categoria.id = :categoria");
                params.put("categoria", categoria);
            }

            if (min != null && max != null) {
                jpql.append(" AND v.precio BETWEEN :min AND :max");
                params.put("min", min);
                params.put("max", max);
            }

            TypedQuery<Vehiculo> query = em.createQuery(jpql.toString(), Vehiculo.class);

            params.forEach(query::setParameter);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

}
