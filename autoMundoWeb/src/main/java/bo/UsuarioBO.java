/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.UsuarioDAO;
import java.util.List;
import modelos.RolUsuario;
import modelos.Usuario;
import seguridad.Encriptador;

/**
 *
 * @author multaslokas33
 */
public class UsuarioBO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Recibe la contraseña en texto plano, la hashea y delega al DAO.
     *
     * @param correo
     * @param contrasenaPlano
     * @return
     */
    public Usuario iniciarSesion(String correo, String contrasenaPlano) {

        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);

        if (usuario == null) {
            return null;
        }

        if (!Encriptador.verificarPassword(contrasenaPlano, usuario.getContrasena())) {
            return null;
        }

        return usuario;
    }

    /**
     * Valida datos, verifica que no exista el correo y guarda la contraseña
     * hasheada.
     *
     * @param nombre
     * @param correo
     * @param contrasena
     * @param confirmar
     * @return
     */
    public String registrarCliente(String nombre, String correo, String contrasena, String confirmar) {

        if (nombre == null || nombre.isBlank() || nombre.length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }

        if (correo == null || correo.isBlank()) {
            return "Debes ingresar un correo.";
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "El correo no tiene un formato válido.";
        }

        if (usuarioDAO.buscarPorCorreo(correo) != null) {
            return "Ya existe una cuenta con ese correo.";
        }

        if (contrasena == null || contrasena.length() < 8) {
            return "La contraseña debe tener al menos 8 caracteres.";
        }

        if (!contrasena.matches(".*[0-9].*")) {
            return "La contraseña debe contener al menos un número.";
        }

        if (!contrasena.matches(".*[A-Za-z].*")) {
            return "La contraseña debe contener letras.";
        }

        if (!contrasena.equals(confirmar)) {
            return "Las contraseñas no coinciden.";
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setContrasena(Encriptador.hashPassword(contrasena));
        nuevo.setRol(RolUsuario.CLIENTE);
        nuevo.setEsActivo(true);

        usuarioDAO.crear(nuevo);
        return null;
    }

    /**
     * Obtiene todos los usuarios.
     */
    public List<Usuario> obtenerTodos() {
        return usuarioDAO.buscarTodos();
    }

    /**
     * Busca un usuario por su ID.
     */
    public Usuario buscarPorId(Long id) {
        return usuarioDAO.buscarPorId(id);
    }

    /**
     * Busca usuarios por nombre o correo (búsqueda parcial).
     */
    public List<Usuario> buscarPorNombreOCorreo(String termino) {
        return usuarioDAO.buscarPorNombreOCorreo(termino);
    }

    /**
     * Busca usuarios por rol.
     */
    public List<Usuario> buscarPorRol(RolUsuario rol) {
        return usuarioDAO.buscarPorRol(rol);
    }

    /**
     * Busca usuarios por estado.
     */
    public List<Usuario> buscarPorEstado(boolean esActivo) {
        return usuarioDAO.buscarPorEstado(esActivo);
    }

    /**
     * Cuenta el total de usuarios.
     */
    public long contarTodos() {
        return usuarioDAO.contarTodos();
    }

    /**
     * Cuenta usuarios por estado.
     */
    public long contarPorEstado(boolean esActivo) {
        return usuarioDAO.contarPorEstado(esActivo);
    }

    /**
     * Edita el nombre y correo de un usuario.
     * @return null si es exitoso, mensaje de error si falla
     */
    public String editarUsuario(Long id, String nombre, String correo) {
        if (nombre == null || nombre.isBlank() || nombre.length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }

        if (correo == null || correo.isBlank()) {
            return "Debes ingresar un correo.";
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "El correo no tiene un formato válido.";
        }

        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario == null) {
            return "Usuario no encontrado.";
        }

        // Verificar si el correo ya existe en otro usuario
        Usuario existente = usuarioDAO.buscarPorCorreo(correo);
        if (existente != null && !existente.getId().equals(id)) {
            return "Ya existe otro usuario con ese correo.";
        }

        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuarioDAO.actualizar(usuario);
        return null;
    }

    /**
     * Cambia el estado activo/inactivo de un usuario.
     * @return null si es exitoso, mensaje de error si falla
     */
    public String cambiarEstado(Long id) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario == null) {
            return "Usuario no encontrado.";
        }

        usuario.setEsActivo(!usuario.isEsActivo());
        usuarioDAO.actualizar(usuario);
        return null;
    }

    /**
     * Obtiene el estado actual de un usuario.
     */
    public Boolean obtenerEstado(Long id) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        return usuario != null ? usuario.isEsActivo() : null;
    }

    /**
     * Elimina un usuario.
     * @return null si es exitoso, mensaje de error si falla
     */
    public String eliminarUsuario(Long id) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario == null) {
            return "Usuario no encontrado.";
        }

        usuarioDAO.eliminar(usuario);
        return null;
    }
}
