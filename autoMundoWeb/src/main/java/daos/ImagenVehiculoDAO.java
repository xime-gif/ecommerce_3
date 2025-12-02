package daos;

import java.util.List;
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
        return em.createQuery("SELECT i FROM ImagenVehiculo i WHERE i.vehiculo.id = :idVehiculo", ImagenVehiculo.class)
                .setParameter("idVehiculo", idVehiculo)
                .getResultList();
    }
    
    public ImagenVehiculo buscarPrincipal(Long idVehiculo) {
        List<ImagenVehiculo> lista = em.createQuery(
                "SELECT i FROM ImagenVehiculo i WHERE i.vehiculo.id = :idVehiculo AND i.esPrincipal = true", ImagenVehiculo.class)
                .setParameter("idVehiculo", idVehiculo)
                .getResultList();
        
        return lista.isEmpty() ? null : lista.get(0);
    }
}
