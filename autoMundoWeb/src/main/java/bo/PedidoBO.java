/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import daos.JPAUtil;
import daos.PedidoDAO;
import daos.VehiculoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import modelos.*;

/**
 *
 * @author Jp
 */
public class PedidoBO {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();

    /**
     * Realiza el proceso de compra, si falla algo no se completa la
     * transaccion, por eso no usa las DAOs
     *
     * @param usuario El cliente que compra.
     * @param carrito El carrito con los productos.
     * @param direccion La dirección de envío seleccionada.
     * @param metodoPago El método de pago (String).
     * @return El Pedido creado y guardado.
     * @throws Exception Si no hay stock o falla la base de datos.
     */
    public Pedido realizarCompra(Usuario usuario, CarritoDeCompras carrito, Direccion direccion, String metodoPago) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaccion = em.getTransaction();

        if (carrito == null || carrito.getItems().isEmpty()) {
            throw new Exception("El carrito de compras está vacío.");
        }

        try {
            transaccion.begin();

            Pedido pedido = new Pedido();
            pedido.setCliente(usuario);
            pedido.setDireccionEnvio(direccion);
            pedido.setFechaCompra(LocalDateTime.now());
            pedido.setMetodoPago(metodoPago);
            pedido.setEstado("PENDIENTE");

            pedido.setNumeroPedido(UUID.randomUUID().toString());

            List<DetallePedido> detalles = new ArrayList<>();
            double totalCalculado = 0.0;

            for (ItemCarrito item : carrito.getItems()) {
                Long vehiculoId = item.getVehiculo().getId();

                Vehiculo vehiculoBD = em.find(Vehiculo.class, vehiculoId);

                if (vehiculoBD.getExistencias() < item.getCantidad()) {
                    throw new Exception("Stock insuficiente para el vehículo: " + vehiculoBD.getNombre() + ". Disponible: " + vehiculoBD.getExistencias());
                }

                vehiculoBD.setExistencias(vehiculoBD.getExistencias() - item.getCantidad());
                em.merge(vehiculoBD);

                DetallePedido detalle = new DetallePedido();
                detalle.setPedido(pedido);
                detalle.setVehiculo(vehiculoBD);
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecioUnitario(vehiculoBD.getPrecio());

                detalles.add(detalle);

                totalCalculado += (vehiculoBD.getPrecio() * item.getCantidad());
            }

            pedido.setDetalles(detalles);
            pedido.setTotalPagado(totalCalculado);

            em.persist(pedido);

            transaccion.commit();

            new Thread(() -> {
                EmailBO emailBO = new EmailBO();
                emailBO.enviarConfirmacionPedido(usuario.getCorreo(), pedido.getNumeroPedido(), pedido.getTotalPagado());
            }).start();

            carrito.limpiar();

            return pedido;

        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el estado de un pedido
     *
     * @param pedidoId id del pedido
     * @param nuevoEstado estado a actualizar
     * @throws java.lang.Exception
     */
    public void actualizarEstadoPedido(Long pedidoId, String nuevoEstado) throws Exception {
        Pedido pedido = pedidoDAO.buscarPorId(pedidoId);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            pedidoDAO.actualizar(pedido);
        } else {
            throw new Exception("Pedido no encontrado ID: " + pedidoId);
        }
    }
}
