/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import bo.PedidoBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.CarritoDeCompras;
import modelos.Direccion;
import modelos.Pedido;
import modelos.Usuario;

/**
 *
 * @author Jp
 */
@WebServlet(name = "ProcesarPedidoServlet", urlPatterns = {"/procesarPedido"})
public class ProcesarPedidoServlet extends HttpServlet {

    private final PedidoBO pedidoBO = new PedidoBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
        CarritoDeCompras carrito = (CarritoDeCompras) session.getAttribute("carrito");

        if (usuario == null) {
            response.sendRedirect("inicioSesion.jsp");
            return;
        }

        if (carrito == null || carrito.getItems().isEmpty()) {
            session.setAttribute("mensajeError", "No puedes pagar un carrito vacío.");
            response.sendRedirect("carritoCompras.jsp");
            return;
        }

        try {
            String idDireccionStr = request.getParameter("idDireccion");
            String numeroTarjeta = request.getParameter("numeroTarjeta");

            String metodoPago = "Tarjeta - " + (numeroTarjeta.length() > 4
                    ? "**** " + numeroTarjeta.substring(numeroTarjeta.length() - 4)
                    : "Crédito/Débito");

            Long idDireccion = Long.parseLong(idDireccionStr);
            Direccion direccionEnvio = null;

            if (usuario.getDirecciones() != null) {
                for (Direccion dir : usuario.getDirecciones()) {
                    if (dir.getId().equals(idDireccion)) {
                        direccionEnvio = dir;
                        break;
                    }
                }
            }

            if (direccionEnvio == null) {
                throw new Exception("La dirección de envío seleccionada no es válida.");
            }

            Pedido pedido = pedidoBO.realizarCompra(usuario, carrito, direccionEnvio, metodoPago);

            session.setAttribute("mensajeExito", "¡Compra exitosa! Tu pedido #" + pedido.getNumeroPedido() + " ha sido registrado.");

            response.sendRedirect("pedidos.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("mensajeError", "Error al procesar tu compra: " + e.getMessage());
            response.sendRedirect("checkout.jsp");
        }
    }
}
