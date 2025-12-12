package bo;

import daos.ReseniaDAO;
import daos.ImagenVehiculoDAO;
import modelos.ImagenVehiculo;
import modelos.Resenia;
import java.util.List;

public class ReseniaBO {

    private final ReseniaDAO reseniaDAO;
    private final ImagenVehiculoDAO imagenVehiculoDAO;

    public ReseniaBO() {
        this.reseniaDAO = new ReseniaDAO();
        this.imagenVehiculoDAO = new ImagenVehiculoDAO();
    }

    public List<Resenia> obtenerTodasLasResenias() {
        return reseniaDAO.buscarTodas();
    }
    
    public String obtenerRutaImagenPrincipal(Long idVehiculo) {
        ImagenVehiculo img = imagenVehiculoDAO.buscarPrincipal(idVehiculo);
        if (img == null) {
            return "imagenes/default.jpg";
        }
        return img.getUrl();
    }
}
