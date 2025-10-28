package daos;

import modelos.Modelo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Modelo.
 */
public class ModeloDAO extends BaseDAO<Modelo, Long> {

    public ModeloDAO() {
        super(Modelo.class);
    }
}
