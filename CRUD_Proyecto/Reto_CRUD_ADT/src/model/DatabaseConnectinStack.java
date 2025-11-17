package model;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.util.ResourceBundle; 
import java.util.Stack;

public class DatabaseConnectinStack {
    private static Stack<Connection> pool = new Stack<>(); // Crea una pila estática que almacenará las conexiones disponibles
    private static final int MAX_CONNECTIONS = 3; // Define el máximo de conexiones que tendrá el pool
    private static String url, user, password;

    static {
        try { // Intenta cargar configuración y preparar el pool
            // Cargar configuración
            ResourceBundle config = ResourceBundle.getBundle("configClase"); // Obtiene el archivo de propiedades 'configClase.properties'
            url = config.getString("Conn"); // Lee la URL de conexión desde la clave 'Conn'
            user = config.getString("DBUser"); // Lee el usuario de BD desde la clave 'DBUser'
            password = config.getString("DBPass"); // Lee la contraseña de BD desde la clave 'DBPass'
            
            // Crear conexiones iniciales
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                pool.push(createConnection()); // Crea una nueva conexión y la añade a la pila
            }
        } catch (Exception e) { // Si ocurre cualquier error (configuración o SQL)
            throw new RuntimeException("Error al configurar el pool", e); // Lanza una RuntimeException con causa para detener la carga
        }
    }

    private static Connection createConnection() throws SQLException { // Método auxiliar para crear una conexión JDBC
        return DriverManager.getConnection(url, user, password); // Pide al DriverManager una conexión usando la config cargada
    }

    public static Connection getConnection() throws SQLException { // Método público para obtener una conexión del pool
        synchronized (pool) { // Bloque sincronizado para evitar condiciones de carrera sobre la pila
            if (!pool.isEmpty()) { // Si hay conexiones disponibles
                return pool.pop(); // Extrae y devuelve la conexión del tope de la pila
            }

       /* if (currentConnections < MAX_CONNECTIONS) {
            currentConnections++;
            return createConnection();
        }*/

        }
        throw new SQLException("No hay conexiones disponibles"); // Si no hay conexiones, lanza una excepción (no espera ni crea nuevas)
    }

    public static void releaseConnection(Connection conn) { // Método público para devolver una conexión al pool
        if (conn != null) { // Verifica que la conexión no sea nula
            synchronized (pool) { // Sincroniza el acceso para evitar problemas de concurrencia
                pool.push(conn); // Devuelve la conexión al pool colocándola en la pila
            }
        }
    }
    /*
    uso:
    // Obtener conexión
Connection conn = DatabaseConnectinStack.getConnection();

// Usarla...
// ... tu código SQL aquí

// Devolver al pool
DatabaseConnectinStack.releaseConnection(conn);
    */
    
    
}
