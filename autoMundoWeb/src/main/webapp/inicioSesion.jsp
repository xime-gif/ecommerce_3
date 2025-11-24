<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AutoZone - Inicio Sesión</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/estiloForms.css">
    </head>
    <body>

        <%@include file="partials/header.jspf" %>

        <section class="login-section">

            <div class="login-imagen">
                <img src="imagenes/carroInicio.jpeg" alt="Auto de inicio">
            </div>

            <div class="login-panel">
                <h1>Inicio Sesión</h1>

                <form class="formulario-login">
                    <label for="correo">Correo</label>
                    <input type="email" id="correo" name="correo" required>

                    <label for="contrasenia">Contraseña</label>
                    <input type="password" id="contrasenia" name="contrasenia" required>

                    <div class="contenedor-botones-login">
                        <button type="submit" class="btn-form btn-primario">Ingresar</button>
                        <button type="reset" class="btn-form btn-secundario">Cancelar</button>
                    </div>
                </form>

                <a href="registro.jsp" class="link-crear-cuenta">
                    Crear Cuenta
                </a>
            </div>

        </section>

        <%@include file="partials/footer.jspf" %>

    </body>
</html>
