package daos;

import java.util.List;
import modelos.Modelo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Modelo.
 */
public class ModeloDAO extends BaseDAO<Modelo, Long> {

    public ModeloDAO() {
        super(Modelo.class);
    }
    
    public List<Modelo> buscarPorMarca(Long idMarca) {
        return em.createQuery("SELECT m FROM Modelo m WHERE m.marca.id = :idMarca", Modelo.class)
                .setParameter("idMarca", idMarca)
                .getResultList();
    }
}
