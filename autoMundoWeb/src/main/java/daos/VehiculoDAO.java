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
}
