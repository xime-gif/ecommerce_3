/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Ximena
 */
@Entity
@Table(name = "Administrador")
public class Administrador extends Usuario implements Serializable {

    public void gestionarUsuarios() {
        
    }

    public void gestionarCatalogo() {
        
    }

    public void gestionarPedidos() {
        
    }

    public void moderarResenias() {
        
    }

    @Override
    public String toString() {
        return "Administrador [id=" + getId() + ", nombre=" + getNombre() + "]";
    }

}
