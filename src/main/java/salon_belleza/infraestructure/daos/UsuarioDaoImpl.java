package salon_belleza.infraestructure.daos;

import salon_belleza.domain.dao.UsuarioDao;
import salon_belleza.domain.entities.Usuario;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class UsuarioDaoImpl extends AbstractDao<Usuario> implements UsuarioDao {


    public UsuarioDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Usuario MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Usuario(
                resultSet.getString("name"),
                resultSet.getString("password"));
    }


    @Override
    public void save(Usuario usuario) {
        try {
            String sql = "INSERT INTO user (id_user, name, password) VALUES(?, ?, ?)";
            executeSave(sql, usuario.getId(), usuario.getNombre(), usuario.getContrasenha());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la persistencia del usuario " + e.getSQLState());
        }
    }

    @Override
    public Set<Usuario> findAllAdmins() {
        try {
            String sql = """
                    SELECT u.name, u.password FROM user u
                    INNER JOIN rol_user ru ON u.id_user = ru.user_id\s
                    INNER JOIN rol r ON r.id_rol = ru.rol_id
                    WHERE r.role_name = ?
                    """;
            return executeFindAll(sql, "ADMIN");
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la obtencion de la lista de administradores" + e.getSQLState());
        }
        return new HashSet<>();
    }

    @Override
    public Set<Usuario> findAllEmployeers() {
        try {
            String sql = """
                    SELECT u.name, u,password FROM user u\s
                    INNER JOIN rol_user ru ON u.id_user = ru.user_id\s
                    INNER JOIN rol r ON r.id_rol = ru.rol_id
                    WHERE r.role_name = ?
                    """;
            return executeFindAll(sql, "EMPLOYEE");
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la obtencion de la lista de empleados " + e.getSQLState());
        }
        return new HashSet<>();
    }

    @Override
    public Usuario findById(String id) {
        try {
            String sql = """
                    SELECT u.name, u.password FROM user u WHERE u.id_user = ?
                    """;
            return executeFindByID(sql, id);
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la obtención del usuario " + e.getSQLState());
        }
        return null;
    }

    @Override
    public void update(Usuario usuario) {
        try {
            String sql = """
                    UPDATE user u\s
                    SET name = ?
                    WHERE id_user = ?
                    """;
            executeUpdate(sql, usuario.getId());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la actualizacion de los datos" + e.getSQLState());
        }
    }

    @Override
    public void delete(Usuario usuario) {
        try {
            String sql = """
                    DELETE FROM user WHERE id_user = ?
                    """;
            exeuteDelete(sql, usuario.getId());
        } catch (SQLException e) {
            System.out.println("Ocurrio un error con la eliminacicon del usuario " + e.getSQLState());
        }

    }

    @Override
    public Usuario findByName(String name) {
        try {
            String sql = "SELECT u.name FROM user u WHERE u.name = ?";
            return executeFindByName(sql, name);
        } catch (SQLException e) {
            System.out.println("Ocurrio un error obteniendo usuario" + e.getSQLState());
        }
        return null;
    }
}
