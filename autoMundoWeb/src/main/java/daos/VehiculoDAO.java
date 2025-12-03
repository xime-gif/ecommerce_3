package daos;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelos.Vehiculo;
import java.util.List;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Vehiculo.
 */
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
        return em.createQuery("SELECT v FROM Vehiculo v WHERE v.marca.nombre = :marca", Vehiculo.class)
                .setParameter("marca", marca)
                .getResultList();
    }

    public List<Vehiculo> buscarPorCategoria(Long idCategoria) {
        return em.createQuery("SELECT v FROM Vehiculo v WHERE v.categoria.id = :idCategoria", Vehiculo.class)
                .setParameter("idCategoria", idCategoria)
                .getResultList();
    }

    public List<Vehiculo> buscarPorModelo(String modelo) {
        return em.createQuery("SELECT v FROM Vehiculo v WHERE v.modelo.nombre = :modelo", Vehiculo.class)
                .setParameter("modelo", modelo)
                .getResultList();
    }

    public List<Vehiculo> buscarPorRangoPrecio(double min, double max) {
        return em.createQuery("SELECT v FROM Vehiculo v WHERE v.precio BETWEEN :min AND :max", Vehiculo.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    /**
     * Inserta un vehículo en la base de datos.
     *
     * @param vehiculo Vehículo a guardar.
     * @return El vehículo guardado con ID autogenerado.
     */
    public Vehiculo insertar(Vehiculo vehiculo) {
        em.getTransaction().begin();
        em.persist(vehiculo);
        em.getTransaction().commit();
        return vehiculo;
    }

    /**
     * Elimina un vehículo por ID.
     *
     * @param id ID del vehículo a eliminar.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminar(Long id) {
        Vehiculo v = em.find(Vehiculo.class, id);
        if (v == null) {
            return false;
        }
        em.getTransaction().begin();
        em.remove(v);
        em.getTransaction().commit();
        return true;
    }

    public Vehiculo buscarPorIdConImagenes(Long id) {
        try {
            // "LEFT JOIN FETCH v.imagenes" obliga a traer las fotos de inmediato
            TypedQuery<Vehiculo> query = em.createQuery(
                    "SELECT v FROM Vehiculo v LEFT JOIN FETCH v.imagenes WHERE v.id = :id",
                    Vehiculo.class
            );
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
