package salon_belleza.infraestructure.daos;

import salon_belleza.domain.dao.ClienteDao;
import salon_belleza.domain.entities.Cliente;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ClienteDaoImpl extends AbstractDao<Cliente> implements ClienteDao {

    public ClienteDaoImpl(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public void saveClient(Cliente cliente) {
            String sql = "INSERT INTO cliente(id_cliente,nombre,telefono,fecha_registro,dni) VALUES(?,?,?,?,?)";
            executeSave(sql, cliente.getId(),cliente.getNombre(), cliente.getTelefono(), cliente.getFecha_regsitro(), cliente.getDni());
    }

    @Override
    public List<Cliente> findClientByName(String nombre) {
            String sql = "SELECT nombre, telefono, fecha_registro, dni FROM cliente c WHERE c.nombre = ?";
            return executeFindByName(sql, nombre);
    }

    @Override
    public Cliente findClientByDni(String dni) {
            String sql = "SELECT nombre, telefono, fecha_registro, dni FROM cliente c WHERE c.dni = ?";
            return executeFindByID(sql, dni); // El metodo findById está siendo reutilizado para obtener un usuario por su DNI
    }

    @Override
    public Cliente findClientById(String id) {
            String sql = "SELECT nombre,telefono,fecha_registro,dni FROM cliente c WHERE c.id = ?";
            return executeFindByID(sql, id);
    }

    @Override
    public void deleteClienteById(String id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        executeDeleteById(sql, id);
    }

    @Override
    public void deleteClienteByName(String nombre) {
        String sql = "DELETE FROM cliente WHERE nombre = ?";
        executeDelete(sql, nombre);
    }

    @Override
    public void deleteClienteByDni(String dni) {

    }

    @Override
    public void updateClienteByName(String nombre, Cliente cliente) {
        String sql = """
                 UPDATE cliente SET\s
                 nombre = ?,
                 telefono = ?,
                 fecha_registro = ?,
                 dni = ? WHERE nombre = ?
                \s""";
        executeUpdate(sql, nombre);
    }

    @Override
    public void updateClienteByID(Cliente cliente) {
        String sql = """
                UPDATE cliente SET\s
                 nombre = ?,
                 telefono = ?,
                 fecha_registro = ?,
                 dni = ? WHERE nombre = ?
                \s""";
        executeUpdate(sql,
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getFecha_regsitro(),
                cliente.getDni()
        );
    }

    @Override
    public Set<Cliente> findAllClients() {
        String sql = "SELECT nombre, telefono, fecha_registro, dni FROM cliente";
        return executeFindAll(sql);
    }

    @Override
    protected Cliente MapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getDate("fecha_registro").toLocalDate(),
                rs.getString("dni")
                );
    }
}
