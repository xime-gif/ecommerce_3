package bo;

import daos.DetallePedidoDAO;
import daos.JPAUtil;
import daos.PedidoDAO;
import daos.ReseniaDAO;
import daos.VehiculoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import modelos.*;

public class PedidoBO {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
    private final ReseniaDAO reseniaDAO = new ReseniaDAO();

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

    public void actualizarEstadoPedido(Long pedidoId, String nuevoEstado) throws Exception {
        Pedido pedido = pedidoDAO.buscarPorId(pedidoId);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            pedidoDAO.actualizar(pedido);
        } else {
            throw new Exception("Pedido no encontrado ID: " + pedidoId);
        }
    }

    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario) {
        List<Pedido> pedidos = pedidoDAO.obtenerPedidosPorCliente(usuario);

        for (Pedido pedido : pedidos) {
            List<DetallePedido> detalles = detallePedidoDAO.obtenerDetallesPorPedido(pedido.getId());
            pedido.setDetalles(detalles);
        }

        return pedidos;
    }

    public void registrarResenia(Long detalleId, int calificacion, String comentario) throws Exception {
        DetallePedido detalle = detallePedidoDAO.buscarPorId(detalleId);
        
        if (detalle == null) {
            throw new Exception("Detalle del pedido no encontrado.");
        }
        
        Vehiculo vehiculo = detalle.getVehiculo();
        Usuario cliente = detalle.getPedido().getCliente();
        
        if (vehiculo == null || cliente == null) {
            throw new Exception("Faltan datos de vehículo o cliente en el detalle del pedido. Error de carga LAZY.");
        }

        boolean exito = reseniaDAO.agregarResenia(
            calificacion, 
            comentario, 
            cliente.getId(), 
            vehiculo.getId()
        );

        if (!exito) {
            throw new Exception("Fallo en la persistencia de la reseña.");
        }
    }
}
