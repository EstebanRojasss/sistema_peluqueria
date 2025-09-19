package salon_belleza.infraestructure.daos;

import salon_belleza.domain.dao.UsuarioDao;
import salon_belleza.domain.entities.Usuario;
import salon_belleza.infraestructure.daos.jdbcCommonTemplates.ArgumentPreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDaoImpl extends AbstractDao<Usuario> implements UsuarioDao {


    public UsuarioDaoImpl(ArgumentPreparedStatementSetter argumentSetter, DataSource dataSource) {
        super(argumentSetter, dataSource);
    }

    @Override
    protected Usuario MapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Usuario(
                resultSet.getString("nombre"),
                        resultSet.getString("contrasenha"));
    }


    @Override
    public void save(Usuario usuario) {
        try{
            String sql = "INSERT INTO usuario (id, nombre, contrasenha) VALUES(?, ?, ?)";
            executeSave(sql, usuario.getId(), usuario.getNombre(), usuario.getContrasenha());
        }catch (SQLException e){
            System.out.println("Ocurrio un error con la persistencia del usuario" + e.getSQLState());
        }
    }

    @Override
    public Usuario findAll() {
        return null;
    }

    @Override
    public Usuario findById() {
        return null;
    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(Usuario usuario) {

    }
}
