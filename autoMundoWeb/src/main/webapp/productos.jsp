<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %> <%-- LIBRERÍA OBLIGATORIA --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- Título dinámico --%>
        <title>AutoZone - ${vehiculo.nombre}</title>
        
        <%-- Rutas corregidas para CSS --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/estiloGeneral.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/producto.css">
    </head>
    <body>
        
        <%@include file="partials/header.jspf" %>
        
        <main>
            <section class="producto-container">
                
                <%-- 1. IMAGEN DINÁMICA --%>
                <div class="imagen-detalle-contenedor">
                    <c:choose>
                        <%-- Si el vehículo tiene imágenes en la BD, muestra la primera --%>
                        <c:when test="${not empty vehiculo.imagenes}">
                            <img src="${pageContext.request.contextPath}/${vehiculo.imagenes[0].url}" 
                                 alt="${vehiculo.nombre}"
                                 onerror="this.src='${pageContext.request.contextPath}/imagenes/default_car.jpg'"> 
                        </c:when>
                        <%-- Si no tiene, muestra una por defecto --%>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/imagenes/default_car.jpg" alt="Sin imagen">
                        </c:otherwise>
                    </c:choose>
                </div>

                <%-- 2. INFORMACIÓN DINÁMICA --%>
                <div class="barra-info-producto">
                    <h1>${vehiculo.nombre}</h1>
                    <span class="precio-grande">$ ${vehiculo.precio}</span>
                </div>

                <%-- 3. CARACTERÍSTICAS DINÁMICAS (Bucle) --%>
                <div class="caracteristicas-grid">
                    
                    <%-- Recorre la lista de características guardadas en la BD --%>
                    <c:forEach var="car" items="${vehiculo.caracteristicas}">
                        <div class="feature-item">
                            <%-- Icono de la característica --%>
                            <img src="${pageContext.request.contextPath}/${car.rutaIcono}" 
                                 alt="Icono"
                                 onerror="this.src='${pageContext.request.contextPath}/imagenes/icon_Caracteristicas/default.png'">
                            
                            <%-- Descripción de la característica --%>
                            <p>${car.nombre}</p>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${empty vehiculo.caracteristicas}">
                        <p style="text-align: center; color: gray;">No hay características registradas para este vehículo.</p>
                    </c:if>
                </div>

                <%-- 4. BOTONES (Funcionales para el carrito) --%>
                <div class="barra-accion">
                    <form action="${pageContext.request.contextPath}/carrito" method="POST">
                        <input type="hidden" name="idVehiculo" value="${vehiculo.id}">
                        <button type="button" class="btn-accion">COMPRAR AHORA</button>
                        <button type="submit" name="accion" value="agregar" class="btn-accion">AÑADIR AL CARRITO</button>
                    </form>
                </div>

            </section>
        </main>        
        <%@include file="partials/footer.jspf" %>
        
    </body>
</html>