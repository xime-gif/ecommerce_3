package daos;

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
}
