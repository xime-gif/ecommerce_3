package pruebas;

import daos.JPAUtil;
import daos.ReseniaDAO;
import daos.UsuarioDAO;
import daos.VehiculoDAO;
import modelos.Usuario;
import modelos.Vehiculo;

/**
 * Clase para probar la inserción de la entidad Resenia.
 * **Esta versión asume que el Usuario y el Vehiculo ya existen en la base de datos.**
 */
public class CrearReseñas {

    private static final ReseniaDAO reseniaDAO = new ReseniaDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    
    // 🛑 ATENCIÓN: DEBES REEMPLAZAR ESTE VALOR CON UN ID DE USUARIO VÁLIDO EN TU BD.
    private static final Long ID_USUARIO_EXISTENTE = 1L; 
    
    // ID del Vehículo que se intentará reseñar.
    // **ATENCIÓN**: Reemplaza este valor con un ID de Vehiculo válido en tu BD.
    private static final Long ID_VEHICULO_EXISTENTE = 1L; 

    public static void main(String[] args) {
        
        System.out.println("--- INICIANDO PRUEBA DE CREACIÓN DE RESEÑAS ---");
        
        try {
            // 1. Buscar las entidades existentes
            Usuario usuarioPrueba = usuarioDAO.buscarPorId(ID_USUARIO_EXISTENTE);
            Vehiculo vehiculoAReseniar = vehiculoDAO.buscarPorId(ID_VEHICULO_EXISTENTE);
            
            if (usuarioPrueba == null) {
                System.out.println("❌ Error: No se encontró el Usuario con ID: " + ID_USUARIO_EXISTENTE);
                System.out.println("Asegúrate de que el ID_USUARIO_EXISTENTE sea correcto.");
                return;
            }
            if (vehiculoAReseniar == null) {
                System.out.println("❌ Error: No se encontró el Vehículo con ID: " + ID_VEHICULO_EXISTENTE);
                System.out.println("Asegúrate de que el ID_VEHICULO_EXISTENTE sea correcto.");
                return;
            }
            
            System.out.println("\n✅ Entidades base listas para la prueba:");
            System.out.println("Usuario: " + usuarioPrueba.getNombre() + " (ID: " + usuarioPrueba.getId() + ")");
            System.out.println("Vehículo: " + vehiculoAReseniar.getNombre() + " (ID: " + vehiculoAReseniar.getId() + ")");
            
            // 2. Probar el método agregarResenia con datos válidos
            System.out.println("\n--- Prueba 1: Reseña con Calificación 5 (Experiencia Excelente) ---");
            probarAgregarResenia(
                5, 
                "El coche fue excelente, sin problemas y muy limpio. ¡Recomendado!", 
                usuarioPrueba.getId(), 
                vehiculoAReseniar.getId()
            );

            // 3. Probar el método agregarResenia con otra reseña
            System.out.println("\n--- Prueba 2: Reseña con Calificación 3 (Experiencia Regular) ---");
            probarAgregarResenia(
                3, 
                "El vehículo estaba bien, pero el aire acondicionado no enfriaba lo suficiente. Necesita mantenimiento.", 
                usuarioPrueba.getId(), 
                vehiculoAReseniar.getId()
            );
            
            // --- INICIO DE NUEVAS PRUEBAS ---
            
            // 4. Nueva reseña con calificación 4
            System.out.println("\n--- Prueba 3: Reseña con Calificación 4 (Muy buena, con detalle menor) ---");
            probarAgregarResenia(
                4, 
                "Muy buen rendimiento de gasolina. El único detalle es que el GPS tardó en arrancar.", 
                usuarioPrueba.getId(), 
                vehiculoAReseniar.getId()
            );

            // 5. Nueva reseña con calificación 5 (Máxima)
            System.out.println("\n--- Prueba 4: Reseña con Calificación 5 (Confirmación de calidad) ---");
            probarAgregarResenia(
                5, 
                "Viaje impecable. Absolutamente recomendable para trayectos largos. Un 10/10.", 
                usuarioPrueba.getId(), 
                vehiculoAReseniar.getId()
            );

            // 6. Nueva reseña con calificación 2
            System.out.println("\n--- Prueba 5: Reseña con Calificación 2 (Experiencia Negativa) ---");
            probarAgregarResenia(
                2, 
                "Lamentablemente, la entrega se retrasó y el vehículo tenía un rayón que no estaba documentado.", 
                usuarioPrueba.getId(), 
                vehiculoAReseniar.getId()
            );
            
            // --- FIN DE NUEVAS PRUEBAS ---
            
            // 7. Probar un caso de error (Vehículo inexistente)
            System.out.println("\n--- Prueba 6: Intentando reseñar un Vehículo inexistente (ID 99999) ---");
            probarAgregarResenia(
                4, 
                "Esto debe fallar.", 
                usuarioPrueba.getId(), 
                99999L // ID inexistente
            );


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Hubo un error inesperado en la prueba.");
        } finally {
            JPAUtil.close(); 
            System.out.println("\n--- PRUEBA DE CREACIÓN DE RESEÑAS FINALIZADA ---");
        }
    }
    
    /**
     * Llama al método del DAO y muestra el resultado.
     */
    private static void probarAgregarResenia(int calificacion, String comentario, Long usuarioId, Long vehiculoId) {
        System.out.println("\n-> Intentando agregar reseña (Calificación: " + calificacion + ")");
        
        boolean exito = reseniaDAO.agregarResenia(
            calificacion, 
            comentario, 
            usuarioId, 
            vehiculoId
        );

        if (exito) {
            System.out.println("    ✅ Éxito: Reseña guardada.");
        } else {
            System.out.println("    ❌ Falla: No se pudo guardar la reseña. (Verifique los logs)");
        }
    }
}