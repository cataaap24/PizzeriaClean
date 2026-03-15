package usecases.services;

import entities.Cliente;
import usecases.dto.OperationResult;
import usecases.ports.ClienteRepository;
import usecases.ports.IdGenerator;

public class RegistrarCliente {
    private final ClienteRepository clientes;
    private final IdGenerator idGenerator;

    public RegistrarCliente(ClienteRepository clientes, IdGenerator idGenerator) {
        this.clientes = clientes;
        this.idGenerator = idGenerator;
    }

    public OperationResult registrar(String nombre, String telefono) {
        if (nombre == null || nombre.isBlank()) {
            return OperationResult.fail("El nombre no puede estar vacío.");
        }
        try {
            Cliente c = new Cliente(idGenerator.nextId("CLI"), nombre, telefono);
            c.setNombre(nombre);
            c.setTelefono(telefono); 
            clientes.saveClient(c);
            return OperationResult.ok("Cliente '" + nombre + "' registrado exitosamente.");
        } catch (Exception e) {
            return OperationResult.fail(e.getMessage());
        }
    }
}
