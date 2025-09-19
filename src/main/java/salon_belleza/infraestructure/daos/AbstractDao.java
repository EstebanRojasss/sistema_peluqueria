package salon_belleza.infraestructure.daos;

import salon_belleza.infraestructure.daos.jdbcCommonTemplates.ArgumentPreparedStatementSetter;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDao {

    protected ArgumentPreparedStatementSetter argumentSetter;
    protected DataSource dataSource;

    public AbstractDao(ArgumentPreparedStatementSetter argumentSetter, DataSource dataSource) {
        this.argumentSetter = argumentSetter;
        this.dataSource = dataSource;
    }

    protected final void executeSave(String sql, Object... params) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            argumentSetter.create(params).setValues(ps);
            ps.executeUpdate();
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la persistencia de los datos");
        }
    }

    protected final void executeUpdate(String sql, Object... params) throws SQLException{
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            argumentSetter.create(params).setValues(ps);
            ps.executeUpdate();
        }catch (RuntimeException e){
            throw new SQLException("Ocurrio un error con la actualizacion de los datos");
        }
    }

    protected final void exeuteDelete(String sql, Object... params) throws SQLException{
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            argumentSetter.create(params).setValues(ps);
            ps.executeUpdate();
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la eliminacion de los datos");
        }
    }

    protected final T findByID(String sql, Object... params) throws SQLException{
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            argumentSetter.create(params).setValues(ps);

            ResultSet rs = ps.executeQuery();

          if(rs.next()){
              return MapResultSetToEntity(rs);
          }

        }catch (RuntimeException e){
            throw new SQLException("Ocurrio un error con la obtencion de los datos");
        }

        return null;
    }

    protected final List<T> findAll(String sql, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            argumentSetter.create(params).setValues(ps);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                results.add(MapResultSetToEntity(rs));
            }
            ;
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la obtencion de los datos");
        }

        return results;
    }

    protected abstract T MapResultSetToEntity(ResultSet resultSet) throws SQLException;

}
