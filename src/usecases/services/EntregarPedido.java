package usecases.services;

import entities.Pedido;
import usecases.dto.OperationResult;
import usecases.ports.PedidoRepository;

public class EntregarPedido {
    private final PedidoRepository pedidos;

    public EntregarPedido(PedidoRepository pedidos) {
        this.pedidos = pedidos;
    }

    public OperationResult entregar(String idPedido) {
        Pedido order = pedidos.findById(idPedido);

        if (order == null) {
            return OperationResult.fail("Pedido no encontrado con ID: " + idPedido);
        }

        try {
            order.entregar();
            return OperationResult.ok("Pedido " + idPedido + " entregado exitosamente.");
        } catch (IllegalStateException e) {
            return OperationResult.fail(e.getMessage());
        }
    }
}
