package daos;

import java.util.List;
import modelos.Marca;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Marca.
 */
public class MarcaDAO extends BaseDAO<Marca, Long> {

    public MarcaDAO() {
        super(Marca.class);
    }
    
    public Marca buscarPorNombre(String nombre) {
        List<Marca> lista = em.createQuery(
                "SELECT m FROM Marca m WHERE m.nombre = :nombre", Marca.class)
                .setParameter("nombre", nombre)
                .getResultList();
        
        return lista.isEmpty() ? null : lista.get(0);
    }
}
