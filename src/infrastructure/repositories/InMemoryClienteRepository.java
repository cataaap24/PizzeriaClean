package infrastructure.repositories;

import entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import usecases.ports.ClienteRepository;

public class InMemoryClienteRepository implements ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void saveClient(Cliente client) { clientes.add(client); }

    @Override
    public Cliente findById(String id) {
        for (Cliente cli : clientes) {
            if (cli.getId().equals(id)) {
                return cli;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() { return new ArrayList<>(clientes); }
}
