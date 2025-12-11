package modelos;

import java.io.Serializable;

/**
 *
 * @author Ximena
 */
public class ItemCarrito implements Serializable {

    private Vehiculo vehiculo;
    private int cantidad;

    public ItemCarrito(Vehiculo vehiculo, int cantidad) {
        this.vehiculo = vehiculo;
        this.cantidad = cantidad;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return vehiculo.getPrecio() * cantidad;
    }

}
