<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <div class="panel-checkout">
            
            <section class="seccion">
                <h2>Dirección de envío</h2>
                
                <div class="direccion activa">
                    <div class="direccion-titulo">Casa</div>
                    <div class="direccion-detalle">
                        Miravalle 1015, Col. Valle Bonita<br>
                        Ciudad Obregón, 85021
                    </div>
                </div>

                <div class="direccion">
                    <div class="direccion-titulo">Oficina</div>
                    <div class="direccion-detalle">
                        Río de oro 2135, Col. Centro<br>
                        Ciudad Obregón, 85118
                    </div>
                </div>

                <div class="agregar-nueva">
                    + Agregar Nueva Dirección
                </div>
            </section>

            <hr class="separador-seccion">

            <section class="seccion">
                <h2>Método de pago</h2>

                <form class="formulario-tarjeta">
                    <label>Número de Tarjeta</label>
                    <input type="text" placeholder="1234 5678 9012 3456" maxlength="19">

                    <div class="fila-doble">
                        <div>
                            <label>Fecha de Expiración</label>
                            <input type="text" placeholder="MM/AA">
                        </div>
                        <div>
                            <label>CVV</label>
                            <input type="text" placeholder="123" maxlength="4">
                        </div>
                    </div>

                    <label>Nombre del Titular</label>
                    <input type="text" placeholder="Juan Pérez López">
                </form>
            </section>

        </div>

        <div class="panel-resumen">
            <div class="resumen-contenido">
                <div class="resumen-texto">Subtotal (1 producto):</div>
                <div class="resumen-total">$20,000</div>
                <button class="boton-pagar">Pagar</button>
            </div>
        </div>

    </div>
</main>
    <%@include file="partials/footer.jspf" %>

</body>
</html>