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
     * @param contrasenaPlano
     * @return
     */
    public Usuario registrarCliente(String nombre, String correo, String contrasenaPlano) {
        if (nombre == null || correo == null || contrasenaPlano == null
                || nombre.isBlank() || correo.isBlank() || contrasenaPlano.isBlank()) {
            return null;
        }

        // correo ya usado
        Usuario existente = usuarioDAO.buscarPorCorreo(correo);
        if (existente != null) {
            return null;
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setContrasena(Encriptador.hashPassword(contrasenaPlano));
        nuevo.setRol(RolUsuario.CLIENTE);
        nuevo.setEsActivo(true);

        usuarioDAO.crear(nuevo);
        return nuevo;
    }
}
