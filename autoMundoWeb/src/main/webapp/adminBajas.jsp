<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Administración - Baja de Autos</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/admin.css">
    </head>
        <body>

        <%@include file="partials/headerAdmin.jspf" %>

        <main>
            <section class="admin-contenido">


                <h1>Dar de Baja Autos</h1>

                <c:if test="${param.msg == 'bajaOk'}">
                    <div class="alert success">Vehículo eliminado correctamente.</div>
                </c:if>
                <c:if test="${param.msg == 'bajaFail'}">
                    <div class="alert error">No se pudo eliminar el vehículo.</div>
                </c:if>

                <div class="car-grid">
                    <c:forEach var="v" items="${vehiculos}">
                        <div class="car-card">
                            <c:choose>
                                <c:when test="${not empty v.imagenes}">

                                    <c:set var="imgUrl" value="${fn:replace(v.imagenes[0].url, '//', '/')}" />

                                    <c:if test="${fn:startsWith(imgUrl, '/')}">
                                        <c:set var="imgUrl" value="${fn:substringAfter(imgUrl, '/')}" />
                                    </c:if>

                                    <img src="${pageContext.request.contextPath}/${imgUrl}"
                                         class="car-img"
                                         alt="${v.nombre}">

                                </c:when>

                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/imagenes/default_car.png"
                                         class="car-img"
                                         alt="${v.nombre}">
                                </c:otherwise>
                            </c:choose>

                            <div class="car-info">
                                <h3>${v.nombre}</h3>
                                <p class="car-price">$${v.precio}</p>
                                <p class="car-stock">Disponibles: ${v.existencias}</p> <!-- NUEVO: muestra existencias -->

                                <button 
                                    type="button" 
                                    class="btn-eliminar js-delete-vehicle" 
                                    data-id="${v.id}" 
                                    data-nombre="${v.nombre}">
                                    Eliminar
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </main>
        <script src="scripts/adminBajas.js"></script>

        <%@include file="partials/footer.jspf" %>
    </body>
</html>