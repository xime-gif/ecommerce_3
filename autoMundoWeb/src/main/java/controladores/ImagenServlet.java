package controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/imagenServlet")
public class ImagenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String rutaImagen = request.getParameter("ruta");

        if (rutaImagen == null || rutaImagen.isBlank()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }


        File archivo = new File(rutaImagen);


        if (archivo.exists()) {

            String mimeType = getServletContext().getMimeType(archivo.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);

            Files.copy(archivo.toPath(), response.getOutputStream());
        } else {
            response.sendRedirect("imagenes/default_car.jpg"); 
        }
    }
}