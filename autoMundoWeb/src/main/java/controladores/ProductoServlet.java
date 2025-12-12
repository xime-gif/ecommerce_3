package controladores;

import daos.VehiculoDAO;
import modelos.Vehiculo;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/producto"})
public class ProductoServlet extends HttpServlet {

    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isBlank()) {
            try {
                Long id = Long.valueOf(idStr);

                Vehiculo vehiculo = vehiculoDAO.buscarPorIdConImagenes(id);

                if (vehiculo != null) {
                    request.setAttribute("vehiculo", vehiculo);
                    request.getRequestDispatcher("/productos.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
            }
        }

        response.sendRedirect("catalogo");
    }
}
