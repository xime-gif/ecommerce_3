package controladores;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Resenia;
import bo.ReseniaBO;

/**
 * Servlet que actúa como Controlador para la funcionalidad de Reseñas.
 */
@WebServlet("/resenias") // Mapeo del Servlet (URL para acceder: /resenias)
public class ReseniasServlet extends HttpServlet {

    private final ReseniaBO reseniaBO;

    public ReseniasServlet() {
        this.reseniaBO = new ReseniaBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Llamar a la capa de negocio para obtener los datos
            List<Resenia> resenias = reseniaBO.obtenerTodasLasResenias();
            
            // 2. Colocar los datos en el objeto request (para que la Vista pueda acceder a ellos)
            request.setAttribute("resenias", resenias);
            
            // 3. Colocar el BO en el request, ya que la Vista necesitará
            //    llamar a 'obtenerRutaImagenPrincipal' para cada reseña.
            request.setAttribute("reseniaBO", reseniaBO);

            // 4. Redirigir la solicitud a la vista (JSP)
            request.getRequestDispatcher("resenias.jsp").forward(request, response);

        } catch (Exception e) {
            // Manejo de errores
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las reseñas: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    // Si manejas la adición de una reseña, usarías doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para procesar la adición de una reseña (ej. llamar a agregarResenia)
    }
}