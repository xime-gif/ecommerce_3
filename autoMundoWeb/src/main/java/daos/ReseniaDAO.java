package daos;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import modelos.Usuario;
import modelos.Resenia;
import modelos.Vehiculo;

public class ReseniaDAO extends BaseDAO<Resenia, Long> {

    private final UsuarioDAO usuarioDAO;
    private final VehiculoDAO vehiculoDAO;

    public ReseniaDAO() {
        super(Resenia.class);
        this.usuarioDAO = new UsuarioDAO();
        this.vehiculoDAO = new VehiculoDAO();
    }

    public boolean agregarResenia(int calificacion, String comentario, Long usuarioId, Long vehiculoId) {
        Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
        Vehiculo vehiculo = vehiculoDAO.buscarPorId(vehiculoId);

        if (usuario == null) {
            System.err.println("Error: No se encontró el Usuario con ID: " + usuarioId);
            return false;
        }
        if (vehiculo == null) {
            System.err.println("Error: No se encontró el Vehiculo con ID: " + vehiculoId);
            return false;
        }

        Resenia resenia = new Resenia();
        resenia.setCalificacion(calificacion);
        resenia.setComentario(comentario);
        resenia.setFechaHora(LocalDateTime.now());
        resenia.setCliente(usuario);
        resenia.setVehiculo(vehiculo);

        try {
            this.crear(resenia);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar la reseña: " + e.getMessage());
            return false;
        }
    }

    public List<Resenia> buscarTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Resenia r", Resenia.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
