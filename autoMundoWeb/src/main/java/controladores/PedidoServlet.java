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

    private PedidoBO pedidoBO = new PedidoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 1️⃣ Validación de Sesión
        // Verifica que la sesión exista y que el atributo del usuario esté presente.
        if (session == null || session.getAttribute("usuarioActual") == null) { 
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioActual");

        // 2️⃣ Obtener pedidos desde la capa BO
        List<Pedido> pedidos = pedidoBO.obtenerPedidosPorUsuario(usuario);

        // Log para depurar (CRUCIAL para verificar que el DAO funciona)
        System.out.println("=== SERVLET: Pedidos encontrados ===");
        System.out.println("Usuario ID: " + usuario.getId());
        System.out.println("Total pedidos (después de DAO): " + pedidos.size());
        
        // Log para verificar si los detalles y las imágenes están cargadas (para debugging)
        if (!pedidos.isEmpty()) {
             Pedido p = pedidos.get(0);
             System.out.println("DEBUG: Pedido #" + p.getId() + " tiene " + p.getDetalles().size() + " detalles.");
             if (!p.getDetalles().isEmpty()) {
                 if (!p.getDetalles().get(0).getVehiculo().getImagenes().isEmpty()) {
                     System.out.println("DEBUG: Imagen de muestra cargada: " + p.getDetalles().get(0).getVehiculo().getImagenes().get(0).getUrl());
                 }
             }
        }
        // Fin de logs de depuración
        
        
        // 3️⃣ Enviar lista al JSP
        request.setAttribute("pedidos", pedidos);

        // 4️⃣ Mandar a la vista
        request.getRequestDispatcher("pedidos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Aquí se manejan acciones (ejemplo: enviar reseña)
        String accion = request.getParameter("accion");

        if ("reseñar".equals(accion)) {
            procesarResenia(request, response);
        } else {
            // Si la acción no se reconoce, redirige a la lista de pedidos
            response.sendRedirect("pedidos");
        }
    }

    private void procesarResenia(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        // Validación de Sesión
        if (session == null || session.getAttribute("usuarioActual") == null) { 
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // El ID del detalle es un Long, pero si el parámetro viene de un campo de formulario puede ser String
            Long detalleId = Long.parseLong(request.getParameter("idDetallePedido"));
            int calificacion = Integer.parseInt(request.getParameter("calificacion"));
            String comentario = request.getParameter("comentario");

            // Nota: Aquí necesitarías inyectar el usuario si la reseña requiere el ID del cliente.
            
            pedidoBO.registrarResenia(detalleId, calificacion, comentario);

            session.setAttribute("mensaje", "Reseña registrada correctamente.");

        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico al procesar reseña: " + e.getMessage());
            session.setAttribute("error", "Error: Los IDs o la calificación deben ser números.");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "No se pudo registrar la reseña: Error interno.");
        }

        response.sendRedirect("pedidos");
    }
}