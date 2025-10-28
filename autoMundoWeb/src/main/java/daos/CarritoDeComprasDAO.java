package daos;

import modelos.CarritoDeCompras;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad
 * CarritoDeCompras.
 */
public class CarritoDeComprasDAO extends BaseDAO<CarritoDeCompras, Long> {

    public CarritoDeComprasDAO() {
        super(CarritoDeCompras.class);
    }
}
