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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros
        String busqueda = request.getParameter("q");
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        String precioMin = request.getParameter("min");
        String precioMax = request.getParameter("max");

        Long categoriaId = null;
        Double min = null, max = null;

        // Filtros de búsqueda
        try {

            if (categoria != null && !categoria.isBlank())
                categoriaId = Long.parseLong(categoria);

            if (precioMin != null && !precioMin.isBlank())
                min = Double.parseDouble(precioMin);

            if (precioMax != null && !precioMax.isBlank())
                max = Double.parseDouble(precioMax);

            } catch (Exception e) {
                // Cualquier fallo manda la excepción
                e.printStackTrace();
            }

        // Cargar catálogos auxiliares
        List<Vehiculo> vehiculos = catalogoBO.filtrarCatalogo(busqueda, marca, categoriaId, min, max);
        
        request.setAttribute("marcas", marcaDAO.buscarTodos());
        request.setAttribute("categorias", categoriaDAO.buscarTodos());

        // Mantener filtros seleccionados
        request.setAttribute("q", busqueda);
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
