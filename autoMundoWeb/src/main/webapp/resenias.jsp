<%@page import="modelos.Resenia"%>
<%@page import="modelos.ImagenVehiculo"%>
<%@page import="daos.ReseniaDAO"%>
<%@page import="daos.ImagenVehiculoDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // =====================================================================
    // LÓGICA DE BÚSQUEDA DE DATOS
    // =====================================================================
    
    // Instanciar los DAOs necesarios
    ReseniaDAO reseniaDAO = new ReseniaDAO();
    ImagenVehiculoDAO imagenVehiculoDAO = new ImagenVehiculoDAO();
    
    // Obtener TODAS las reseñas de la base de datos (usando el nuevo método)
    List<Resenia> resenias = reseniaDAO.buscarTodas();
    
    // NOTA: Si quisieras filtrar por el usuario logueado, deberías 
    // obtener el usuario de la sesión y usar un método de búsqueda por usuario.
    // Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
    // List<Resenia> resenias = reseniaDAO.buscarPorUsuario(usuarioLogueado.getId()); 
    
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mis Reseñas - AutoZone</title>
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
                    // 1. Verificar si hay reseñas para mostrar
                    if (resenias != null && !resenias.isEmpty()) { 
                        
                        // 2. Iterar sobre la lista de reseñas
                        for (Resenia resenia : resenias) {
                            
                            // 3. Obtener la información necesaria para cada reseña
                            String nombreVehiculo = resenia.getVehiculo().getNombre();
                            Long idVehiculo = resenia.getVehiculo().getId();
                            
                            // 4. Buscar la imagen principal del vehículo
                            // Si no hay imagen principal, usa una imagen por defecto.
                            ImagenVehiculo imgPrincipal = imagenVehiculoDAO.buscarPrincipal(idVehiculo);
                            String rutaImagen = (imgPrincipal != null) ? imgPrincipal.getUrl(): "imagenes/default.jpg";
                %>
                
                <div class="pedido-box">
                    <div class="contenido">

                        <img src="<%= rutaImagen %>" class="img-producto" alt="<%= nombreVehiculo %>">

                        <div class="info">
                            <h2><%= nombreVehiculo %></h2> 
                            <p class="comentario">“<%= resenia.getComentario() %>”</p>
                            <p class="fecha-reseña">
                                Publicada por <%= resenia.getCliente().getNombre() %> el: 
                                <%= resenia.getFechaHora().toLocalDate() %>
                            </p>
                        </div>

                        <div class="estrellas">
                            <% 
                                int calificacion = resenia.getCalificacion();
                                // Bucle para renderizar las estrellas
                                for (int i = 1; i <= 5; i++) {
                                    if (i <= calificacion) {
                            %>
                                    <i class="fa-solid fa-star"></i> <%-- Estrella Rellena --%>
                            <%
                                    } else {
                            %>
                                    <i class="fa-regular fa-star"></i> <%-- Estrella Vacía --%>
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
                    } // Fin del if/else
                %>

            </div>
        </main>
        
        <%@include file="partials/footer.jspf" %>

    </body>
</html>