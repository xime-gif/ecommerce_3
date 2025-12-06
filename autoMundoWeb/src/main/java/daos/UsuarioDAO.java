package daos;

import modelos.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Usuario.
 */
public class UsuarioDAO extends BaseDAO<Usuario, Long> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    /**
     * Busca un usuario por su correo electrónico y contraseña para la
     * autenticación.
     *
     * @param correo El correo electrónico del usuario.
     * @param contrasena La contraseña del usuario.
     * @return El objeto Usuario si las credenciales son correctas, de lo
     * contrario null.
     */
    public Usuario buscarPorCredenciales(String correo, String contrasena) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena",
                    Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("contrasena", contrasena)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario buscarPorCorreo(String correo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo",
                    Usuario.class)
                    .setParameter("correo", correo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
