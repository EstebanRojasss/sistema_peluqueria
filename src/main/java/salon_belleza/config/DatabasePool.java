package salon_belleza.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePool {

    private final static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(ConnectionConfig.getUrl());
        config.setUsername(ConnectionConfig.getUser());
        config.setPassword(ConnectionConfig.getPasswd());

        config.setMaximumPoolSize(1);
        config.setMaxLifetime(14400000);
        config.setIdleTimeout(7200000);

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }



}
