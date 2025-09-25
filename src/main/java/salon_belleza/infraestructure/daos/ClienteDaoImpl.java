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
        try {
            String sql = "INSERT INTO cliente(nombre,telefono,fecha_registro,dni) VALUES(?,?,?,?)";
            executeSave(sql, cliente.getNombre(), cliente.getTelefono(), cliente.getFecha_regsitro(), cliente.getDni());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la persistencia de los datos del cliente." + e.getSQLState());
        }
    }

    @Override
    public Cliente findClientByName(String name) {
        try{
            String sql = "SELECT nombre, telefono, fecha_registro, dni FROM cliente c WHERE c.nombre = ?";
            return executeFindByName(sql, name);
        }catch (SQLException e){
            System.out.println("Ocurrio un error con la obtencion de los datos del cliente"+ e.getSQLState());
        }
        return null;
    }

    @Override
    public Cliente findClientByDni(String dni) {
        try{
            String sql = "SELECT name, telefono, fecha_registro, dni FROM cliente c WHERE c.dni = ?";
            return executeFindByID(sql, dni); // El metodo findById está siendo reutilizado para obtener un usuario por su DNI
        }catch (SQLException e){
            System.out.println("Ocurrio un error con la obtencion de los datos del cliente" + e.getSQLState());
        }

        return null;
    }

    @Override
    public Cliente findClientById(String id) {
        return null;
    }

    @Override
    public void deleteClienteById(String id) {

    }

    @Override
    public void deleteClienteByName(String name) {

    }

    @Override
    public void deleteClienteByDni(String dni) {

    }

    @Override
    public void updateClienteByName(String name) {

    }

    @Override
    public Set<Cliente> findAllClients() {
        return Set.of();
    }

    @Override
    protected Cliente MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
