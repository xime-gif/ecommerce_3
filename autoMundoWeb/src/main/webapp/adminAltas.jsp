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

                                    <option value="<c:out value='${cat.id}'/>"><c:out value='${cat.nombre}'/></option>
                                </c:forEach>
                            </select>

                            <label>Modelo</label>
                            <select name="modelo" required>
                                <option value="">Seleccione un modelo</option>
                                <c:forEach var="modelo" items="${modelos}">

                                    <option value="<c:out value='${modelo.id}'/>"><c:out value='${modelo.nombre}'/></option>
                                </c:forEach>
                            </select>


                            <label>Imagen Principal</label>
                            <div class="file-selection-group">

                                <input type="hidden" name="imagen" id="selectedImageURL" required>


                                <input type="text" id="display-selectedImageURL" placeholder="Ruta de la imagen seleccionada" readonly required>

                                <button type="button" class="btn-secundario btn-select-file" 
                                        data-field-target="selectedImageURL" 
                                        data-type="vehicle">Seleccionar</button>
                            </div>


                        </div>

                        <div class="form-col form-col-right">

                            <h3>Características y Equipamiento</h3>
                            <p>Ingresa la descripción y selecciona la ruta del icono (opcional).</p>


                            <c:forEach begin="1" end="6" var="i">
                                <label>Característica <c:out value="${i}"/></label>
                                <div class="feature-row">
                                    <input type="text" name="desc<c:out value='${i}'/>" placeholder="Descripción">


                                    <input type="hidden" name="icon<c:out value='${i}'/>" id="icon<c:out value='${i}'/>URL">


                                    <input type="text" id="display-icon<c:out value='${i}'/>URL" placeholder="Ruta Icono" readonly>


                                    <button type="button" class="btn-secundario btn-icon-select btn-select-file" 
                                            data-field-target="icon<c:out value='${i}'/>URL" 
                                            data-type="icon">Seleccionar</button>
                                </div>
                            </c:forEach>

                        </div>
                    </div>


                    <button class="btn-primario" type="submit">Guardar Auto</button>

                </form>

            </section>
        </main>


        <div id="imageSelectorModal" class="modal-selector">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 id="modalTitle">Seleccionar Archivo</h2>
                    <span class="close-button" id="closeModal">&times;</span>
                </div>
                <div class="modal-body">

                    <button id="toggleViewButton" class="btn-secundario">Cambiar a Iconos</button>


                    <div id="vehicleImagesGrid" class="image-selector-grid modal-grid">
                        <c:forEach var="imgUrl" items="${imagenesDisponibles}">
                            <div class="image-option modal-img-option" data-url="<c:out value='${imgUrl}'/>" data-type="vehicle" title="<c:out value='${imgUrl}'/>">
                                <img src="<c:url value='${imgUrl}'/>" alt="<c:out value='Imagen ${imgUrl}'/>">
                            </div>
                        </c:forEach>
                    </div>


                    <div id="iconImagesGrid" class="image-selector-grid modal-grid" style="display: none;">
                        <c:forEach var="iconUrl" items="${iconosDisponibles}">
                            <div class="image-option modal-img-option" data-url="<c:out value='${iconUrl}'/>" data-type="icon" title="<c:out value='${iconUrl}'/>">
                                <img src="<c:url value='${iconUrl}'/>" alt="<c:out value='Icono ${iconUrl}'/>">
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>



        <%@include file="partials/footer.jspf" %>


        <script src="scripts/adminAltas.js"></script>

    </body>
</html>