package daos;

import interfaces.IBaseDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Implementación abstracta y genérica de un Data Access Object (DAO) utilizando
 * JPA. Contiene la lógica común para las operaciones CRUD.
 *
 * @param <T> El tipo de la entidad.
 * @param <ID> El tipo del identificador (ID) de la entidad.
 */
public abstract class BaseDAO<T, ID extends Serializable> implements IBaseDAO<T, ID> {

    protected final EntityManager em;
    private final Class<T> entityClass;

    /**
     * Constructor que inicializa el DAO con la clase de la entidad específica.
     *
     * @param entityClass El objeto Class de la entidad que este DAO manejará.
     */
    public BaseDAO(Class<T> entityClass) {
        this.em = JPAUtil.getEntityManager();
        this.entityClass = entityClass;
    }

    @Override
    public void crear(T entity) {
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al persistir la entidad de tipo " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public T buscarPorId(ID id) {
        return em.find(entityClass, id);
    }

    @Override
    public void actualizar(T entity) {
        em.getTransaction().begin();
        try {
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar la entidad de tipo " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public void eliminar(T entity) {
        em.getTransaction().begin();
        try {
            // Asegura que la entidad esté gestionada por el EntityManager antes de eliminarla.
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar la entidad de tipo " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public List<T> buscarTodos() {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
}
