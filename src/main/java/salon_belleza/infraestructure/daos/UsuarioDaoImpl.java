package salon_belleza.infraestructure.daos;

import salon_belleza.domain.dao.UsuarioDao;
import salon_belleza.domain.entities.Usuario;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public class UsuarioDaoImpl extends AbstractDao<Usuario> implements UsuarioDao {


    public UsuarioDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Usuario MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Usuario.desdeBD(
                resultSet.getString("id_user"),
                resultSet.getString("name"),
                resultSet.getString("password")
        );
    }


    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO user (id_user, name, password) VALUES(?, ?, ?)";
        executeSave(sql, usuario.getId(), usuario.getNombre(), usuario.getContrasenha());
    }

    @Override
    public Set<Usuario> findAllAdmins() {
        String sql = """
                SELECT u.id_user,u.name, u.password FROM user u
                INNER JOIN rol_user ru ON u.id_user = ru.user_id\s
                INNER JOIN rol r ON r.id_rol = ru.rol_id
                WHERE r.role_name = ?
                """;
        return executeFindAll(sql, "ADMIN");
    }

    @Override
    public Set<Usuario> findAllEmployeers() {
        String sql = """
                SELECT u.id_user,u.name, u,password FROM user u\s
                INNER JOIN rol_user ru ON u.id_user = ru.user_id\s
                INNER JOIN rol r ON r.id_rol = ru.rol_id
                WHERE r.role_name = ?
                """;
        return executeFindAll(sql, "EMPLOYEE");
    }

    @Override
    public Usuario findById(String id) {
        String sql = """
                SELECT u.id_user,u.name, u.password FROM user u WHERE u.id_user = ?
                """;
        return executeFindByID(sql, id);
    }

    @Override
    public void update(Usuario usuario) {
        String sql = """
                UPDATE user u\s
                SET name = ?
                WHERE id_user = ?
                """;
        executeUpdate(sql, usuario.getId());
    }

    @Override
    public void delete(Usuario usuario) {
        String sql = """
                DELETE FROM user WHERE id_user = ?
                """;
        executeDelete(sql, usuario.getId());

    }

    @Override
    public List<Usuario> findByName(String name) {
        String sql = "SELECT u.id_user,u.name, u.password FROM user u WHERE u.name = ?";
        return executeFindByName(sql, name);
    }

    @Override
    public Usuario findAdmin(String rol) {
        String sql = """
                SELECT u.id_user, u.name, u.password FROM user u
                INNER JOIN rol_user ru ON u.id_user = ru.user_id
                INNER JOIN rol r ON r.id_rol = ru.rol_id
                WHERE r.role_name = ?""";
        return executeFindByRole(sql, rol);
    }
}
