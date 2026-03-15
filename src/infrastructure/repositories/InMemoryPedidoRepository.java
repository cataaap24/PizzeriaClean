package infrastructure.repositories;

import entities.Pedido;
import java.util.ArrayList;
import java.util.List;
import usecases.ports.PedidoRepository;

public class InMemoryPedidoRepository implements PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void saveOrder(Pedido order) { pedidos.add(order); }

    @Override
    public Pedido findById(String id) {
        for (Pedido order : pedidos) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> findAll() { return new ArrayList<>(pedidos); }

    @Override 
    public List<Pedido> findActivos() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido order : pedidos) {
            if (order.estaActivo()) {
                activos.add(order);
            }
        }
        return activos;
    }
}
