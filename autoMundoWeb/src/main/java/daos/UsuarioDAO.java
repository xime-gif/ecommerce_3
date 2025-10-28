package daos;

import modelos.Usuario;
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
     * @param contrasenia La contraseña del usuario.
     * @return El objeto Usuario si las credenciales son correctas, de lo
     * contrario null.
     */
    public Usuario buscarPorCredenciales(String correo, String contrasenia) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :correo AND u.contrasenia = :contrasenia", Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("contrasenia", contrasenia)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
