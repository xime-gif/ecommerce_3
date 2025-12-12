<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servicios.ReseniaBO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%-- Necesitas importar JSTL si lo vas a usar, aunque con EL puro también funciona --%>
 
<%
    // Recuperar el BO y la lista de reseñas del Request
    List<modelos.Resenia> resenias = (List<modelos.Resenia>) request.getAttribute("resenias");
    ReseniaBO reseniaBO = (ReseniaBO) request.getAttribute("reseniaBO");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Reseñas - AutoZone</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/resenias.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>

    <body>
        <%@include file="partials/header.jspf" %>
        
        <main>
            <div class="pedidos-wrapper">
                <h1 class="titulo-historial">Reseñas Publicadas</h1>

                <% 
                    if (resenias != null && !resenias.isEmpty()) { 
                        for (modelos.Resenia resenia : resenias) {
                            
                            // Llamar al BO para obtener la ruta de la imagen, pasando el ID del vehículo
                            String rutaImagen = reseniaBO.obtenerRutaImagenPrincipal(resenia.getVehiculo().getId());
                %>
                
                <div class="pedido-box">
                    <div class="contenido">

                        <img src="<%= rutaImagen %>" class="img-producto" alt="<%= resenia.getVehiculo().getNombre() %>">

                        <div class="info">
                            <h2><%= resenia.getVehiculo().getNombre() %></h2> 
                            <p class="comentario">“<%= resenia.getComentario() %>”</p>
                            <p class="fecha-reseña">
                                Publicada por <%= resenia.getCliente().getNombre() %> el: 
                                <%= resenia.getFechaHora().toLocalDate() %>
                            </p>
                        </div>

                        <div class="estrellas">
                            <% 
                                int calificacion = resenia.getCalificacion();
                                for (int i = 1; i <= 5; i++) {
                                    if (i <= calificacion) {
                            %>
                                    <i class="fa-solid fa-star"></i>
                            <%
                                    } else {
                            %>
                                    <i class="fa-regular fa-star"></i>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>
                
                <%
                        } // Fin del for
                    } else {
                %>
                <p class="sin-registros">No se encontraron reseñas en la base de datos.</p>
                <%
                    } 
                %>

            </div>
        </main>
        
        <%@include file="partials/footer.jspf" %>
    </body>
</html>