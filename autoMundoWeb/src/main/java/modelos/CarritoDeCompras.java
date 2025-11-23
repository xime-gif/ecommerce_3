package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ximena
 */
public class CarritoDeCompras implements Serializable {

    private final List<ItemCarrito> items;

    public CarritoDeCompras() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items);
    }

    public void agregarItem(Vehiculo vehiculo, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getVehiculo().getId().equals(vehiculo.getId())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }

        ItemCarrito itemNuevo = new ItemCarrito(vehiculo, cantidad);
        this.items.add(itemNuevo);
    }

    public void eliminarItem(Long idVehiculo) {
        this.items.removeIf(item -> item.getVehiculo().getId().equals(idVehiculo));
    }

    public double getTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void limpiar() {
        this.items.clear();
    }
}
