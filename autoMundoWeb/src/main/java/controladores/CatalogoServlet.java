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
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros
        String busqueda = request.getParameter("q");
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        String precioMin = request.getParameter("min");
        String precioMax = request.getParameter("max");

        List<Vehiculo> vehiculos;

        // Filtros de búsqueda
        try {

            if (marca != null && !marca.isBlank()) {
                vehiculos = catalogoBO.filtrarPorMarca(marca);

            } else if (categoria != null && !categoria.isBlank()) {
                Long idCategoria = Long.parseLong(categoria);
                vehiculos = catalogoBO.filtrarPorCategoria(idCategoria);

            } else if (precioMin != null && precioMax != null
                    && !precioMin.isBlank() && !precioMax.isBlank()) {

                double min = Double.parseDouble(precioMin);
                double max = Double.parseDouble(precioMax);

                vehiculos = vehiculoDAO.buscarPorRangoPrecio(min, max);

            } else {
                // Sin filtros
                vehiculos = catalogoBO.obtenerCatalogo();
            }

        } catch (Exception e) {
            // Cualquier fallo muestra el catálogo completo
            vehiculos = catalogoBO.obtenerCatalogo();
        }

        // Cargar catálogos auxiliares
        request.setAttribute("marcas", marcaDAO.buscarTodos());
        request.setAttribute("categorias", categoriaDAO.buscarTodos());

        // Mantener filtros seleccionados
        request.setAttribute("marcaSel", marca);
        request.setAttribute("categoriaSel", categoria);
        request.setAttribute("minSel", precioMin);
        request.setAttribute("maxSel", precioMax);

        // Datos del catálogo
        request.setAttribute("vehiculos", vehiculos);

        // argar vista
        request.getRequestDispatcher("/catalogo.jsp").forward(request, response);
    }
}
