/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import bo.DireccionBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Direccion;
import modelos.Usuario;

/**
 *
 * @author Jp
 */
@WebServlet(name = "GuardarDireccionServlet", urlPatterns = {"/guardarDireccion"})
public class GuardarDireccionServlet extends HttpServlet {

    private final DireccionBO direccionBO = new DireccionBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Verificar sesión
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioActual");

        if (usuario == null) {
            response.sendRedirect("inicioSesion.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String calle = request.getParameter("calle");
        String numero = request.getParameter("numero");
        String colonia = request.getParameter("colonia");
        String cp = request.getParameter("codigoPostal");
        String ciudad = request.getParameter("ciudad");
        String estado = request.getParameter("estado");
        String pais = request.getParameter("pais");

        try {
            Direccion nuevaDireccion = new Direccion();
            nuevaDireccion.setNombre(nombre);
            nuevaDireccion.setCalle(calle);
            nuevaDireccion.setNumero(numero);
            nuevaDireccion.setColonia(colonia);
            nuevaDireccion.setCodigoPostal(cp);
            nuevaDireccion.setCiudad(ciudad);
            nuevaDireccion.setEstado(estado);
            nuevaDireccion.setPais(pais);

            nuevaDireccion.setUsuario(usuario);

            direccionBO.guardar(nuevaDireccion);
            usuario.getDirecciones().add(nuevaDireccion);
            session.setAttribute("usuarioActual", usuario);
            response.sendRedirect("checkout.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("nuevaDireccion.jsp?error=true");
        }
    }
}
