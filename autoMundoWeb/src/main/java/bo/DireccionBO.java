/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.DireccionDAO;
import modelos.Direccion;

/**
 *
 * @author Jp
 */
public class DireccionBO {

    private final DireccionDAO direccionDAO = new DireccionDAO();

    /**
     * Valida y guarda una dirección en la base de datos.
     *
     * @param direccion La dirección a guardar.
     * @throws IllegalArgumentException Si algún dato obligatorio falta.
     */
    public void guardar(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La dirección no puede ser nula.");
        }

        if (direccion.getNombre() == null || direccion.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la dirección (ej. Casa, Oficina) es obligatorio.");
        }

        if (direccion.getCalle() == null || direccion.getCalle().isBlank()) {
            throw new IllegalArgumentException("La calle es obligatoria.");
        }

        if (direccion.getColonia() == null || direccion.getColonia().isBlank()) {
            throw new IllegalArgumentException("La colonia es obligatoria.");
        }

        if (direccion.getCodigoPostal() == null || direccion.getCodigoPostal().isBlank()) {
            throw new IllegalArgumentException("El código postal es obligatorio.");
        }

        if (direccion.getCodigoPostal().length() > 10) {
            throw new IllegalArgumentException("El código postal es demasiado largo.");
        }

        if (direccion.getCiudad() == null || direccion.getCiudad().isBlank()) {
            throw new IllegalArgumentException("La ciudad es obligatoria.");
        }

        if (direccion.getEstado() == null || direccion.getEstado().isBlank()) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }

        if (direccion.getPais() == null || direccion.getPais().isBlank()) {
            throw new IllegalArgumentException("El país es obligatorio.");
        }

        if (direccion.getUsuario() == null) {
            throw new IllegalArgumentException("La dirección debe estar asociada a un usuario.");
        }

        direccionDAO.crear(direccion);
    }

    /**
     * Busca una dirección por su ID.Útil para recuperar la dirección
     * seleccionada en el Checkout.
     *
     * @param id id de la dirección
     * @return el objeto Direccion relacionado
     */
    public Direccion obtenerPorId(Long id) {
        if (id == null) {
            return null;
        }
        return direccionDAO.buscarPorId(id);
    }
}
