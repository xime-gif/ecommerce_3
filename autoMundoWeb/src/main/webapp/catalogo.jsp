<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutoZone - Catálogo</title>
        <link rel="stylesheet" href="estilos/catalogo.css">
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
    </head>
    <body>
        
        <%@include file="partials/header.jspf" %>

<main>
        <div class="catalogo-container">
            
            <h1 class="titulo-catalogo">Catálogo</h1>
            
            <div class="buscador">
                <input type="text">
            </div>
            
            <h2 class="subtitulo-catalogo">Precios</h2>
            
            <div class="precios">
                <span>Entre</span>
                <input type="text" class="precio">
                <span>y</span>
                <input type="text" class="precio">
                <button class="boton-buscar">Buscar</button>
            </div>
            
            <div class="categorias">
                <a>Autos</a>
                <a>Pickups</a>
                <a>Vans & Comerciales</a>
                <a>Deportivos</a>
                <a>Eléctricos</a>
            </div>
            
            <hr class="divider">
            
            <div class="car-grid">
            
                <div class="car-card">
                    <div class="car-info">
                        <h3>Chevrolet Groove</h3>
                        <a class="car-link">Más información</a>
                        <p class="car-price">$20,000</p>
                    </div>
                </div>

                <div class="car-card">
                    <div class="car-info">
                        <h3>Chevrolet Captiva</h3>
                        <a class="car-link">Más información</a>
                        <p class="car-price">$105,000</p>
                    </div>
                </div>

                <div class="car-card">
                    <div class="car-info">
                        <h3>Chevrolet Aveo Sedán</h3>
                        <a class="car-link">Más información</a>
                        <p class="car-price">$35,000</p>
                    </div>
                </div>
            </div>
        </div>
        </main>
        <%@include file="partials/footer.jspf" %>
        
    </body>
</html>
