package daos;

import modelos.ItemCarrito;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad ItemCarrito.
 */
public class ItemCarritoDAO extends BaseDAO<ItemCarrito, Long> {

    public ItemCarritoDAO() {
        super(ItemCarrito.class);
    }
}
