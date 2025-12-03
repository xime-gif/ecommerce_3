package pruebas;

import daos.UsuarioDAO;
import modelos.RolUsuario;
import modelos.Usuario;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Jp
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Juan Pérez");
        nuevoUsuario.setCorreo("juan@ejemplo.com");
        nuevoUsuario.setContrasena("12345678");
        nuevoUsuario.setRol(RolUsuario.CLIENTE);
        nuevoUsuario.setEsActivo(true);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.crear(nuevoUsuario);
    }

}
