package usecases.ports;

import entities.Pedido;
import java.util.List;

public interface PedidoRepository {
    void saveOrder(Pedido order);
    Pedido findById(String id);
    List<Pedido> findAll();
    List<Pedido> findActivos();
}
