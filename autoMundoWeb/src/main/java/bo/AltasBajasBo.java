/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.ImagenVehiculoDAO;
import daos.VehiculoDAO;
import java.util.List;
import modelos.ImagenVehiculo;
import modelos.Vehiculo;

/**
 *
 * Clase Bo para admin altas y bajas
 */
public class AltasBajasBo {

    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final ImagenVehiculoDAO imagenDAO = new ImagenVehiculoDAO();

    /**
     * Registrar un nuevo vehículo
     * @param vehiculo
     * @param urlImagen
     * @return guardado
     */
    public Vehiculo altaVehiculo(Vehiculo vehiculo, String urlImagen) {
        Vehiculo guardado = vehiculoDAO.insertar(vehiculo);

        if (guardado != null && urlImagen != null && !urlImagen.isBlank()) {
            ImagenVehiculo img = new ImagenVehiculo();
            img.setId(guardado.getId());
            img.setUrl(urlImagen);
            img.setEsPrincipal(true);
            img.setVehiculo(vehiculo);

            imagenDAO.insertar(img);
        }

        return guardado;
    }

    /**
     * Eliminar un vehículo por ID
     * @param idVehiculo
     * @return vehiculoDAO
     */
    public boolean bajaVehiculo(Long idVehiculo) {
        imagenDAO.eliminarPorVehiculo(idVehiculo);
        return vehiculoDAO.eliminar(idVehiculo);
    }

    /**
     * Obtener todos los autos (para mostrarlos en la página de bajas)
     * @return lista
     */
    public List<Vehiculo> obtenerAutos() {
        return vehiculoDAO.buscarTodos();
    }

}
