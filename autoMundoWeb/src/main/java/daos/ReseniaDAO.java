package daos;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime; // Importación necesaria para LocalDateTime.now()
import java.util.List;
import modelos.Usuario; // Asegúrate de importar la clase Usuario
import modelos.Resenia;
import modelos.Vehiculo;

/**
 * DAO para gestionar las operaciones de persistencia de la entidad Resenia.
 */
public class ReseniaDAO extends BaseDAO<Resenia, Long> {

    // Cambiado de ClienteDAO a UsuarioDAO
    private final UsuarioDAO usuarioDAO;
    private final VehiculoDAO vehiculoDAO;

    public ReseniaDAO() {
        super(Resenia.class);
        // Inicialización de los DAOs necesarios
        this.usuarioDAO = new UsuarioDAO(); // Asumiendo que existe UsuarioDAO
        this.vehiculoDAO = new VehiculoDAO();
    }

    /**
     * Agrega una nueva reseña con la calificación, comentario y las entidades
     * Usuario y Vehiculo asociadas, llenando la fecha/hora actual.
     *
     * @param calificacion La calificación de la reseña.
     * @param comentario El comentario de la reseña.
     * @param usuarioId El ID del usuario que realiza la reseña. // Nombre
     * ajustado
     * @param vehiculoId El ID del vehículo reseñado.
     * @return true si la reseña se guardó exitosamente, false en caso
     * contrario.
     */
    public boolean agregarResenia(int calificacion, String comentario, Long usuarioId, Long vehiculoId) {

        // 1. Buscar las entidades Usuario y Vehiculo
        Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
        Vehiculo vehiculo = vehiculoDAO.buscarPorId(vehiculoId);

        // Validar que el usuario y el vehículo existan
        if (usuario == null) {
            System.err.println("Error: No se encontró el Usuario con ID: " + usuarioId);
            return false;
        }
        if (vehiculo == null) {
            System.err.println("Error: No se encontró el Vehiculo con ID: " + vehiculoId);
            return false;
        }

        // 2. Crear la nueva instancia de Resenia
        Resenia resenia = new Resenia();

        // 3. Llenar los datos recibidos y faltantes
        resenia.setCalificacion(calificacion);
        resenia.setComentario(comentario);

        // ¡Línea Corregida! Usamos LocalDateTime.now() porque tu modelo usa LocalDateTime
        resenia.setFechaHora(LocalDateTime.now());

        // 4. Establecer las relaciones 
        resenia.setCliente(usuario); // El setter se llama setCliente, pero recibe un Usuario
        resenia.setVehiculo(vehiculo);

        // 5. Persistir la entidad usando el método heredado de BaseDAO (guardar)
        try {
            this.crear(resenia);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar la reseña: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las reseñas presentes en la base de datos.
     *
     * @return Una lista de todas las reseñas.
     */
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
