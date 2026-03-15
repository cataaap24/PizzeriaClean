package usecases.services;

import entities.Pedido;
import usecases.dto.OperationResult;
import usecases.ports.PedidoRepository;

public class CancelarPedido {
    private final PedidoRepository pedidos;

    public CancelarPedido(PedidoRepository pedidos) {
        this.pedidos = pedidos;
    }

    public OperationResult cancelar(String idPedido) {
        Pedido order = pedidos.findById(idPedido);

        if (order == null) {
            return OperationResult.fail("Pedido no encontrado con ID: " + idPedido);
        }

        try {
            order.cancelar();
            return OperationResult.ok("Pedido " + idPedido + " cancelado exitosamente.");
        } catch (IllegalStateException e) {
            return OperationResult.fail(e.getMessage());
        }
    }
}
