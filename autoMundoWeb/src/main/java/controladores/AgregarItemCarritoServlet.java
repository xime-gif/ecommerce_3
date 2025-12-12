/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import bo.CatalogoBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.CarritoDeCompras;
import modelos.Vehiculo;

/**
 *
 * @author Jp
 */
@WebServlet(name = "AgregarItemCarritoServlet", urlPatterns = {"/agregarItemCarrito"})
public class AgregarItemCarritoServlet extends HttpServlet {

    private final CatalogoBO catalogoBO = new CatalogoBO();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String cantidadStr = request.getParameter("cantidad");

        try {
            if (idStr == null || idStr.isBlank()) {
                throw new NumberFormatException("ID nulo o vacío");
            }
            Long id = Long.valueOf(idStr);

            int cantidad = 1;
            if (cantidadStr != null && !cantidadStr.isBlank()) {
                try {
                    cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad < 1) {
                        cantidad = 1;
                    }
                } catch (NumberFormatException e) {
                    cantidad = 1;
                }
            }

            Vehiculo v = catalogoBO.obtenerVehiculo(id);

            if (v != null) {
                HttpSession session = request.getSession();
                CarritoDeCompras carrito = (CarritoDeCompras) session.getAttribute("carrito");

                if (carrito == null) {
                    carrito = new CarritoDeCompras();
                    session.setAttribute("carrito", carrito);
                }

                carrito.agregarItem(v, cantidad);

                session.setAttribute("mensajeExito", "¡" + v.getNombre() + " agregado al carrito!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- LÓGICA DE REDIRECCIÓN ---
        response.sendRedirect("carritoCompras.jsp");
    }
}
