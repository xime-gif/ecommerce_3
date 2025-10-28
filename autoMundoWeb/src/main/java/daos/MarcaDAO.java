package daos;

import modelos.Marca;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Marca.
 */
public class MarcaDAO extends BaseDAO<Marca, Long> {

    public MarcaDAO() {
        super(Marca.class);
    }
}
