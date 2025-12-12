package daos;

import jakarta.persistence.EntityManager;
import java.util.List;
import modelos.DetallePedido;

/**
 * DAO para la entidad DetallePedido.
 */
public class DetallePedidoDAO extends BaseDAO<DetallePedido, Long> {

    public DetallePedidoDAO() {
        super(DetallePedido.class);
    }
    
    /**
     * Obtiene todos los detalles de un pedido, cargando ansiosamente (EAGER)
     * el Vehículo asociado para evitar la LIE.
     *
     * @param idPedido ID del pedido padre.
     * @return Lista de detalles del pedido.
     */
    public List<DetallePedido> obtenerDetallesPorPedido(Long idPedido) {
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            String jpql = "SELECT d FROM DetallePedido d JOIN FETCH d.vehiculo v WHERE d.pedido.id = :idPedido"; 
            return em.createQuery(jpql, DetallePedido.class)
            .setParameter("idPedido", idPedido)
            .getResultList();
            
        } finally {
            em.close();
        }
    }
}