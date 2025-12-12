<%-- 
    Document   : direccion
    Created on : 11 dic 2025, 5:23:28 p.m.
    Author     : Jp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AutoMundo - Nueva Dirección</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/estiloForms.css">
        <link rel="stylesheet" href="estilos/nuevaDireccion.css">
    </head>
    <body>
        <%@include file="partials/header.jspf" %>

        <main class="registro-section">
            <div class="registro-panel">
                <h1>Nueva Dirección</h1>

                <form action="guardarDireccion" method="POST" class="formulario-registro">

                    <label>Nombre de la dirección (Ej. Casa, Oficina)</label>
                    <input type="text" name="nombre" placeholder="Ej. Casa de playa" required>

                    <div class="fila-doble">
                        <div>
                            <label>Calle</label>
                            <input type="text" name="calle" placeholder="Calle Principal" required>
                        </div>
                        <div>
                            <label>Número</label>
                            <input type="text" name="numero" placeholder="123 Ext / 4 Int" required>
                        </div>
                    </div>

                    <div class="fila-doble">
                        <div>
                            <label>Colonia</label>
                            <input type="text" name="colonia" placeholder="Col. Centro" required>
                        </div>
                        <div>
                            <label>Código Postal</label>
                            <input type="text" name="codigoPostal" placeholder="85000" maxlength="10" required>
                        </div>
                    </div>

                    <div class="fila-triple">
                        <div>
                            <label>Ciudad</label>
                            <input type="text" name="ciudad" required>
                        </div>
                        <div>
                            <label>Estado</label>
                            <input type="text" name="estado" required>
                        </div>
                        <div>
                            <label>País</label>
                            <input type="text" name="pais" value="México" required>
                        </div>
                    </div>

                    <div class="contenedor-botones-login">
                        <button type="submit" class="btn-form btn-primario">Guardar Dirección</button>
                        <button type="button" class="btn-form btn-secundario" onclick="history.back()">Cancelar</button>
                    </div>

                </form>
            </div>
        </main>

        <%@include file="partials/footer.jspf" %>
    </body>
</html>
