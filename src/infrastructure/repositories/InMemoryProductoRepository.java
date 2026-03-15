package infrastructure.repositories;

import entities.Producto;
import java.util.ArrayList;
import java.util.List;
import usecases.ports.ProductoRepository;

public class InMemoryProductoRepository implements ProductoRepository {
    private final List<Producto> menu = new ArrayList<>();

    @Override
    public void saveProduct(Producto product) { menu.add(product); }

    @Override
    public Producto findById(String id) {
        for (Producto product : menu) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Producto> findAll() { return new ArrayList<>(menu); }
}
