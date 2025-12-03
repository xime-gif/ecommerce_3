/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import bo.AltasBajasBo;
import daos.CategoriaDAO;
import daos.ModeloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelos.Categoria;
import modelos.Vehiculo;

/**
 *
 * @author javie
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    private final AltasBajasBo altasBajasBo = new AltasBajasBo();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final ModeloDAO modeloDAO = new ModeloDAO();
    
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
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "lista";
        }

        switch (accion) {

            case "altaForm":
                request.setAttribute("categorias", categoriaDAO.buscarTodos());
                request.setAttribute("modelos", modeloDAO.buscarTodos());
                request.getRequestDispatcher("adminAltas.jsp").forward(request, response);
                break;

            case "baja":
                Long id = Long.valueOf(request.getParameter("id"));
                altasBajasBo.bajaVehiculo(id);
                response.sendRedirect("admin?accion=lista&msg=eliminado");
                break;

            case "lista":
            default:
                List<Vehiculo> autos = altasBajasBo.obtenerAutos();
                request.setAttribute("vehiculos", autos);
                request.getRequestDispatcher("adminBajas.jsp").forward(request, response);
        }
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

        String accion = request.getParameter("accion");

        if ("alta".equals(accion)) {
            registrarVehiculo(request, response);
        } else {
            response.sendRedirect("admin?accion=lista");
        }
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

    
    /**
     * Registrar un nuevo vehículo
     */
    private void registrarVehiculo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Vehiculo v = new Vehiculo();
        
        v.setNombre(request.getParameter("nombre"));
        v.setDescripcion(request.getParameter("descripcion"));
        v.setPrecio(Double.valueOf(request.getParameter("precio")));
        v.setExistencias(Integer.valueOf(request.getParameter("existencias")));
        v.setKilometraje(Integer.valueOf(request.getParameter("kilometraje")));
        v.setAnio(Integer.valueOf(request.getParameter("anio")));
        v.setMotor(request.getParameter("motor"));
        v.setTransmision(request.getParameter("transmision"));
        v.setColor(request.getParameter("color"));

        Long idCategoria = Long.valueOf(request.getParameter("categoria"));
        v.setCategoria(categoriaDAO.buscarPorId(idCategoria));

        Long idModelo = Long.valueOf(request.getParameter("modelo"));
        v.setModelo(modeloDAO.buscarPorId(idModelo));

        String urlImagen = request.getParameter("imagen");

        altasBajasBo.altaVehiculo(v, urlImagen);

        response.sendRedirect("admin?accion=altaForm&msg=ok");
    }


}
