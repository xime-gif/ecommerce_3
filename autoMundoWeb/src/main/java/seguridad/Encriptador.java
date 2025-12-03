/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

import org.mindrot.jbcrypt.BCrypt;

public class Encriptador {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verificarPassword(String passwordPlano, String hashBD) {
        return BCrypt.checkpw(passwordPlano, hashBD);
    }
}
