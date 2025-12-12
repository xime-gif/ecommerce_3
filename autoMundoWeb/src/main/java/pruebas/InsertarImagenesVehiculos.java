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
 * @author javie
 */
public class InsertarImagenesVehiculos {

    public static void main(String[] args) {

        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        ImagenVehiculoDAO imagenDAO = new ImagenVehiculoDAO();

        String carpeta = "imagenes/"; 

        String[] imagenes = {
                "auto-portada.jpg",
                "aveo.jpg",
                "carro.jpg",
                "carroInicio.jpeg",
                "logo.png",
                "onix.jpg"
        };

        try {
            System.out.println("--- Insertando imágenes a los vehículos ---");

            List<Vehiculo> vehiculos = vehiculoDAO.buscarTodos();

            if (vehiculos.isEmpty()) {
                System.out.println("No existen vehículos en la BD.");
                return;
            }

            int indexImagen = 0;

            for (Vehiculo v : vehiculos) {

                String nombreImagen = imagenes[indexImagen % imagenes.length];
                String url = carpeta + nombreImagen;

                ImagenVehiculo img = new ImagenVehiculo();
                img.setUrl(url);
                img.setEsPrincipal(true);
                img.setVehiculo(v);

                imagenDAO.insertar(img);

                System.out.println("Asignada imagen: " + nombreImagen + " → a vehículo: " + v.getNombre());

                indexImagen++;
            }

            System.out.println("--- Imágenes insertadas exitosamente ---");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al insertar imágenes.");
        }
    }
}
