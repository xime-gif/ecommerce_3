<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Pedidos - AutoZone</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/pedidos.css">
    </head>

    <body>

        <%@include file="partials/header.jspf" %>
<main>
        <div class="pedidos-wrapper">

            <h1 class="titulo-historial">Historial de pedidos</h1>
            <div class="pedido-box">
                <div class="contenido">
                    <img src="imagenes/aveo.jpg" class="img-producto">

                    <div class="info">
                        <h2>Chevrolet Aveo Sedán</h2>

                        <div class="acciones-info">
                            <a class="btn-mini">Agregar reseña</a>
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
            </div>
            <div class="pedido-box">
                <div class="contenido">
                    <img src="imagenes/aveo.jpg" class="img-producto">

                    <div class="info">
                        <h2>Chevrolet Aveo Sedán</h2>

                        <div class="acciones-info">
                            <a class="btn-mini">Agregar reseña</a>
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
            </div>
            <div class="pedido-box">
                <div class="contenido">
                    <img src="imagenes/aveo.jpg" class="img-producto">

                    <div class="info">
                        <h2>Chevrolet Aveo Sedán</h2>

                        <div class="acciones-info">
                            <a class="btn-mini">Agregar reseña</a>
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
            </div>

        </div>
</main>
        <%@include file="partials/footer.jspf" %>

    </body>
</html>
