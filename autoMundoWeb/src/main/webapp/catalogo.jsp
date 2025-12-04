<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutoZone - Catálogo</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/catalogo.css">
    </head>
    <body>
        
        <%@include file="partials/header.jspf" %>

<main>
        <div class="catalogo-container">
            
            <h1 class="titulo-catalogo">Catálogo</h1>
            
            <!-- ===================== BUSCADOR ===================== -->
            <form method="GET" action="catalogo">
                
                <div class="buscador">
                    <input type="text" name="q" placeholder="Buscar modelo..." value="${param.q}">
                </div>
                
                <!-- ===================== FILTRO DE PRECIOS ===================== -->
                <h2 class="subtitulo-catalogo">Precios</h2>

                <div class="precios">
                    <span>Entre</span>
                    <input type="number" name="min" class="precio" value="${minSel}">
                    <span>y</span>
                    <input type="number" name="max" class="precio" value="${maxSel}">
                    <button class="boton-buscar">Buscar</button>
                </div>
                    
                <!-- ===================== CATEGORÍAS ===================== -->
                <div class="categorias">
                    <c:forEach var="c" items="${categorias}">
                        <a href="catalogo?categoria=${c.id}"
                           class="${categoriaSel == c.id.toString() ? 'active-cat' : ''}">
                            ${c.nombre}
                        </a>
                    </c:forEach>
                </div>
                
                <!-- ===================== MARCAS ===================== -->
                <h2 class="subtitulo-catalogo">Marcas</h2>

                <select name="marca" class="selector-marcas" onchange="this.form.submit()">
                    <option value="">Todas las marcas</option>

                    <c:forEach var="m" items="${marcas}">
                        <option value="${m.nombre}"
                                ${m.nombre == marcaSel ? 'selected' : ''}>
                            ${m.nombre}
                        </option>
                    </c:forEach>
                </select>
                
            </form>
            
            <hr class="divider">
            
            <!-- ===================== VEHÍCULOS ===================== -->
            
            <div class="car-grid">
                
                <c:forEach var="v" items="${vehiculos}">
                    
                    <div class="car-card">
                        <!-- Imagen principal del vehículo -->
                        <c:if test="${not empty v.imagenes}">
                            <img class="car-img" src="${v.imagenes[0].ruta}" alt="${v.nombre}">
                        </c:if>

                        <div class="car-info">
                            <h3>${v.modelo.nombre}</h3>

                            <p class="car-price">$${v.precio}</p>

                            <a class="car-link" href="detalleVehiculo?id=${v.id}">
                                Más información
                            </a>
                        </div>
                    </div>
                    
                </c:forEach>
                
                <c:if test="${empty vehiculos}">
                    <p>No hay vehículos disponibles.</p>
                </c:if>

            </div>
        </div>
        </main>
        <%@include file="partials/footer.jspf" %>
        
    </body>
</html>
