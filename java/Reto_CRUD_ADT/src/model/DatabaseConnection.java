package model;

import java.util.ResourceBundle;
import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnection {

    private static BasicDataSource dataSource;

    static {
        try {
            ResourceBundle configFile = ResourceBundle.getBundle("configClase");
            String urlBD = configFile.getString("Conn");
            String userBD = configFile.getString("DBUser");
            String passwordBD = configFile.getString("DBPass");

            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl(urlBD);
            dataSource.setUsername(userBD);
            dataSource.setPassword(passwordBD);

            // Configuración del pool
            dataSource.setInitialSize(1);
            dataSource.setMaxTotal(1);       // Máximo de conexiones
            dataSource.setMaxIdle(1);        // Máximo de conexiones inactivas
            dataSource.setMinIdle(1);        // Mínimo de conexiones inactivas
            
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestOnBorrow(true);
            dataSource.setTestWhileIdle(true);
            dataSource.setMaxWaitMillis(5000); 
            dataSource.setRemoveAbandonedOnBorrow(true);
            dataSource.setLogAbandoned(true);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el pool de conexiones", e);
        }
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
