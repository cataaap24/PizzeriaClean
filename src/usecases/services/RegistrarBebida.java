package usecases.services;

import entities.Bebida;
import usecases.dto.OperationResult;
import usecases.ports.IdGenerator;
import usecases.ports.ProductoRepository;

public class RegistrarBebida {
    private final ProductoRepository menu;
    private final IdGenerator idGenerator;

    public RegistrarBebida(ProductoRepository menu, IdGenerator idGenerator) {
        this.menu = menu;
        this.idGenerator = idGenerator;
    }

    public OperationResult registrar(String nombre, int volumenMl) {
        try {
            String id = idGenerator.nextId("PROD");
            Bebida bebida = new Bebida(id, nombre, volumenMl);
            menu.saveProduct(bebida);
            return OperationResult.ok("Bebida registrada exitosamente: " + nombre + " (ID = " + id + ")");
        } catch (Exception e) {
            return OperationResult.fail("Error al registrar la bebida: " + e.getMessage());
        }
    }
}
