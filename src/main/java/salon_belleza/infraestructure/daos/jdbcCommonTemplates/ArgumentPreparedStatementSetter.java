package salon_belleza.infraestructure.daos.jdbcCommonTemplates;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ArgumentPreparedStatementSetter implements PreparedStatementSetter{

    private final Object[] params;

    private ArgumentPreparedStatementSetter(Object... params){
        this.params = params;
    }

    public ArgumentPreparedStatementSetter create(Object[] params){
        return new ArgumentPreparedStatementSetter(params);
    }

    @Override
    public void setValues(PreparedStatement ps)throws SQLException {
        if(params == null) return;

        for(int i = 0; i < params.length; i++){
            Object object = params[i];

            int index = i + 1;

            if(object instanceof BigDecimal){
                ps.setBigDecimal(index, (BigDecimal) object);
            }else if(object instanceof LocalDateTime){
                ps.setTimestamp(index, Timestamp.valueOf((LocalDateTime) object));
            }else if(object instanceof LocalDate){
                ps.setDate(index, Date.valueOf((LocalDate)object));
            }else if(object instanceof Enum<?>){
                ps.setString(index,((Enum<?>) object).name());
            }else{
                ps.setObject(index, object);
            }
        }
    }
}
