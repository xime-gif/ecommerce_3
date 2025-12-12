/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import daos.ImagenVehiculoDAO;
import daos.VehiculoDAO;
import java.util.List;
import modelos.ImagenVehiculo;
import modelos.Vehiculo;

/**
 *
 * @author Jp
 */
public class CargarImagenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        ImagenVehiculoDAO imagenDAO = new ImagenVehiculoDAO();

        try {
            System.out.println("--- Iniciando Carga de Imágenes ---");

            List<Vehiculo> vehiculos = vehiculoDAO.buscarTodos();

            if (vehiculos.isEmpty()) {
                System.out.println("¡No hay vehículos! Ejecuta primero InsertarVehiculos.java");
                return;
            }

            for (Vehiculo v : vehiculos) {

                String nombre = v.getNombre().toLowerCase();
                String rutaImagen;

                if (nombre.contains("aveo")) {
                    rutaImagen = "imagenes/aveo.jpg";
                } else if (nombre.contains("onix") || nombre.contains("chevrolet")) {
                    rutaImagen = "imagenes/onix.jpg";
                } else if (nombre.contains("mustang") || nombre.contains("ford")) {
                    rutaImagen = "imagenes/auto-portada.jpg";
                } else {
                    rutaImagen = "imagenes/carro.jpg";
                }

                ImagenVehiculo img = new ImagenVehiculo();
                img.setUrl(rutaImagen);
                img.setEsPrincipal(true);
                img.setVehiculo(v);

                imagenDAO.crear(img);

                System.out.println("Imagen asignada a: " + v.getNombre() + " -> " + rutaImagen);
            }

            System.out.println("--- ¡Carga de imágenes completada! ---");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
