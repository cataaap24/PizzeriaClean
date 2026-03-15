package usecases.services;

import entities.Pizza;
import usecases.dto.OperationResult;
import usecases.ports.IdGenerator;
import usecases.ports.ProductoRepository;

public class RegistrarPizza {
    private final ProductoRepository menu;
    private final IdGenerator idGenerator;

    public RegistrarPizza(ProductoRepository menu, IdGenerator idGenerator) {
        this.menu = menu;
        this.idGenerator = idGenerator;
    }

    public OperationResult registrar(String nombre, String tamano, String sabor) {
        try {
            String id = idGenerator.nextId("PROD");
            Pizza pizza = new Pizza(id, nombre, tamano, sabor);
            menu.saveProduct(pizza);
            return OperationResult.ok("Pizza agregda al menú exitosamente: " + nombre + " (ID = " + id + ")");
        } catch (Exception e) {
            return OperationResult.fail("Error al registrar la pizza: " + e.getMessage());
        }
    }
}
