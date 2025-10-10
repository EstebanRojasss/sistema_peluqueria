package salon_belleza.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePoolTemplate {
    private DatabasePoolTemplate() {
    }


    private static final class DataSourceHolder {
        private static final HikariDataSource dataSource = initDatasource();
    }

    public static HikariDataSource getDatasource() {

        return DataSourceHolder.dataSource;
    }


    private static HikariDataSource initDatasource() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mariadb://localhost:3306/{YOUR_DB}?useSSL=false");
        config.setUsername("{YOUR_USER}}");
        config.setPassword("YOUR_PASSWORD");
        config.setMaximumPoolSize(5);
        config.setMaxLifetime(14400000);
        config.setIdleTimeout(720000);

        return new HikariDataSource(config);
    }
}

