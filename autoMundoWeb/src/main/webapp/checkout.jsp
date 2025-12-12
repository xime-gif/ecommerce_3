<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutoZone - Checkout</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/checkout.css">
    </head>
    <body>

        <%@include file="partials/header.jspf" %>
        <main>
            <div class="contenedor-principal">

                <form action="procesarPedido" method="POST" class="form-checkout">

                    <div class="panel-checkout">

                        <section class="seccion">
                            <h2>Dirección de envío</h2>

                            <c:if test="${empty sessionScope.usuarioActual.direcciones}">
                                <div class="mensaje-alerta">
                                    <p>No tienes direcciones guardadas.</p>
                                </div>
                            </c:if>

                            <c:forEach items="${sessionScope.usuarioActual.direcciones}" var="dir" varStatus="status">
                                <label class="direccion-label">
                                    <div class="direccion ${status.first ? 'activa' : ''}">
                                        <div class="direccion-titulo">
                                            <input type="radio" name="idDireccion" value="${dir.id}" ${status.first ? 'checked' : ''} required>
                                            ${dir.nombre}
                                        </div>
                                        <div class="direccion-detalle">
                                            ${dir.calle} #${dir.numero}, Col. ${dir.colonia}<br>
                                            ${dir.ciudad}, ${dir.codigoPostal}
                                        </div>
                                    </div>
                                </label>
                            </c:forEach>

                            <a href="direccion.jsp" style="text-decoration: none;">
                                <div class="agregar-nueva">
                                    + Agregar Nueva Dirección
                                </div>
                            </a>
                        </section>

                        <hr class="separador-seccion">

                        <section class="seccion">
                            <h2>Método de pago</h2>

                            <div class="formulario-tarjeta">
                                <label>Número de Tarjeta</label>
                                <input type="text" name="numeroTarjeta" placeholder="1234 5678 9012 3456" maxlength="19" required>

                                <div class="fila-doble">
                                    <div>
                                        <label>Fecha de Expiración</label>
                                        <input type="text" name="fechaExpiracion" placeholder="MM/AA" required>
                                    </div>
                                    <div>
                                        <label>CVV</label>
                                        <input type="text" name="cvv" placeholder="123" maxlength="4" required>
                                    </div>
                                </div>

                                <label>Nombre del Titular</label>
                                <input type="text" name="titular" placeholder="Juan Pérez López" required>
                            </div>
                        </section>

                    </div>

                    <div class="panel-resumen">
                        <div class="resumen-contenido">
                            <div class="resumen-texto">Subtotal (${sessionScope.carrito.items.size()} productos):</div>

                            <div class="resumen-total">
                                <fmt:setLocale value="es_MX"/>
                                <fmt:formatNumber value="${sessionScope.carrito.total}" type="currency"/>
                            </div>

                            <button type="submit" class="boton-pagar">Pagar</button>
                        </div>
                    </div>

                </form> </div>
        </main>
        <%@include file="partials/footer.jspf" %>

    </body>
</html>