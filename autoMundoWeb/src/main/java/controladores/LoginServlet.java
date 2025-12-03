package controladores;

import bo.UsuarioBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;
import java.io.IOException;
import modelos.CarritoDeCompras;

@WebServlet("/inicioSesion")
public class LoginServlet extends HttpServlet {

    private final UsuarioBO usuarioBO = new UsuarioBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("⚡ ENTRO AL SERVLET ⚡");   

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        Usuario usuario = usuarioBO.iniciarSesion(correo, contrasena);

        if (usuario == null) {
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
            return;
        }

        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuarioActual", usuario);
        sesion.setAttribute("carrito", new CarritoDeCompras());

        switch (usuario.getRol()) {
            case ADMINISTRADOR:
                response.sendRedirect("adminUsuarios.jsp");
                break;
            case CLIENTE:
                response.sendRedirect("index.jsp");
                break;
            default:
                response.sendRedirect("inicioSesion.jsp");
        }
    }
}
