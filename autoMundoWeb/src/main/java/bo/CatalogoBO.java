/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.ImagenVehiculoDAO;
import daos.VehiculoDAO;
import java.util.List;
import modelos.Vehiculo;

/**
 *
 * @author Ximena
 */
public class CatalogoBO {
    
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final ImagenVehiculoDAO imagenDAO = new ImagenVehiculoDAO();
    
    public List<Vehiculo> obtenerCatalogo() {
        List<Vehiculo> lista = vehiculoDAO.buscarTodos();
        lista.forEach(v -> v.setImagenes(imagenDAO.buscarPorVehiculo(v.getId())));
        return lista;
    }
    
    public Vehiculo obtenerVehiculo(Long id) {
        Vehiculo v = vehiculoDAO.buscarPorId(id);
        
        if (v != null) {
            v.setImagenes(imagenDAO.buscarPorVehiculo(id));
        }
        
        return v;
    }
    
    public List<Vehiculo> filtrarPorCategoria(Long idCategoria) {
        return vehiculoDAO.buscarPorCategoria(idCategoria);
    }
    
    public List<Vehiculo> filtrarPorMarca(String marca) {
        return vehiculoDAO.buscarPorMarca(marca);
    }
    
}
