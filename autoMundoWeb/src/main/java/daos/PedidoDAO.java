package daos;

import modelos.Pedido;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Pedido.
 */
public class PedidoDAO extends BaseDAO<Pedido, Long> {

    public PedidoDAO() {
        super(Pedido.class);
    }

}
