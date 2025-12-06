<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AutoZone - Crear Cuenta</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/estiloForms.css">
        <link rel="stylesheet" href="estilos/registro.css">

    </head>

    <body>

        <%@include file="partials/header.jspf" %>

        <main>
            <section class="registro-section">
                <div class="registro-panel fade-in">

                    <center><h1>Crear Cuenta</h1></center>

                    <c:if test="${not empty error}">
                        <p class="error-msg show">${error}</p>
                    </c:if>

                    <c:if test="${not empty exito}">
                        <p class="exito-msg show">${exito}</p>
                    </c:if>

                    <form class="formulario-registro" action="registro" method="post">

                        <div class="campo">
                            <label>Nombre Completo</label>
                            <input type="text" id="nombreCompleto" name="nombreCompleto">
                        </div>

                        <div class="campo">
                            <label>Correo</label>
                            <input type="email" id="correo" name="correo">
                        </div>

                        <div class="campo">
                            <label>Contraseña</label>
                            <input type="password" id="contrasenia" name="contrasenia">
                        </div>

                        <div class="campo">
                            <label>Confirmar Contraseña</label>
                            <input type="password" id="confirmarContrasenia" name="confirmarContrasenia">
                        </div>

                        <p id="error-js" class="error-msg"></p>

                        <div class="contenedor-boton-registro">
                            <center><button type="submit" id="btn-registrar" class="btn-form btn-primario btn-anim">Registrarme</button></center>    
                        </div>
                    </form>

                    <center>
                        <p class="texto-login">
                            ¿Ya tienes cuenta?
                            <a href="inicioSesion.jsp" class="link-login">Inicia sesión</a>
                        </p>
                    </center>

                </div>
            </section>
        </main>

        <%@include file="partials/footer.jspf" %>

        <script src="scripts/registro.js"></script>
    </body>
</html>
