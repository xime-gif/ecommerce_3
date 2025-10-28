package daos;

import modelos.DetallePedido;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad
 * DetallePedido.
 */
public class DetallePedidoDAO extends BaseDAO<DetallePedido, Long> {

    public DetallePedidoDAO() {
        super(DetallePedido.class);
    }
}
