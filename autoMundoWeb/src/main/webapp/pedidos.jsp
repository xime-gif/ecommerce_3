<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List"%>
<%@page import="modelos.Pedido"%>
<%@page import="modelos.DetallePedido"%>
<%@page import="modelos.Usuario"%>

<%
    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
    String mensaje = (String) session.getAttribute("mensaje");
    String error = (String) session.getAttribute("error");
    session.removeAttribute("mensaje");
    session.removeAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Pedidos - AutoZone</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/pedidos.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>

    <body>
        <%@include file="partials/header.jspf" %>

        <main>
            <div class="pedidos-wrapper">

                <h1 class="titulo-historial">Historial de pedidos</h1>

                <% if (mensaje != null) {%>
                <div class="mensaje-exito"><%= mensaje%></div>
                <% } %>
                <% if (error != null) {%>
                <div class="mensaje-error"><%= error%></div>
                <% }%>

                <c:choose>
                    <c:when test="${empty pedidos}">
                        <p class="sin-registros">Aún no tienes pedidos registrados.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="pedido" items="${pedidos}">
                            <div class="pedido-box">

                                <div class="pedido-info-header">
                                    <h3>Pedido # ${pedido.numeroPedido}</h3>
                                    <p>Fecha: ${pedido.fechaCompra}</p>
                                    <p>Estado: <strong class="estado-${pedido.estado}">${pedido.estado}</strong></p>
                                    <p>Total: **$${pedido.totalPagado}**</p>
                                </div>
                                <hr>

                                <c:forEach var="detalle" items="${pedido.detalles}">
                                    <div class="contenido">

                                        <c:set var="imagenUrl" value="imagenes/default.jpg"/>
                                        <c:if test="${not empty detalle.vehiculo.imagenes}">
                                            <c:set var="imagenUrl" value="${detalle.vehiculo.imagenes[0].url}"/>
                                        </c:if>
                                        <img src="${imagenUrl}" class="img-producto" alt="${detalle.vehiculo.nombre}">

                                        <div class="info">
                                            <h2>${detalle.vehiculo.nombre}</h2>
                                            <p>Cantidad: ${detalle.cantidad}</p>

                                            <div class="acciones-info">

                                                <form action="pedidos" method="POST" class="form-resenia">
                                                    <input type="hidden" name="accion" value="reseñar">
                                                    <input type="hidden" name="idDetallePedido" value="${detalle.id}">

                                                    <input type="number" id="calificacion_${detalle.id}" name="calificacion" 
                                                           min="1" max="5" required placeholder="Calificación (1-5)" class="input-calificacion">

                                                    <textarea id="comentario_${detalle.id}" name="comentario" required placeholder="Tu comentario..." class="textarea-comentario"></textarea>

                                                    <button type="submit" class="btn-mini">Agregar reseña</button>
                                                </form>

                                                <a class="btn-mini">Visualizar compra</a>
                                            </div>
                                        </div>

                                        <div class="estrellas">
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                            <i class="fa-regular fa-star"></i>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </div>
        </main>

        <%@include file="partials/footer.jspf" %>

        <script src="https://kit.fontawesome.com/640f0c03df.js" crossorigin="anonymous"></script> 
    </body>
</html>
