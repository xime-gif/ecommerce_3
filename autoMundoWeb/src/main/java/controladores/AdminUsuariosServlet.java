package controladores;

import bo.UsuarioBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Usuario;
import modelos.RolUsuario;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AdminUsuariosServlet", urlPatterns = {"/adminUsuarios"})
public class AdminUsuariosServlet extends HttpServlet {

    private final UsuarioBO usuarioBO = new UsuarioBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            response.sendRedirect("adminUsuarios");
            return;
        }

        switch (accion) {
            case "editar":
                editarUsuario(request, response);
                break;
            case "cambiarEstado":
                cambiarEstadoUsuario(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response);
                break;
            case "buscar":
                buscarUsuarios(request, response);
                break;
            default:
                response.sendRedirect("adminUsuarios");
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> usuarios = usuarioBO.buscarPorRol(RolUsuario.CLIENTE);
        long totalUsuarios = usuarios.size();
        long usuariosActivos = usuarios.stream().filter(Usuario::isEsActivo).count();
        long usuariosInactivos = totalUsuarios - usuariosActivos;

        request.setAttribute("usuarios", usuarios);
        request.setAttribute("totalUsuarios", totalUsuarios);
        request.setAttribute("usuariosActivos", usuariosActivos);
        request.setAttribute("usuariosInactivos", usuariosInactivos);

        request.getRequestDispatcher("adminUsuarios.jsp").forward(request, response);
    }

    private void buscarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String termino = request.getParameter("termino");
        String estado = request.getParameter("estado");

        List<Usuario> usuarios;

        if (termino != null && !termino.trim().isEmpty()) {
            usuarios = usuarioBO.buscarPorNombreOCorreo(termino.trim()).stream()
                    .filter(u -> u.getRol() == RolUsuario.CLIENTE)
                    .collect(Collectors.toList());
        } else {
            usuarios = usuarioBO.buscarPorRol(RolUsuario.CLIENTE);
        }

        if (estado != null && !estado.isEmpty() && !estado.equals("todos")) {
            boolean esActivo = estado.equals("activos");
            usuarios = usuarios.stream()
                    .filter(u -> u.isEsActivo() == esActivo)
                    .collect(Collectors.toList());
        }

        List<Usuario> todosClientes = usuarioBO.buscarPorRol(RolUsuario.CLIENTE);
        long totalUsuarios = todosClientes.size();
        long usuariosActivos = todosClientes.stream().filter(Usuario::isEsActivo).count();
        long usuariosInactivos = totalUsuarios - usuariosActivos;

        request.setAttribute("usuarios", usuarios);
        request.setAttribute("totalUsuarios", totalUsuarios);
        request.setAttribute("usuariosActivos", usuariosActivos);
        request.setAttribute("usuariosInactivos", usuariosInactivos);
        request.setAttribute("terminoBusqueda", termino);
        request.setAttribute("estadoSeleccionado", estado);

        request.getRequestDispatcher("adminUsuarios.jsp").forward(request, response);
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");

            String error = usuarioBO.editarUsuario(id, nombre, correo);
            if (error == null) {
                response.sendRedirect("adminUsuarios?msg=editado");
            } else {
                response.sendRedirect("adminUsuarios?error=" + java.net.URLEncoder.encode(error, "UTF-8"));
            }
        } catch (Exception e) {
            response.sendRedirect("adminUsuarios?error=edicion");
        }
    }

    private void cambiarEstadoUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Long id = Long.valueOf(request.getParameter("id"));

            Boolean estadoActual = usuarioBO.obtenerEstado(id);

            String error = usuarioBO.cambiarEstado(id);
            if (error == null) {
                String msg = (estadoActual != null && estadoActual) ? "bloqueado" : "activado";
                response.sendRedirect("adminUsuarios?msg=" + msg);
            } else {
                response.sendRedirect("adminUsuarios?error=" + java.net.URLEncoder.encode(error, "UTF-8"));
            }
        } catch (Exception e) {
            response.sendRedirect("adminUsuarios?error=estado");
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Long id = Long.valueOf(request.getParameter("id"));

            String error = usuarioBO.eliminarUsuario(id);
            if (error == null) {
                response.sendRedirect("adminUsuarios?msg=eliminado");
            } else {
                response.sendRedirect("adminUsuarios?error=" + java.net.URLEncoder.encode(error, "UTF-8"));
            }
        } catch (Exception e) {
            response.sendRedirect("adminUsuarios?error=eliminar");
        }
    }
}
