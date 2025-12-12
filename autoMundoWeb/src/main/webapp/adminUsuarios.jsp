<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Administración de Usuarios</title>
        <link rel="stylesheet" href="estilos/estiloGeneral.css">
        <link rel="stylesheet" href="estilos/admin.css">
    </head>
    <body>

        <%@include file="partials/headerAdmin.jspf" %>

<main>
        <section class="admin-contenido">

            <h1>Administración de Usuarios</h1>

            <c:if test="${not empty param.msg}">
                <div class="mensaje-exito">
                    <c:choose>
                        <c:when test="${param.msg == 'editado'}">Usuario editado correctamente.</c:when>
                        <c:when test="${param.msg == 'eliminado'}">Usuario eliminado correctamente.</c:when>
                        <c:when test="${param.msg == 'activado'}">Usuario activado correctamente.</c:when>
                        <c:when test="${param.msg == 'bloqueado'}">Usuario bloqueado correctamente.</c:when>
                    </c:choose>
                </div>
            </c:if>

            <c:if test="${not empty param.error}">
                <div class="mensaje-error">
                    <c:choose>
                        <c:when test="${param.error == 'noEncontrado'}">Usuario no encontrado.</c:when>
                        <c:when test="${param.error == 'edicion'}">Error al editar el usuario.</c:when>
                        <c:when test="${param.error == 'eliminar'}">Error al eliminar el usuario.</c:when>
                        <c:when test="${param.error == 'estado'}">Error al cambiar el estado del usuario.</c:when>
                    </c:choose>
                </div>
            </c:if>

            <div class="estadisticas">
                <span>Total usuarios: ${totalUsuarios}</span>
                <span>Activos: ${usuariosActivos}</span>
                <span>Inactivos: ${usuariosInactivos}</span>
            </div>

            <form action="adminUsuarios" method="POST" class="filtros">
                <input type="hidden" name="accion" value="buscar">
                <input type="text" name="termino" placeholder="Buscar usuario por nombre o correo..."
                       value="${terminoBusqueda}">
                <button type="submit" class="btn-buscar">Buscar</button>

                <select name="estado">
                    <option value="todos" ${estadoSeleccionado == 'todos' || empty estadoSeleccionado ? 'selected' : ''}>Estado: Todos</option>
                    <option value="activos" ${estadoSeleccionado == 'activos' ? 'selected' : ''}>Activos</option>
                    <option value="inactivos" ${estadoSeleccionado == 'inactivos' ? 'selected' : ''}>Inactivos</option>
                </select>
            </form>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr data-id="${usuario.id}"
                            data-nombre="${usuario.nombre}"
                            data-correo="${usuario.correo}"
                            data-activo="${usuario.esActivo}">
                            <td>${usuario.id}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.correo}</td>
                            <td>${usuario.esActivo ? 'Activo' : 'Inactivo'}</td>
                            <td>
                                <button class="btn-editar" type="button">Editar</button>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty usuarios}">
                        <tr>
                            <td colspan="5" style="text-align: center;">No se encontraron usuarios.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>

        </section>
</main>
        <%@include file="partials/footer.jspf" %>

        <div id="modalUsuario" class="modal">
            <div class="modal-contenido">

                <span class="modal-cerrar" onclick="cerrarModal()">&times;</span>

                <h2>Editar Usuario</h2>

                <form id="formEditar" action="adminUsuarios" method="POST">
                    <input type="hidden" name="accion" value="editar">
                    <input type="hidden" id="modalUserId" name="id">

                    <label>Nombre</label>
                    <input type="text" id="modalNombre" name="nombre" required>

                    <label>Correo</label>
                    <input type="email" id="modalCorreo" name="correo" required>

                    <button type="submit" class="btn-primario">Guardar Cambios</button>
                </form>

                <div class="acciones-secundarias">
                    <form action="adminUsuarios" method="POST" style="display: inline;">
                        <input type="hidden" name="accion" value="cambiarEstado">
                        <input type="hidden" id="formBloquearId" name="id">
                        <button type="submit" id="btnBloquear" class="btn-secundario">Bloquear</button>
                    </form>

                    <form action="adminUsuarios" method="POST" style="display: inline;"
                          onsubmit="return confirm('¿Estás seguro de que deseas eliminar este usuario? Esta acción no se puede deshacer.');">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" id="formEliminarId" name="id">
                        <button type="submit" class="btn-eliminar">Eliminar</button>
                    </form>
                </div>

            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function() {
                var botonesEditar = document.querySelectorAll(".btn-editar");
                botonesEditar.forEach(function(boton) {
                    boton.addEventListener("click", function() {
                        var fila = this.closest("tr");
                        var id = fila.getAttribute("data-id");
                        var nombre = fila.getAttribute("data-nombre");
                        var correo = fila.getAttribute("data-correo");
                        var esActivo = fila.getAttribute("data-activo") === "true";
                        abrirModal(id, nombre, correo, esActivo);
                    });
                });
            });

            function abrirModal(id, nombre, correo, esActivo) {
                document.getElementById("modalUserId").value = id;
                document.getElementById("modalNombre").value = nombre;
                document.getElementById("modalCorreo").value = correo;
                document.getElementById("formBloquearId").value = id;
                document.getElementById("formEliminarId").value = id;

                var btnBloquear = document.getElementById("btnBloquear");
                if (esActivo) {
                    btnBloquear.textContent = "Bloquear";
                    btnBloquear.className = "btn-secundario";
                } else {
                    btnBloquear.textContent = "Activar";
                    btnBloquear.className = "btn-primario";
                }

                document.getElementById("modalUsuario").style.display = "flex";
            }

            function cerrarModal() {
                document.getElementById("modalUsuario").style.display = "none";
            }

            window.onclick = function(event) {
                var modal = document.getElementById("modalUsuario");
                if (event.target == modal) {
                    cerrarModal();
                }
            }
        </script>

    </body>
</html>
