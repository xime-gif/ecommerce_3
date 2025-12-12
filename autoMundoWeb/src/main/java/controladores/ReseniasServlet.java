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

@WebServlet("/resenias")
public class ReseniasServlet extends HttpServlet {

    private final ReseniaBO reseniaBO;

    public ReseniasServlet() {
        this.reseniaBO = new ReseniaBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Resenia> resenias = reseniaBO.obtenerTodasLasResenias();
            request.setAttribute("resenias", resenias);
            request.setAttribute("reseniaBO", reseniaBO);
            request.getRequestDispatcher("resenias.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar las reseñas: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
