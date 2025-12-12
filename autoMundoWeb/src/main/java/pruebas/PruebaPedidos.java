package pruebas;

import bo.PedidoBO;
import daos.JPAUtil;
import daos.UsuarioDAO; // Necesario para obtener un Usuario existente
import modelos.Pedido;
import modelos.Usuario;
import java.util.List;
import modelos.DetallePedido;

/**
 * Clase para probar la obtención de pedidos y la carga de datos relacionados 
 * (Detalles, Vehículos e Imágenes) a través de la capa BO.
 */
public class PruebaPedidos {

    private static final PedidoBO pedidoBO = new PedidoBO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    // 🛑 ¡IMPORTANTE! Reemplaza este valor con el ID de un usuario que sepas que tiene pedidos en tu base de datos.
    private static final Long ID_USUARIO_CON_PEDIDOS = 1L; 

    public static void main(String[] args) {
        
        System.out.println("--- INICIANDO PRUEBA DE OBTENCIÓN DE PEDIDOS Y DATOS RELACIONADOS ---");
        
        try {
            // 1. Obtener el usuario de prueba de la base de datos
            Usuario usuarioPrueba = usuarioDAO.buscarPorId(ID_USUARIO_CON_PEDIDOS);
            
            if (usuarioPrueba == null) {
                System.err.println("❌ ERROR: No se encontró el Usuario con ID: " + ID_USUARIO_CON_PEDIDOS);
                System.err.println("Por favor, verifica el ID_USUARIO_CON_PEDIDOS en la base de datos.");
                return;
            }
            
            System.out.println("✅ Usuario encontrado: " + usuarioPrueba.getNombre() + " (ID: " + usuarioPrueba.getId() + ")");

            // 2. Probar el método obtenerPedidosPorUsuario del BO
            List<Pedido> pedidos = pedidoBO.obtenerPedidosPorUsuario(usuarioPrueba);

            // 3. Imprimir el resultado
            System.out.println("\n--- RESULTADO DEL BO ---");
            if (pedidos.isEmpty()) {
                System.out.println("❌ El BO devolvió 0 pedidos para este usuario. Revisa el PedidoDAO.");
            } else {
                System.out.println("✅ Éxito! Total de pedidos encontrados: " + pedidos.size());

                // 4. PRUEBA CRÍTICA: Acceder a los detalles y la imagen (Simulando el JSP)
                Pedido primerPedido = pedidos.get(0);
                
                System.out.println("\n--- VERIFICACIÓN DEL PRIMER PEDIDO (#" + primerPedido.getId() + ") ---");
                
                if (primerPedido.getDetalles().isEmpty()) {
                    System.err.println("❌ FALLA EN LA CARGA: El pedido no tiene detalles cargados (Lista de detalles vacía).");
                } else {
                    System.out.println("✅ Detalles cargados: " + primerPedido.getDetalles().size());
                    
                    DetallePedido primerDetalle = primerPedido.getDetalles().get(0);
                    
                    if (primerDetalle.getVehiculo() == null) {
                        System.err.println("❌ FALLA EN LA CARGA: El Vehiculo es nulo en el detalle. Revisa DetallePedidoDAO.");
                    } else {
                        System.out.println("✅ Vehículo del detalle: " + primerDetalle.getVehiculo().getNombre());
                        
                        // 5. PRUEBA DE IMAGEN: Accediendo a la colección LAZY cargada por el BO/DAO
                        if (primerDetalle.getVehiculo().getImagenes().isEmpty()) {
                            System.err.println("❌ FALLA EN LA IMAGEN: El vehículo no tiene imágenes cargadas (Lista vacía). Revisa el mapeo o si el vehículo tiene imágenes.");
                        } else {
                            // Si esta línea se ejecuta sin LazyInitializationException, el proceso es correcto.
                            String rutaImagen = primerDetalle.getVehiculo().getImagenes().get(0).getUrl();
                            System.out.println("✅ Imagen principal recuperada: " + rutaImagen);
                        }
                    }
                }
            }

        } catch (Exception e) {
            // Si ves una LazyInitializationException (LIE) aquí, es porque la carga falló ANTES de que el DAO/BO cerrara la sesión.
            System.err.println("\n❌ ERROR CRÍTICO: Se produjo una excepción. Podría ser LIE.");
            e.printStackTrace();
        } finally {
            JPAUtil.close();
            System.out.println("\n--- PRUEBA FINALIZADA ---");
        }
    }
}