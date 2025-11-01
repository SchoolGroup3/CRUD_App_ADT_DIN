package model;

import java.time.Duration;
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
            dataSource.setMaxTotal(4);       // Máximo de conexiones
            dataSource.setMaxIdle(4);        // Máximo de conexiones inactivas
            dataSource.setMinIdle(1);        // Mínimo de conexiones inactivas
                                        // 30 segundos de espera
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