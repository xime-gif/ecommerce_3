package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import modelos.Pedido;
import modelos.Usuario;
import modelos.DetallePedido; // Necesaria para iterar sobre los detalles
// IMPORTACIÓN CRÍTICA: Necesaria para forzar la carga de la colección LAZY.
import org.hibernate.Hibernate; 

// Nota: Asumo que BaseDAO y JPAUtil están correctamente definidos y disponibles.
public class PedidoDAO extends BaseDAO<Pedido, Long> {

    public PedidoDAO() {
        super(Pedido.class);
    }

    public List<Pedido> obtenerPedidosPorCliente(Usuario cliente) {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();

            // 1. JPQL SIMPLIFICADA: 
            // Se quita el JOIN FETCH de las imágenes para evitar el error de consulta complejo (error 500 anterior).
            String jpql = "SELECT p FROM Pedido p "
                    + "LEFT JOIN FETCH p.direccionEnvio "
                    + "LEFT JOIN FETCH p.detalles d "
                    + "LEFT JOIN FETCH d.vehiculo v "
                    // LÍNEA RETIRADA: Se evita el uso de "+ "LEFT JOIN FETCH v.imagenes img "
                    + "WHERE p.cliente.id = :clienteId "
                    + "ORDER BY p.fechaCompra DESC";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("clienteId", cliente.getId());

            List<Pedido> pedidosConDuplicados = query.getResultList();

            // 2. 🚩 INICIALIZACIÓN MANUAL DE LA COLECCIÓN DE IMÁGENES (Solución LIE)
            // Esto se ejecuta mientras el EntityManager (em) está ABIERTO, garantizando que la colección 'imagenes' se cargue.
            for (Pedido p : pedidosConDuplicados) {
                // Iteramos sobre la colección 'detalles' que sí fue cargada con FETCH
                for (DetallePedido d : p.getDetalles()) {
                    // Forzamos la inicialización de la colección 'imagenes' del vehículo
                    if (d.getVehiculo() != null && d.getVehiculo().getImagenes() != null) {
                        Hibernate.initialize(d.getVehiculo().getImagenes());
                    }
                }
            }

            // 3. FILTRADO: Usamos LinkedHashSet para eliminar los duplicados creados por los FETCH JOINs.
            Set<Pedido> setPedidosUnicos = new LinkedHashSet<>(pedidosConDuplicados);
            List<Pedido> pedidosFinales = new ArrayList<>(setPedidosUnicos);
            
            return pedidosFinales;

        } catch (Exception e) {
            // Es crucial que esta sección siga devolviendo una lista vacía para evitar errores 500 en la vista.
            System.err.println("--- ¡ERROR CRÍTICO EN PEDIDO DAO! ---");
            e.printStackTrace();

            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}