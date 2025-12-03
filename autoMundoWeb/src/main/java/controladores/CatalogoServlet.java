/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import bo.CatalogoBO;
import daos.CategoriaDAO;
import daos.MarcaDAO;
import daos.VehiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelos.Vehiculo;

/**
 *
 * @author Ximena
 */
@WebServlet(name = "CatalogoServlet", urlPatterns = {"/catalogo"})
public class CatalogoServlet extends HttpServlet {
    private final CatalogoBO catalogoBO = new CatalogoBO();
    private final MarcaDAO marcaDAO = new MarcaDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CatalogoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CatalogoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        String precioMin = request.getParameter("min");
        String precioMax = request.getParameter("max");
        // Crear lista de vehiculos
        List<Vehiculo> vehiculos;
        // Filtros para catálogo
        if (marca != null && !marca.isBlank()) {
            vehiculos = catalogoBO.filtrarPorMarca(marca);
        } else if (categoria != null && !categoria.isBlank()) {
            Long idCategoria = Long.parseLong(categoria);
            vehiculos = catalogoBO.filtrarPorCategoria(idCategoria);
        } else if (precioMin != null && precioMax != null) {
            try {
                double min = Double.parseDouble(precioMin);
                double max = Double.parseDouble(precioMax);
                vehiculos = new VehiculoDAO().buscarPorRangoPrecio(min, max);
            } catch (Exception e) {
                vehiculos = catalogoBO.obtenerCatalogo();
            }
        } else {
            vehiculos = catalogoBO.obtenerCatalogo();
        }
        
        // Cargar marca y categoría
        request.setAttribute("marcas", marcaDAO.buscarTodos());
        request.setAttribute("categoria", categoriaDAO.buscarTodos());
        // Enviar resultados
        request.setAttribute("vehiculos", vehiculos);
        request.getRequestDispatcher("/catalogo.jsp").forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
