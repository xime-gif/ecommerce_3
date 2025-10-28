package daos;

import modelos.Categoria;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Categoria.
 */
public class CategoriaDAO extends BaseDAO<Categoria, Long> {

    public CategoriaDAO() {
        super(Categoria.class);
    }
}
