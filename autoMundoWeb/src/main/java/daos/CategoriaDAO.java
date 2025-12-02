package daos;

import java.util.List;
import modelos.Categoria;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Categoria.
 */
public class CategoriaDAO extends BaseDAO<Categoria, Long> {

    public CategoriaDAO() {
        super(Categoria.class);
    }
    
    public Categoria buscarPorNombre(String nombre) {
        List<Categoria> lista = em.createQuery("SELECT c FROM Categoria c WHERE c.nombre = :nombre", Categoria.class)
                .setParameter("nombre", nombre)
                .getResultList();
        
        return lista.isEmpty() ? null : lista.get(0);
    }
}
