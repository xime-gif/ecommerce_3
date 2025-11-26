<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutoZone - Chevrolet Aveo Sedán</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/producto.css">
    </head>
    <body>
        
        <%@include file="partials/header.jspf" %>

        <section class="producto-container">
            
            <div class="imagen-detalle-contenedor">
                <img src="imagenes/aveo.jpg" alt="Chevrolet Aveo Sedán">
            </div>

            <div class="barra-info-producto">
                <h1>Chevrolet Aveo Sedán</h1>
                <span class="precio-grande">$35,000</span>
            </div>

            <div class="caracteristicas-grid">
                
                <div class="feature-item">
                    <img src="imagenes/icon_Caracteristicas/icon_Caracteristica_1.avif" alt="Alarma">
                    <p>Alarma antirrobo.</p>
                </div>
                
                <div class="feature-item">
                    <img src="imagenes/icon_Caracteristicas/icon_Caracteristica_1.avif" alt="Cámara">
                    <p>Cámara de visión trasera.</p>
                </div>
                
                <div class="feature-item">
                    <img src="imagenes/icon_Caracteristicas/icon_Caracteristica_1.avif" alt="Estabilidad">
                    <p>Control de estabilidad StabiliTrak.</p>
                </div>

                <div class="feature-item">
                    <img src="imagenes/icon_Caracteristicas/icon_Caracteristica_1.avif" alt="Motor">
                    <p>Motor 1.5L de 4 cilindros con 98 HP, 105 lb-pie de torque.</p>
                </div>
                
                <div class="feature-item">
                    <img src="imagenes/icon_Caracteristicas/icon_Caracteristica_1.avif" alt="Transmisión">
                    <p>Transmisión manual de 6 velocidades y automática CVT.</p>
                </div>
                
                <div class="feature-item">
                    <img src="imagenes/icon_Caracteristicas/icon_Caracteristica_1.avif" alt="Suspensión">
                    <p>Dirección electroasistida y suspensión delantera tipo McPherson.</p>
                </div>
            </div>

            <div class="barra-accion">
                <button class="btn-accion">COMPRAR AHORA</button>
                <button class="btn-accion">AÑADIR AL CARRITO</button>
            </div>

        </section>
        
        <%@include file="partials/footer.jspf" %>
        
    </body>
</html>