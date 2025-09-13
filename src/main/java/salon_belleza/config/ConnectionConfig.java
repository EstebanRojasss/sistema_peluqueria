package salon_belleza.config;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

    static Properties properties = new Properties();
    
    static {
        try(InputStream inputStream = ConnectionConfig.class
                .getClassLoader()
                .getResourceAsStream("db.properties")){

            if(inputStream == null){
                throw new RuntimeException("No se encontró db.properties");
            }

            properties.load(inputStream);
        }catch (Exception e){
            throw new RuntimeException("Ocurrió un error con el archivo de configuracion de la db", e);
        }
    }


    public static String getUrl(){
        return properties.getProperty("db.url");
    }

    public static String getUser(){
        return properties.getProperty("db.user");
    }

    public static String getPasswd(){
        return properties.getProperty("db.password");
    }


}
