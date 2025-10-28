package daos;

import modelos.Direccion;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Direccion.
 */
public class DireccionDAO extends BaseDAO<Direccion, Long> {

    public DireccionDAO() {
        super(Direccion.class);
    }
}
