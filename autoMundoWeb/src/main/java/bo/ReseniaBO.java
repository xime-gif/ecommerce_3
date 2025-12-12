package bo;

import daos.ReseniaDAO;
import daos.ImagenVehiculoDAO; // Necesario para buscar la imagen
import modelos.ImagenVehiculo;
import modelos.Resenia;
import java.util.List;

/**
 * Capa de Lógica de Negocio (Business Object) para las Reseñas.
 */
public class ReseniaBO {

    private final ReseniaDAO reseniaDAO;
    private final ImagenVehiculoDAO imagenVehiculoDAO;

    public ReseniaBO() {
        this.reseniaDAO = new ReseniaDAO();
        this.imagenVehiculoDAO = new ImagenVehiculoDAO();
    }

    /**
     * Obtiene la lista de todas las reseñas registradas en el sistema.
     *
     * @return Una lista de objetos Resenia.
     */
    public List<Resenia> obtenerTodasLasResenias() {
        // Aquí podrías agregar lógica de negocio (ej. filtrar, ordenar)
        return reseniaDAO.buscarTodas();
    }
    
    /**
     * Obtiene la ruta de la imagen principal para un vehículo dado su ID.
     *
     * @param idVehiculo El ID del vehículo.
     * @return La ruta de la imagen principal, o una ruta por defecto si no existe.
     */
    public String obtenerRutaImagenPrincipal(Long idVehiculo) {
        ImagenVehiculo img = imagenVehiculoDAO.buscarPrincipal(idVehiculo);
        
        // Si no se encuentra una imagen principal, devuelve una ruta por defecto
        if (img == null) {
            return "imagenes/default.jpg";
        }
        return img.getUrl();
    }
    
}