<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AutoZone - Crear Cuenta</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/estiloForms.css">
    </head>
    <body>

        <%@include file="partials/header.jspf" %>
<main>
        <section class="registro-section">

            <div class="registro-panel">
                <center><h1>Crear Cuenta</h1></center>

                <form class="formulario-registro">
                    <label for="nombreCompleto">Nombre Completo</label>
                    <input type="text" id="nombreCompleto" name="nombreCompleto" required>

                    <label for="correo">Correo</label>
                    <input type="email" id="correo" name="correo" required>

                    <label for="contrasenia">Contraseña</label>
                    <input type="password" id="contrasenia" name="contrasenia" required>

                    <label for="confirmarContrasenia">Confirmar Contraseña</label>
                    <input type="password" id="confirmarContrasenia" name="confirmarContrasenia" required>

                    <div class="contenedor-boton-registrar">
                        <center><button type="submit" class="btn-form btn-primario">Registrarme</button></center>
                    </div>
                </form>

                <center>
                <p class="texto-login">
                    ¿Ya tienes cuenta? <a href="inicioSesion.jsp">Inicia sesión</a>
                </p>
                </center>
            </div>

        </section>
</main>
        <%@include file="partials/footer.jspf" %>

    </body>
</html>
