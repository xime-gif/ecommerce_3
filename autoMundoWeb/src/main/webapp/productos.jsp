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
                    <c:if test="${not empty vehiculo.imagenes}">
                        <c:forEach items="${vehiculo.imagenes}" var="img">
                            <img src="${pageContext.request.contextPath}/${img.url}" 
                                 alt="${vehiculo.nombre}" 
                                 class="car-img" 
                                 style="margin-bottom: 10px; display: block;"> 
                        </c:forEach>
                    </c:if>

                    <%-- Si NO hay imágenes, mostramos la default --%>
                    <c:if test="${empty vehiculo.imagenes}">
                        <img src="${pageContext.request.contextPath}/imagenes/default_car.jpg" alt="Sin imagen">
                    </c:if>
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
                    <form action="${pageContext.request.contextPath}/agregarItemCarrito" method="GET">
                        <input type="hidden" name="id" value="${vehiculo.id}">
                        <input type="hidden" name="cantidad" value="1">
                        <button type="submit" name="accion" value="agregar" class="btn-accion">AÑADIR AL CARRITO</button>
                    </form>
                </div>

            </section>
        </main>        
        <%@include file="partials/footer.jspf" %>

    </body>
</html>