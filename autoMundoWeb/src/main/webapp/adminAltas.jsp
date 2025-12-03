<%-- adminAltas.jsp --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración - Alta de Autos</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/admin.css">
    </head>
    <body>

        <%@include file="partials/headerAdmin.jspf" %>

        <main>
            <section class="admin-contenido">

                <h1>Registrar Nuevo Auto</h1>

                <form class="form-alta" action="admin?accion=alta" method="POST">

                    <div class="form-grid">

                        <div class="form-col">
                            <h3>Datos Generales</h3>

                            <label>Nombre del Auto</label>
                            <input type="text" name="nombre" placeholder="Ej: Chevrolet Aveo Sedán" required>

                            <label>Precio</label>
                            <input type="number" name="precio" placeholder="$" required>

                            <label>Categoría</label>
                            <select name="categoria" required>
                                <option value="">Seleccione una categoría</option>
                                <c:forEach var="cat" items="${categorias}">
                                    <option value="${cat.id}">${cat.nombre}</option>
                                </c:forEach>
                            </select>

                            <label>Modelo</label>
                            <select name="modelo" required>
                                <option value="">Seleccione un modelo</option>
                                <c:forEach var="modelo" items="${modelos}">
                                    <option value="${modelo.id}">${modelo.nombre}</option>
                                </c:forEach>
                            </select>

                            <label>URL de Imagen Principal</label>
                            <input type="text" name="imagen" placeholder="imagenes/aveo.jpg" required>
                        </div>

                        <div class="form-col form-col-right">
                            <h3>Características y Equipamiento</h3>
                            <p>Ingresa la descripción y la ruta del icono (opcional).</p>

                            <label>Característica 1</label>
                            <div class="feature-row">
                                <input type="text" name="desc1" placeholder="Descripción (Ej: Frenos ABS)">
                                <input type="text" name="icon1" placeholder="URL Icono">
                            </div>

                            <label>Característica 2</label>
                            <div class="feature-row">
                                <input type="text" name="desc2" placeholder="Descripción">
                                <input type="text" name="icon2" placeholder="URL Icono">
                            </div>

                            <label>Característica 3</label>
                            <div class="feature-row">
                                <input type="text" name="desc3" placeholder="Descripción">
                                <input type="text" name="icon3" placeholder="URL Icono">
                            </div>

                            <label>Característica 4</label>
                            <div class="feature-row">
                                <input type="text" name="desc4" placeholder="Descripción">
                                <input type="text" name="icon4" placeholder="URL Icono">
                            </div>

                            <label>Característica 5</label>
                            <div class="feature-row">
                                <input type="text" name="desc5" placeholder="Descripción">
                                <input type="text" name="icon5" placeholder="URL Icono">
                            </div>

                            <label>Característica 6</label>
                            <div class="feature-row">
                                <input type="text" name="desc6" placeholder="Descripción">
                                <input type="text" name="icon6" placeholder="URL Icono">
                            </div>
                        </div>
                    </div>


                    <button class="btn-primario" type="submit">Guardar Auto</button>

                </form>

            </section>
        </main>

        <%@include file="partials/footer.jspf" %>

    </body>
</html>