<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="modelos.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutoZone - Carrito de Compras</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/carritoCompras.css"/>
    </head>
    <body>
        <%@include file="partials/header.jspf" %>
        <%
            CarritoDeCompras carrito = (CarritoDeCompras) session.getAttribute("carrito");

            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);

            double total = 0;
            int cantidadItems = 0;

            if (carrito != null) {
                total = carrito.getTotal();
                cantidadItems = carrito.getItems().size();
            }
        %>

        <main>
            <div class="contenedor-principal">
                <div class="panel-productos">
                    <div class="panel-productos-header">
                        <div class="panel-productos-titulo">
                            <h1>Carrito</h1>
                            <span style="font-size: 0.9em; color: #555; margin-left: 10px;">
                                (<%= cantidadItems%> productos)
                            </span>
                            <span class="link-deseleccionar">Deseleccionar todo</span>
                        </div>
                    </div>
                    <hr class="separador">

                    <%
                        if (carrito != null && !carrito.getItems().isEmpty()) {
                            for (ItemCarrito item : carrito.getItems()) {
                                String urlImagen = "imagenes/no-image.png";
                                if (item.getVehiculo().getImagenes() != null && !item.getVehiculo().getImagenes().isEmpty()) {
                                    urlImagen = item.getVehiculo().getImagenes().get(0).getUrl();
                                }
                    %>
                    <div class="producto-fila">
                        <img src="<%= urlImagen%>" alt="<%= item.getVehiculo().getNombre()%>" class="producto-img">
                        <div class="producto-detalles">
                            <div class="producto-nombre"><%= item.getVehiculo().getNombre()%></div>

                            <div class="flex-spacer"></div>
                            <div class="producto-acciones">
                                <div class="cantidad-selector">
                                    <button class="btn-qty" onclick="location.href = 'EliminarItemServlet?id=<%= item.getVehiculo().getId()%>'"><i class="fas fa-trash"></i></button>

                                    <span class="cantidad-numero"><%= item.getCantidad()%></span>

                                    <button class="btn-qty"><i class="fas fa-plus"></i></button>
                                </div>
                                <div class="producto-precio-individual">
                                    <%= formatoMoneda.format(item.getSubtotal())%>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr class="separador alt">
                    <%
                        }
                    } else {
                    %>
                    <div class="mensaje-vacio">
                        <p>Tu carrito está vacío.</p>
                        <a href="catalogo.jsp">Ir al catálogo</a>
                    </div>
                    <% } %>

                    <% if (carrito != null && !carrito.getItems().isEmpty()) {%>
                    <div class="subtotal-inferior">
                        Subtotal (<%= cantidadItems%> productos): <strong><%= formatoMoneda.format(total)%></strong>
                    </div>
                    <% }%>
                </div>

                <div class="panel-resumen">
                    <div class="resumen-contenido">
                        <div class="resumen-texto">Subtotal (<%= cantidadItems%> productos):</div>
                        <div class="resumen-total"><%= formatoMoneda.format(total)%></div>

                        <% if (carrito != null && !carrito.getItems().isEmpty()) { %>
                        <button onclick="location.href = 'checkout.jsp'" class="boton-pagar">Pagar</button>
                        <% } else { %>
                        <button class="boton-pagar" disabled style="background-color: #ccc; cursor: not-allowed;">Pagar</button>
                        <% }%>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="partials/footer.jspf" %>
    </body>
</html>
