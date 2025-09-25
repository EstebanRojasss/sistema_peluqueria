package salon_belleza.infraestructure.daos;

import salon_belleza.infraestructure.daos.jdbcCommonTemplates.ArgumentPreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDao<T> {

    
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected final void executeSave(String sql, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ArgumentPreparedStatementSetter.create(params).setValues(ps);
            ps.executeUpdate();
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la persistencia de los datos");
        }
    }

    protected final void executeUpdate(String sql, Object... params)  {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ArgumentPreparedStatementSetter.create(params).setValues(ps);
            ps.executeUpdate();
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la actualizacion de los datos");
        }
    }

    protected final void exeuteDelete(String sql, Object... params)  {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ArgumentPreparedStatementSetter.create(params).setValues(ps);
            ps.executeUpdate();
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la eliminacion de los datos");
        }
    }

    protected final T executeFindByID(String sql, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ArgumentPreparedStatementSetter.create(params).setValues(ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return MapResultSetToEntity(rs);
            }

        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la obtencion de los datos");
        }

        return null;
    }

    protected final Set<T> executeFindAll(String sql, Object... params) {
        Set<T> results = new HashSet<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ArgumentPreparedStatementSetter.create(params).setValues(ps);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                results.add(MapResultSetToEntity(rs));
            }
        } catch (RuntimeException e) {
            throw new SQLException("Ocurrio un error con la obtencion de los datos");
        }

        return results;
    }

    protected final T executeFindByName(String sql, Object...params) {
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ArgumentPreparedStatementSetter.create(params).setValues(ps);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return MapResultSetToEntity(rs);
            }

        }catch (RuntimeException e){
            throw new SQLException("Ocurrio un error con la obtencion de los datos");
        }
        return null;
    }

    protected abstract T MapResultSetToEntity(ResultSet resultSet) throws SQLException;

}
