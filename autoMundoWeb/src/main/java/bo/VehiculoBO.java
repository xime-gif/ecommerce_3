/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.VehiculoDAO;
import modelos.Vehiculo;

/**
 *
 * @author Ximena
 */
public class VehiculoBO {
    
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    
    public void guardar(Vehiculo v) {
        if (v == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }
        
        if (v.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo ni 0.");
        }
        
        if (v.getExistencias() < 0) {
            throw new IllegalArgumentException("Las existencias no pueden ser negativas.");
        }
        
        if (v.getModelo() == null || v.getModelo().getId() == null) {
            throw new IllegalArgumentException("El modelo es obligatorio.");
        }

        if (v.getCategoria() == null || v.getCategoria().getId() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria.");
        }
        
        if (v.getId() == null) {
            vehiculoDAO.crear(v);
        } else {
            vehiculoDAO.actualizar(v);
        }
    }
    
    public void eliminar(Long id) {
        Vehiculo v = vehiculoDAO.buscarPorId(id);
        if (v != null) {
            vehiculoDAO.eliminar(v);
        }
    }
    
}
