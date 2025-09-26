package salon_belleza.domain.dao;

import salon_belleza.domain.entities.Cliente;

import java.util.List;
import java.util.Set;

public interface ClienteDao {
    void saveClient(Cliente cliente);
    List<Cliente> findClientByName(String nombre);
    Cliente findClientByDni(String cliente);
    Cliente findClientById(String id);
    void deleteClienteById(Cliente cliente);
    void deleteClienteByName(Cliente cliente);
    void deleteClienteByDni(Cliente cliente);
    void updateClienteByName(Cliente cliente);
    void updateClienteByID(Cliente cliente);
    Set<Cliente>findAllClients();
}
