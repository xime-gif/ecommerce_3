<%@page import="modelos.Resenia"%>
<%@page import="modelos.ImagenVehiculo"%>
<%@page import="daos.ReseniaDAO"%>
<%@page import="daos.ImagenVehiculoDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ReseniaDAO reseniaDAO = new ReseniaDAO();
    ImagenVehiculoDAO imagenVehiculoDAO = new ImagenVehiculoDAO();
    List<Resenia> resenias = reseniaDAO.buscarTodas();
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
                    if (resenias != null && !resenias.isEmpty()) {
                        for (Resenia resenia : resenias) {
                            String nombreVehiculo = resenia.getVehiculo().getNombre();
                            Long idVehiculo = resenia.getVehiculo().getId();
                            ImagenVehiculo imgPrincipal = imagenVehiculoDAO.buscarPrincipal(idVehiculo);
                            String rutaImagen = (imgPrincipal != null) ? imgPrincipal.getUrl() : "imagenes/default.jpg";
                %>

                <div class="pedido-box">
                    <div class="contenido">

                        <img src="<%= rutaImagen%>" class="img-producto" alt="<%= nombreVehiculo%>">

                        <div class="info">
                            <h2><%= nombreVehiculo%></h2> 
                            <p class="comentario">“<%= resenia.getComentario()%>”</p>
                            <p class="fecha-reseña">
                                Publicada por <%= resenia.getCliente().getNombre()%> el: 
                                <%= resenia.getFechaHora().toLocalDate()%>
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
                    }
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
