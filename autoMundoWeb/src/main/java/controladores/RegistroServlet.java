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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    private final UsuarioBO usuarioBO = new UsuarioBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nombre = req.getParameter("nombreCompleto");
        String correo = req.getParameter("correo");
        String contrasena = req.getParameter("contrasenia");
        String confirmar = req.getParameter("confirmarContrasenia");

        String error = usuarioBO.registrarCliente(nombre, correo, contrasena, confirmar);

        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("registro.jsp").forward(req, resp);
            return;
        }

        Usuario usuario = usuarioBO.iniciarSesion(correo, contrasena);

        HttpSession sesion = req.getSession();
        sesion.setAttribute("usuarioActual", usuario);

        resp.sendRedirect("index.jsp");
    }
}
