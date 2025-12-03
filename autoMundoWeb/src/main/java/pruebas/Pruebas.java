package pruebas;

import daos.UsuarioDAO;
import modelos.RolUsuario;
import modelos.Usuario;
import seguridad.Encriptador;

public class Pruebas {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Usuario cliente
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Juan Pérez");
        nuevoUsuario.setCorreo("juan@ejemplo.com");
        nuevoUsuario.setContrasena(Encriptador.hashPassword("juan123"));
        nuevoUsuario.setRol(RolUsuario.CLIENTE);
        nuevoUsuario.setEsActivo(true);
        usuarioDAO.crear(nuevoUsuario);

        // Usuario admin
        Usuario admin = new Usuario();
        admin.setNombre("Admin Principal");
        admin.setCorreo("admin@autozone.com");
        admin.setContrasena(Encriptador.hashPassword("admin123"));
        admin.setRol(RolUsuario.ADMINISTRADOR);
        admin.setEsActivo(true);
        usuarioDAO.crear(admin);

    }
}
