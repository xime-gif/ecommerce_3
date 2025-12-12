package controladores;

import bo.PedidoBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import modelos.Usuario;
import modelos.Pedido;

@WebServlet("/pedidos")
public class PedidoServlet extends HttpServlet {

    private final PedidoBO pedidoBO = new PedidoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioActual") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioActual");

        try {
            List<Pedido> pedidos = pedidoBO.obtenerPedidosPorUsuario(usuario);
            System.out.println("=== SERVLET: Pedidos encontrados ===");
            System.out.println("Usuario ID: " + usuario.getId());
            System.out.println("Total pedidos (cargados por BO): " + pedidos.size());
            
            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("pedidos.jsp").forward(request, response);
            
        } catch (Exception e) {
            System.err.println("Error al obtener pedidos para el usuario " + usuario.getId() + ": " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error interno al cargar el historial de pedidos.");
            request.getRequestDispatcher("pedidos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("reseñar".equals(accion)) {
            procesarResenia(request, response);
        } else {
            response.sendRedirect("pedidos");
        }
    }

    private void procesarResenia(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioActual") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Long detalleId = Long.parseLong(request.getParameter("idDetallePedido"));
            int calificacion = Integer.parseInt(request.getParameter("calificacion"));
            String comentario = request.getParameter("comentario");
            
            pedidoBO.registrarResenia(detalleId, calificacion, comentario);

            session.setAttribute("mensaje", "Reseña registrada correctamente. ¡Gracias por tu opinión!");

        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico al procesar reseña: " + e.getMessage());
            session.setAttribute("error", "Error: La calificación debe ser un número entre 1 y 5.");
        } catch (Exception e) {
            System.err.println("Fallo al registrar la reseña: " + e.getMessage());
            session.setAttribute("error", "No se pudo registrar la reseña: " + e.getMessage());
        }

        response.sendRedirect("pedidos");
    }
}
