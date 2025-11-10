package boletin_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorMySQL implements AutoCloseable {

    // Atributos
    private Connection connect;
    private String url;
    private String user;
    private String pass;

 // Constructor
    public ConnectorMySQL(String url,String user, String pass) {
        System.out.println("Iniciando conexión con la Base de Datos...");

        this.url = url;
        this.user = user;
        this.pass = pass;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar driver
            this.connect = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida exitosamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la Base de Datos.");
            e.printStackTrace();
        }
    }


    // Getter de connect
    public Connection getConnect() {
        return connect;
    }

    // Método para liberar los recursos
    public void Release() {
        System.out.println("Liberando recursos de la conexión...");

        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
                System.out.println("Conexión cerrada correctamente.");
            } else {
                System.out.println("No hay conexión activa para cerrar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        } finally {
            connect = null;
            url = null;
            user = null;
            pass = null;
            System.out.println("Recursos liberados correctamente.");
        }
    }

    // AutoCloseable: implementación de close()
    @Override
    public void close() {
        Release();
    }
}
