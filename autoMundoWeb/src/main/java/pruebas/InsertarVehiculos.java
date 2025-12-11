/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import daos.CaracteristicaDAO;
import daos.CategoriaDAO;
import daos.JPAUtil;
import daos.MarcaDAO;
import daos.ModeloDAO;
import daos.VehiculoDAO;
import java.util.List;
import modelos.Caracteristica;
import modelos.Categoria;
import modelos.Marca;
import modelos.Modelo;
import modelos.Vehiculo;

/**
 *
 * @author Jp
 */
public class InsertarVehiculos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1. Instanciar los DAOs
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        MarcaDAO marcaDAO = new MarcaDAO();
        ModeloDAO modeloDAO = new ModeloDAO();
        CaracteristicaDAO caracteristicaDAO = new CaracteristicaDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        try {
            System.out.println("--- Iniciando Carga de Datos ---");

            // 2. Crear Categorías
            Categoria suv = crearCategoria(categoriaDAO, "SUV");
            Categoria sedan = crearCategoria(categoriaDAO, "Sedán");
            Categoria hatchback = crearCategoria(categoriaDAO, "Hatchback");
            Categoria pickup = crearCategoria(categoriaDAO, "Pickup");
            Categoria deportivo = crearCategoria(categoriaDAO, "Deportivo");

            // 3. Crear Características
            Caracteristica aire = crearCaracteristica(caracteristicaDAO, "Aire Acondicionado", "icon_aire.png");
            Caracteristica gps = crearCaracteristica(caracteristicaDAO, "GPS", "icon_gps.png");
            Caracteristica piel = crearCaracteristica(caracteristicaDAO, "Asientos de Piel", "icon_piel.png");
            Caracteristica bluetooth = crearCaracteristica(caracteristicaDAO, "Bluetooth", "icon_bt.png");

            // 4. Crear Marcas y Modelos
            // Toyota
            Marca toyota = crearMarca(marcaDAO, "Toyota");
            Modelo corolla = crearModelo(modeloDAO, "Corolla", toyota);
            Modelo rav4 = crearModelo(modeloDAO, "RAV4", toyota);
            Modelo tacoma = crearModelo(modeloDAO, "Tacoma", toyota);

            // Ford
            Marca ford = crearMarca(marcaDAO, "Ford");
            Modelo mustang = crearModelo(modeloDAO, "Mustang", ford);
            Modelo f150 = crearModelo(modeloDAO, "F-150", ford);
            Modelo explorer = crearModelo(modeloDAO, "Explorer", ford);

            // Chevrolet
            Marca chevrolet = crearMarca(marcaDAO, "Chevrolet");
            Modelo aveo = crearModelo(modeloDAO, "Aveo", chevrolet);
            Modelo camaro = crearModelo(modeloDAO, "Camaro", chevrolet);
            Modelo silverado = crearModelo(modeloDAO, "Silverado", chevrolet);

            // Honda
            Marca honda = crearMarca(marcaDAO, "Honda");
            Modelo civic = crearModelo(modeloDAO, "Civic", honda);
            Modelo crv = crearModelo(modeloDAO, "CR-V", honda);

            // Nissan
            Marca nissan = crearMarca(marcaDAO, "Nissan");
            Modelo sentra = crearModelo(modeloDAO, "Sentra", nissan);
            Modelo versa = crearModelo(modeloDAO, "Versa", nissan);

            // Volkswagen
            Marca vw = crearMarca(marcaDAO, "Volkswagen");
            Modelo jetta = crearModelo(modeloDAO, "Jetta", vw);

            // Mazda
            Marca mazda = crearMarca(marcaDAO, "Mazda");
            Modelo mazda3 = crearModelo(modeloDAO, "Mazda 3", mazda);

            // 5. Insertar vehículos
            System.out.println("--- Insertando Vehículos ---");

            // --- Toyota ---
            crearVehiculo(vehiculoDAO, "Toyota Corolla LE 2024", 410000.0, 10, sedan, corolla, List.of(aire, bluetooth));
            crearVehiculo(vehiculoDAO, "Toyota Corolla XSE 2024", 490000.0, 5, sedan, corolla, List.of(aire, gps, piel));
            crearVehiculo(vehiculoDAO, "Toyota RAV4 Hybrid", 750000.0, 8, suv, rav4, List.of(aire, gps, bluetooth, piel));
            crearVehiculo(vehiculoDAO, "Toyota Tacoma Sport", 850000.0, 3, pickup, tacoma, List.of(aire, gps));

            // --- Ford ---
            crearVehiculo(vehiculoDAO, "Ford Mustang GT", 1100000.0, 2, deportivo, mustang, List.of(piel, gps, bluetooth, aire));
            crearVehiculo(vehiculoDAO, "Ford Mustang EcoBoost", 950000.0, 4, deportivo, mustang, List.of(bluetooth, aire));
            crearVehiculo(vehiculoDAO, "Ford F-150 Lariat", 1200000.0, 5, pickup, f150, List.of(aire, gps, piel));
            crearVehiculo(vehiculoDAO, "Ford Explorer XLT", 980000.0, 6, suv, explorer, List.of(aire, bluetooth, gps));

            // --- Chevrolet ---
            crearVehiculo(vehiculoDAO, "Chevrolet Aveo LS", 285000.0, 15, sedan, aveo, List.of(aire));
            crearVehiculo(vehiculoDAO, "Chevrolet Aveo LT", 310000.0, 12, sedan, aveo, List.of(aire, bluetooth));
            crearVehiculo(vehiculoDAO, "Chevrolet Camaro SS", 1050000.0, 2, deportivo, camaro, List.of(piel, gps, aire));
            crearVehiculo(vehiculoDAO, "Chevrolet Silverado 1500", 980000.0, 4, pickup, silverado, List.of(aire, gps));

            // --- Honda ---
            crearVehiculo(vehiculoDAO, "Honda Civic Turbo", 550000.0, 7, sedan, civic, List.of(aire, gps, bluetooth));
            crearVehiculo(vehiculoDAO, "Honda Civic Touring", 620000.0, 4, sedan, civic, List.of(piel, gps, aire, bluetooth));
            crearVehiculo(vehiculoDAO, "Honda CR-V Turbo", 780000.0, 6, suv, crv, List.of(aire, gps));

            // --- Nissan ---
            crearVehiculo(vehiculoDAO, "Nissan Sentra Advance", 430000.0, 10, sedan, sentra, List.of(aire, bluetooth));
            crearVehiculo(vehiculoDAO, "Nissan Sentra SR", 480000.0, 5, sedan, sentra, List.of(aire, gps, piel));
            crearVehiculo(vehiculoDAO, "Nissan Versa Drive", 320000.0, 20, sedan, versa, List.of(aire));

            // --- Otros ---
            crearVehiculo(vehiculoDAO, "Volkswagen Jetta Sportline", 510000.0, 8, sedan, jetta, List.of(aire, gps, bluetooth));
            crearVehiculo(vehiculoDAO, "Mazda 3 Hatchback Grand Touring", 530000.0, 6, hatchback, mazda3, List.of(piel, aire, bluetooth, gps));

            System.out.println("--- Carga Exitosa: 20 Vehículos insertados ---");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Hubo un error en la carga de datos.");
        } finally {
            JPAUtil.close(); // Cerrar la fábrica de EntityManager al final
        }
    }

    // --- Métodos Auxiliares para simplificar el código ---
    private static Categoria crearCategoria(CategoriaDAO dao, String nombre) {
        Categoria c = new Categoria();
        c.setNombre(nombre);
        return dao.crear(c);
    }

    private static Marca crearMarca(MarcaDAO dao, String nombre) {
        Marca m = new Marca();
        m.setNombre(nombre);
        return dao.crear(m);
    }

    private static Modelo crearModelo(ModeloDAO dao, String nombre, Marca marca) {
        Modelo m = new Modelo();
        m.setNombre(nombre);
        m.setMarca(marca);
        return dao.crear(m);
    }

    private static Caracteristica crearCaracteristica(CaracteristicaDAO dao, String nombre, String icono) {
        Caracteristica c = new Caracteristica();
        c.setNombre(nombre);
        c.setRutaIcono(icono);
        return dao.crear(c);
    }

    private static Vehiculo crearVehiculo(VehiculoDAO dao, String nombre, Double precio, Integer stock,
            Categoria cat, Modelo mod, List<Caracteristica> caracteristicas) {
        Vehiculo v = new Vehiculo();
        v.setNombre(nombre);
        v.setPrecio(precio);
        v.setExistencias(stock);
        v.setCategoria(cat);
        v.setModelo(mod);

        // Agregar características
        if (caracteristicas != null) {
            for (Caracteristica c : caracteristicas) {
                v.agregarCaracteristica(c);
            }
        }

        dao.insertar(v);
        System.out.println("Insertado: " + v.getNombre());
        return v;
    }

}
