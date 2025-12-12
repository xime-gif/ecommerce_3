package daos;

import modelos.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

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

    /**
     * Busca usuarios por nombre o correo (búsqueda parcial).
     */
    public List<Usuario> buscarPorNombreOCorreo(String termino) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(:termino) OR LOWER(u.correo) LIKE LOWER(:termino)",
                    Usuario.class)
                    .setParameter("termino", "%" + termino + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca usuarios por rol.
     */
    public List<Usuario> buscarPorRol(modelos.RolUsuario rol) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.rol = :rol",
                    Usuario.class)
                    .setParameter("rol", rol)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Busca usuarios por estado activo/inactivo.
     * @param esActivo
     * @return 
     */
    public List<Usuario> buscarPorEstado(boolean esActivo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.esActivo = :esActivo",
                    Usuario.class)
                    .setParameter("esActivo", esActivo)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Cuenta usuarios por estado.
     */
    public long contarPorEstado(boolean esActivo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT COUNT(u) FROM Usuario u WHERE u.esActivo = :esActivo",
                    Long.class)
                    .setParameter("esActivo", esActivo)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     * Cuenta el total de usuarios.
     */
    public long contarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT COUNT(u) FROM Usuario u",
                    Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
