<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <div class="pedidos-wrapper">

            <h1 class="titulo-historial">Reseñas</h1>

         
            <div class="pedido-box">
                <div class="contenido">

                    <img src="imagenes/aveo.jpg" class="img-producto" alt="Aveo">

                    <div class="info">
                        <h2>Chevrolet Aveo Sedán</h2>
                        <p class="comentario">“Muy buen carro, económico y confiable.”</p>
                    </div>

                    <div class="estrellas">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                    </div>

                </div>
            </div>

       
            <div class="pedido-box">
                <div class="contenido">

                    <img src="imagenes/onix.jpg" class="img-producto" alt="Onix">

                    <div class="info">
                        <h2>Chevrolet Onix</h2>
                        <p class="comentario">“Excelente rendimiento, arrancó en clima frío sin problemas.”</p>
                    </div>

                    <div class="estrellas">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-regular fa-star"></i>
                    </div>

                </div>
            </div>

        </div>

        <%@include file="partials/footer.jspf" %>

    </body>
</html>
