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
        <main>
            <div class="contenedor-principal">
                <div class="panel-productos">
                    <div class="panel-productos-header">
                        <div class="panel-productos-titulo">
                            <h1>Carrito</h1>
                            <span class="link-deseleccionar">Deseleccionar todo</span>
                        </div>
                    </div>
                    <hr class="separador">
                    
                    <div class="producto-fila">
                        <img src="https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=500&q=80" alt="Auto Chevrolet" class="producto-img">
                        <div class="producto-detalles">
                            <div class="producto-nombre">Chevrolet Groove</div>
                            <div class="flex-spacer"></div>
                            <div class="producto-acciones">
                                <div class="cantidad-selector">
                                    <button class="btn-qty"><i class="fas fa-trash"></i></button>
                                    <span class="cantidad-numero">1</span>
                                    <button class="btn-qty"><i class="fas fa-plus"></i></button>
                                </div>
                                <div class="producto-precio-individual">$20,000</div>
                            </div>
                        </div>
                    </div>
                    
                    <hr class="separador alt">
                    <div class="subtotal-inferior">
                        Subtotal (1 producto): <strong>$20,000</strong>
                    </div>
                </div>

                <div class="panel-resumen">
            <div class="resumen-contenido">
                <div class="resumen-texto">Subtotal (1 producto):</div>
                <div class="resumen-total">$20,000</div>
                <button onclick="location.href='checkout.jsp'" class="boton-pagar">Pagar</button>
            </div>
        </div>
            </div>
        </main>
        <%@include file="partials/footer.jspf" %>
    </body>
</html>
