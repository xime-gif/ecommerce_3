/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import bo.AltasBajasBo;
import daos.VehiculoDAO;
import java.util.List;
import modelos.ImagenVehiculo;
import modelos.Vehiculo;

/**
 *
 * @author javie
 */
public class PruebaImprimirCarros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AltasBajasBo vehiculoBO = new AltasBajasBo();
        vehiculoBO.obtenerAutos();

        List<Vehiculo> vehiculos = vehiculoBO.obtenerAutos();

        System.out.println("Vehículos encontrados: " + vehiculos.size());
        System.out.println("-----------------------------------------");

        for (Vehiculo v : vehiculos) {

            System.out.println("ID: " + v.getId());
            System.out.println("Nombre: " + v.getNombre());
            System.out.println("Precio: " + v.getPrecio());

            List<ImagenVehiculo> imagenes = v.getImagenes();

            if (imagenes == null || imagenes.isEmpty()) {
                System.out.println(" → Este vehículo NO tiene imágenes.");
            } else {
                System.out.println(" → Imágenes:");
                for (ImagenVehiculo img : imagenes) {
                    System.out.println("    URL: " + img.getUrl()
                            + " | Principal: " + img.getEsPrincipal());
                }
            }

            System.out.println("-----------------------------------------\n");
        }
    }
    
}
