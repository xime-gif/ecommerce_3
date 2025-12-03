/* Archivo: src/main/java/bo/AltasBajasBo.java */
package bo;

import daos.CaracteristicaDAO;
import daos.VehiculoDAO;
import modelos.Caracteristica;
import modelos.ImagenVehiculo;
import modelos.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class AltasBajasBo {

    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final CaracteristicaDAO caracteristicaDAO = new CaracteristicaDAO();

    public Vehiculo altaVehiculo(Vehiculo vehiculo, String urlImagen) {
        
        List<Caracteristica> caracteristicasOriginales = vehiculo.getCaracteristicas();

        if (caracteristicasOriginales != null && !caracteristicasOriginales.isEmpty()) {
            List<Caracteristica> listaProcesada = new ArrayList<>();

            for (Caracteristica c : caracteristicasOriginales) {
                
                Caracteristica existente = caracteristicaDAO.buscarPorNombre(c.getNombre());

                if (existente != null) {

                    listaProcesada.add(existente);
                } else {
                    
                    caracteristicaDAO.crear(c);
                    listaProcesada.add(c);
                }
            }
    
            vehiculo.setCaracteristicas(listaProcesada);
        }
        
        if (urlImagen != null && !urlImagen.isBlank()) {
            ImagenVehiculo img = new ImagenVehiculo();
            img.setUrl(urlImagen);
            img.setEsPrincipal(true);
            img.setVehiculo(vehiculo); 
    
            if (vehiculo.getImagenes() == null) {
                vehiculo.setImagenes(new ArrayList<>());
            }
            
            vehiculo.getImagenes().add(img);
        }
        
        return vehiculoDAO.insertar(vehiculo);
    }


    public List<Vehiculo> obtenerAutos() {
        return vehiculoDAO.buscarTodos();
    }

    
    public void bajaVehiculo(Long id) {
        Vehiculo v = vehiculoDAO.buscarPorId(id);
        if (v != null) {
            vehiculoDAO.eliminar(v);
        }
    }
}