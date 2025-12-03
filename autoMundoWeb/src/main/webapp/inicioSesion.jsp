<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AutoZone - Inicio Sesión</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/estiloForms.css">
    </head>
    <body>

        <main>
            <section class="login-section">

                <div class="login-imagen">
                    <img src="imagenes/carroInicio.jpeg" alt="Auto de inicio">
                </div>

                <div class="login-panel">
                    <h1>Inicio Sesión</h1>
                    <c:if test="${not empty error}">
                        <p class="error-msg">${error}</p>
                    </c:if>

                    <form class="formulario-login" action="inicioSesion" method="post">

                        <label for="correo">Correo</label>
                        <input type="email" id="correo" name="correo" required>

                        <label for="contrasena">Contraseña</label>
                        <input type="password" id="contrasena" name="contrasena" required>

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
        </main>

        <%@include file="partials/footer.jspf" %>

    </body>
</html>
