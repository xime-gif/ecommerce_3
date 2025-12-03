<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <input type="text" name="q">
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
                <a href="?categoria=Auto">Autos</a>
                <a href="?categoria=Pickup">Pickups</a>
                <a href="?categoria=Van">Vans & Comerciales</a>
                <a href="?categoria=Deportivo">Deportivos</a>
                <a href="?categoria=Electrico">Eléctricos</a>
            </div>
            
            <hr class="divider">
            
            <div class="car-grid">
                
                <c:forEach var="v" items="${listaVehiculos}">
                    
                    <div class="car-card">
                    <div class="car-info">
                        
                        <h3>${v.modelo}</h3>
                        
                        <a class="car-link" href="detalleVehiculo?id=${v.id}">Más información</a>
                        <p class="car-price">$${v.precio}</p>
                    </div>
                </div>
                    
                </c:forEach>
                
                <c:if test="${empty listaVehiculos}">
                    <p>No hay vehículos disponibles.</p>
                </c:if>

            </div>
        </div>
        </main>
        <%@include file="partials/footer.jspf" %>
        
    </body>
</html>
