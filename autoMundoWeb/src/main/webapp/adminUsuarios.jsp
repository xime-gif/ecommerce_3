<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <section class="admin-contenido">

            <h1>Administración de Usuarios</h1>

            <div class="estadisticas">
                <span>Total usuarios: 24</span>
                <span>Activos: 22</span>
                <span>Inactivos: 2</span>
            </div>

            <div class="filtros">
                <input type="text" placeholder="Buscar usuario por nombre o correo...">
                <button class="btn-buscar">Buscar</button>

                <select>
                    <option>Rol: Todos</option>
                    <option>Cliente</option>
                    <option>Administrador</option>
                </select>

                <select>
                    <option>Estado: Todos</option>
                    <option>Activos</option>
                    <option>Inactivos</option>
                </select>
            </div>

            <table class="tabla-usuarios">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Rol</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Juan Pérez</td>
                        <td>juan@gmail.com</td>
                        <td>Cliente</td>
                        <td>Activo</td>
                        <td><button class="btn-editar" onclick="abrirModal()">Editar</button></td>
                    </tr>

                    <tr>
                        <td>2</td>
                        <td>Ana López</td>
                        <td>ana@correo.com</td>
                        <td>Administrador</td>
                        <td>Activo</td>
                        <td><button class="btn-editar" onclick="abrirModal()">Editar</button></td>
                    </tr>

                    <tr>
                        <td>3</td>
                        <td>Luis Gómez</td>
                        <td>luis@gmail.com</td>
                        <td>Cliente</td>
                        <td>Inactivo</td>
                        <td><button class="btn-editar" onclick="abrirModal()">Editar</button></td>
                    </tr>

                    <tr>
                        <td>3</td>
                        <td>Luis Gómez</td>
                        <td>luis@gmail.com</td>
                        <td>Cliente</td>
                        <td>Inactivo</td>
                        <td><button class="btn-editar" onclick="abrirModal()">Editar</button></td>
                    </tr>

                    <tr>
                        <td>3</td>
                        <td>Luis Gómez</td>
                        <td>luis@gmail.com</td>
                        <td>Cliente</td>
                        <td>Inactivo</td>
                        <td><button class="btn-editar" onclick="abrirModal()">Editar</button></td>
                    </tr>
                </tbody>
            </table>

        </section>

        <%@include file="partials/footer.jspf" %>

        <div id="modalUsuario" class="modal">
            <div class="modal-contenido">

                <span class="modal-cerrar" onclick="cerrarModal()">×</span>

                <h2>Usuario</h2>

                <label>Nombre</label>
                <input type="text" value="Juan Pérez">

                <label>Correo</label>
                <input type="text" value="juan@example.com">

                <button class="btn-primario">Editar</button>

                <div class="acciones-secundarias">
                    <button class="btn-secundario">Bloquear</button>
                    <button class="btn-eliminar">Eliminar</button>
                </div>

            </div>
        </div>

        <script>
            function abrirModal() {
                document.getElementById("modalUsuario").style.display = "flex";
            }

            function cerrarModal() {
                document.getElementById("modalUsuario").style.display = "none";
            }
        </script>

    </body>
</html>
