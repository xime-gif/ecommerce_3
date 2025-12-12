package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import modelos.Pedido;
import modelos.Usuario;

// IMPORTACIÓN ELIMINADA: Ya no se necesita Hibernate.initialize()
// ya que la responsabilidad de cargar detalles es movida a las BO/Servicios.
// import org.hibernate.Hibernate; 

public class PedidoDAO extends BaseDAO<Pedido, Long> {

    public PedidoDAO() {
        super(Pedido.class);
    }
    
    public List<Pedido> obtenerPedidosPorCliente(Usuario cliente) {
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();

            String jpql = "SELECT p FROM Pedido p "
                        + "WHERE p.cliente.id = :clienteId "
                        + "ORDER BY p.fechaCompra DESC";
            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("clienteId", cliente.getId());
            return query.getResultList();

        } catch (Exception e) {
            System.err.println("--- ¡ERROR EN PEDIDO DAO! ---");
            e.printStackTrace();
            return new ArrayList<>(); 
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}