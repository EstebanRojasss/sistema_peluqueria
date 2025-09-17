package salon_belleza.infraestructure.daos.jdbcCommonTemplates;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArgumentPreparedStatementSetter implements PreparedStatementSetter{

    private final Object[] params;

    public ArgumentPreparedStatementSetter(Object[] params){
        this.params = params;
    }

    @Override
    public void setValues(PreparedStatement ps)throws SQLException {
        if (params != null){
            for(int i = 0; i < params.length; i++){
                ps.setObject(i+1, params[i]);
            }
        }
    }
}
