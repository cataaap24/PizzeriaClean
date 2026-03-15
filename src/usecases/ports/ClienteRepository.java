package usecases.ports;

import entities.Cliente;
import java.util.List;

public interface ClienteRepository {
    void saveClient(Cliente client);
    Cliente findById(String id);
    List<Cliente> findAll();
}
