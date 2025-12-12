/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Jp
 */
public class PedidoBO {

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAO();
    private final ReseniaDAO reseniaDAO = new ReseniaDAO();

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
    
    /**
     * Delega al DAO la obtención de todos los pedidos de un cliente.
     * La lógica de JOIN FETCH, inicialización de imágenes y eliminación de duplicados 
     * reside en el DAO para evitar la LazyInitializationException (LIE) en la capa de vista.
     * * @param cliente El usuario cuyos pedidos se buscan.
     * @return Una lista de Pedidos con todos los detalles y vehículos cargados.
     */
    public List<Pedido> obtenerPedidosPorUsuario(Usuario cliente) {
        // Delegación directa a la capa de persistencia (DAO)
        return pedidoDAO.obtenerPedidosPorCliente(cliente);
    }


  

    /**
     * Registra una reseña para un DetallePedido específico.
     * * @param detalleId ID del detalle del pedido que se va a reseñar.
     * @param calificacion Puntuación de la reseña.
     * @param comentario Texto de la reseña.
     */
    public void registrarResenia(Long detalleId, int calificacion, String comentario) throws Exception {
        
        // 1. Buscar el DetallePedido (necesario para obtener el Vehiculo y el Cliente)
        DetallePedido detalle = detallePedidoDAO.buscarPorId(detalleId);
        
        if (detalle == null) {
            throw new Exception("Detalle de pedido no encontrado.");
        }
        
        // 2. Crear y configurar la nueva Reseña
        Resenia nuevaResenia = new Resenia();
        nuevaResenia.setCalificacion(calificacion);
        nuevaResenia.setComentario(comentario);
        
        // Asignar el vehículo y el cliente (a través del pedido) a la reseña
        nuevaResenia.setVehiculo(detalle.getVehiculo()); 
        nuevaResenia.setCliente(detalle.getPedido().getCliente()); 

        // 3. Persistir la Reseña
        reseniaDAO.crear(nuevaResenia);
    }
}
