<%-- 
    Document   : adminAltas
    Created on : 26 nov 2025, 5:56:57 p.m.
    Author     : javie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <form class="form-alta">

            <label>Nombre del Auto</label>
            <input type="text" placeholder="Ejemplo: Chevrolet Aveo Sedán">

            <label>Precio</label>
            <input type="number" placeholder="$">

            <label>Categoría</label>
            <select>
                <option>Autos</option>
                <option>Pickups</option>
                <option>Vans & Comerciales</option>
                <option>Deportivos</option>
                <option>Eléctricos</option>
            </select>

            <label>Descripción</label>
            <textarea rows="4" placeholder="Características del auto..."></textarea>

            <label>URL de Imagen</label>
            <input type="text" placeholder="imagenes/aveo.jpg">

            <button class="btn-primario">Agregar Auto</button>

        </form>

    </section>
</main>

<%@include file="partials/footer.jspf" %>

</body>
</html>