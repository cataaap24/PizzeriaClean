package usecases.ports;

import entities.Producto;
import java.util.List;

public interface ProductoRepository {
    void saveProduct(Producto product);
    Producto findById(String id);
    List<Producto> findAll();
}
