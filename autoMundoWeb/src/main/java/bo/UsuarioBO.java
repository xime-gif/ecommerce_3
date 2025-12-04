/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.UsuarioDAO;
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
}
