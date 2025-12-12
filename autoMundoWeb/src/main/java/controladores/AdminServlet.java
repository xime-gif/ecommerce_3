package controladores;

import bo.AltasBajasBo;
import daos.CategoriaDAO;
import daos.ModeloDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Vehiculo;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.servlet.ServletContext;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    private final AltasBajasBo altasBajasBo = new AltasBajasBo();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final ModeloDAO modeloDAO = new ModeloDAO();

    /**
     * Filtra los paths para asegurarse de que terminen en una extensión de
     * imagen válida.
     */
    private boolean isImageFile(String path) {
        String lowerPath = path.toLowerCase();
        return lowerPath.endsWith(".png")
                || lowerPath.endsWith(".jpg")
                || lowerPath.endsWith(".jpeg")
                || lowerPath.endsWith(".gif")
                || lowerPath.endsWith(".svg")
                || lowerPath.endsWith(".avif")
                || lowerPath.endsWith(".webp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "lista";
        }

        switch (accion) {
            case "altaForm":
                ServletContext context = getServletContext();

                Set<String> mainResourcePaths = context.getResourcePaths("/imagenes/");
                List<String> imagenesDisponibles;

                if (mainResourcePaths != null) {
                    imagenesDisponibles = mainResourcePaths.stream()
                            .filter(path -> isImageFile(path) && !path.contains("/icon_Caracteristicas/"))
                            .map(path -> path.startsWith("/") ? path.substring(1) : path)
                            .collect(Collectors.toList());
                } else {
                    imagenesDisponibles = new ArrayList<>();
                }

                Collections.sort(imagenesDisponibles);
                request.setAttribute("imagenesDisponibles", imagenesDisponibles);

                Set<String> iconResourcePaths = context.getResourcePaths("/imagenes/icon_Caracteristicas/");
                List<String> iconosDisponibles;

                if (iconResourcePaths != null) {
                    iconosDisponibles = iconResourcePaths.stream()
                            .filter(this::isImageFile)
                            .map(path -> path.startsWith("/") ? path.substring(1) : path)
                            .collect(Collectors.toList());
                } else {
                    iconosDisponibles = new ArrayList<>();
                }

                Collections.sort(iconosDisponibles);
                request.setAttribute("iconosDisponibles", iconosDisponibles);

                request.setAttribute("categorias", categoriaDAO.buscarTodos());
                request.setAttribute("modelos", modeloDAO.buscarTodos());
                request.getRequestDispatcher("adminAltas.jsp").forward(request, response);
                break;
            case "lista":
            List<Vehiculo> autos = altasBajasBo.obtenerAutos();
            request.setAttribute("vehiculos", autos);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            break;
        }
    }

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

    private void registrarVehiculo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Vehiculo v = new Vehiculo();
        v.setNombre(request.getParameter("nombre"));
        v.setPrecio(Double.valueOf(request.getParameter("precio")));
        String existenciasStr = request.getParameter("existencias");
        v.setExistencias(existenciasStr != null && !existenciasStr.isEmpty() ? Integer.valueOf(existenciasStr) : 1);
        Long idCategoria = Long.valueOf(request.getParameter("categoria"));
        v.setCategoria(categoriaDAO.buscarPorId(idCategoria));

        Long idModelo = Long.valueOf(request.getParameter("modelo"));
        v.setModelo(modeloDAO.buscarPorId(idModelo));
        for (int i = 1; i <= 6; i++) {
            String desc = request.getParameter("desc" + i);
            String icon = request.getParameter("icon" + i);
            if (desc != null && !desc.trim().isEmpty()) {
                modelos.Caracteristica c = new modelos.Caracteristica();
                c.setNombre(desc.trim());
                if (icon != null && !icon.trim().isEmpty()) {
                    c.setRutaIcono(icon.trim());
                } else {
                    c.setRutaIcono("imagenes/icon_Caracteristicas/default.png");
                }
                v.agregarCaracteristica(c);
            }
        }
        String urlImagen = request.getParameter("imagen");
        altasBajasBo.altaVehiculo(v, urlImagen);
        response.sendRedirect("admin?accion=altaForm&msg=ok");
    }

}
