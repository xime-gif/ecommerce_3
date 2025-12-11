package interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Define el contrato estándar para las operaciones de persistencia de datos
 * (CRUD).
 *
 * @param <T> El tipo de la entidad.
 * @param <ID> El tipo del identificador (ID) de la entidad.
 */
public interface IBaseDAO<T, ID extends Serializable> {

    /**
     * Persiste una nueva entidad en la base de datos.
     *
     * @param entity La entidad a guardar.
     * @return la entidad creada.
     */
    T crear(T entity);

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id El ID de la entidad a buscar.
     * @return La entidad encontrada, o null si no existe.
     */
    T buscarPorId(ID id);

    /**
     * Actualiza el estado de una entidad existente en la base de datos.
     *
     * @param entity La entidad con los datos actualizados.
     * @return la entidad actualizada.
     */
    T actualizar(T entity);

    /**
     * Elimina una entidad de la base de datos.
     *
     * @param entity La entidad a eliminar.
     * @return la entidad eliminada.
     */
    T eliminar(T entity);

    /**
     * Recupera una lista de todas las entidades de un tipo específico.
     *
     * @return Una lista con todas las entidades.
     */
    List<T> buscarTodos();
}
