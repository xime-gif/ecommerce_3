package daos;

import modelos.ImagenVehiculo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad
 * ImagenVehiculo.
 */
public class ImagenVehiculoDAO extends BaseDAO<ImagenVehiculo, Long> {

    public ImagenVehiculoDAO() {
        super(ImagenVehiculo.class);
    }
}
