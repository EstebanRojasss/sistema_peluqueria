package salon_belleza.domain.dao;

import salon_belleza.domain.entities.Cliente;

import java.util.Set;

public interface ClienteDao {
    void saveClient(Cliente cliente);
    Cliente findClientByName(String name);
    Cliente findClientByDni(String cliente);
    Cliente findClientById(String id);
    void deleteClienteById(String id);
    void deleteClienteByName(String name);
    void deleteClienteByDni(String dni);
    void updateClienteByName(String name);
    Set<Cliente>findAllClients();
}
