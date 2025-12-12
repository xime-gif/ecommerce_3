/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.CarritoDeCompras;

/**
 *
 * @author Jp
 */
@WebServlet(name = "RestarItemCarritoServlet", urlPatterns = {"/restarItemCarrito"})
public class RestarItemCarritoServlet extends HttpServlet {

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

        if (idStr != null && !idStr.isBlank()) {
            try {
                Long id = Long.valueOf(idStr);
                HttpSession session = request.getSession();
                CarritoDeCompras carrito = (CarritoDeCompras) session.getAttribute("carrito");

                if (carrito != null) {
                    carrito.restarCantidad(id);
                    session.setAttribute("carrito", carrito);
                }

            } catch (NumberFormatException e) {
                System.out.println("ID inválido al restar item");
            }
        }

        response.sendRedirect("carritoCompras.jsp");
    }

}
