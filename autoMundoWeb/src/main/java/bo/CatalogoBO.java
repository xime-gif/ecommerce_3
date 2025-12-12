/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.ImagenVehiculoDAO;
import daos.VehiculoDAO;
import java.util.ArrayList;
import java.util.List;
import modelos.ImagenVehiculo;
import modelos.Vehiculo;

/**
 *
 * @author Ximena
 */
public class CatalogoBO {

    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final ImagenVehiculoDAO imagenDAO = new ImagenVehiculoDAO();

    public Vehiculo obtenerVehiculo(Long id) {
        Vehiculo v = vehiculoDAO.buscarPorId(id);

        if (v != null) {
            List<ImagenVehiculo> imagenes = imagenDAO.buscarPorVehiculo(v.getId());
            v.setImagenes(imagenes != null ? imagenes : new ArrayList<>());
        }

        return v;
    }
    
    public List<Vehiculo> filtrarCatalogo(String texto, String marca, Long idCategoria, Double min, Double max) {

        List<Vehiculo> lista = vehiculoDAO.filtrar(texto, marca, idCategoria, min, max);

        // Agregar imágenes
        lista.forEach(v -> {
            List<ImagenVehiculo> imagenes = imagenDAO.buscarPorVehiculo(v.getId());
            v.setImagenes(imagenes != null ? imagenes : new ArrayList<>());
        });

        return lista;
    }

}
