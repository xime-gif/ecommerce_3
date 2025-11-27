<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración - Baja de Autos</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/admin.css">
    </head>
    <body>

    <%@include file="partials/headerAdmin.jspf" %>

    <main>
        <section class="admin-contenido">

            <h1>Dar de Baja Autos</h1>

            <div class="car-grid">

                <div class="car-card">
                    <img src="imagenes/aveo.jpg" class="car-img" alt="Aveo">
                    <div class="car-info">
                        <h3>Chevrolet Aveo Sedán</h3>
                        <p class="car-price">$35,000</p>
                        <button class="btn-eliminar">Eliminar</button>
                    </div>
                </div>

                <div class="car-card">
                    <img src="imagenes/onix.jpg" class="car-img" alt="Groove">
                    <div class="car-info">
                        <h3>Chevrolet Groove</h3>
                        <p class="car-price">$20,000</p>
                        <button class="btn-eliminar">Eliminar</button>
                    </div>
                </div>

                <div class="car-card">
                    <img src="imagenes/carroInicio.jpeg" class="car-img" alt="Captiva">
                    <div class="car-info">
                        <h3>Chevrolet Captiva</h3>
                        <p class="car-price">$105,000</p>
                        <button class="btn-eliminar">Eliminar</button>
                    </div>
                </div>

            </div>

        </section>
    </main>

    <%@include file="partials/footer.jspf" %>

    </body>
</html>