package paquete;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Clase que gestiona la conexión a la base de datos embebida db4o.
 * Actúa como una interfaz de alto nivel para obtener y cerrar el ObjectContainer.
 */
public class Db4oConnection {

    private static final String NOMBRE_FICHERO_BD = "proyectos.db4o"; // Nombre del archivo de la BD
    private ObjectContainer db = null; // El Objeto de Conexión/Sesión

    /**
     * Intenta abrir el fichero de la base de datos. Si no existe, lo crea.
     * * @return true si la conexión se estableció correctamente, false en caso contrario.
     */
    public boolean abrir() {
        try {
        	com.db4o.config.EmbeddedConfiguration config = com.db4o.Db4oEmbedded.newConfiguration();
        	this.db = Db4oEmbedded.openFile(config, NOMBRE_FICHERO_BD);        	
        	System.out.println("Conexión a la BD '" + NOMBRE_FICHERO_BD + "' abierta.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al abrir la conexión db4o: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cierra la conexión al fichero de la base de datos.
     */
    public void cerrar() {
        if (this.db != null) {
            this.db.close();
            System.out.println("Conexión a la BD cerrada.");
            this.db = null;
        }
    }

    /**
     * Devuelve el objeto ObjectContainer para que las clases de negocio (CRUD)
     * puedan realizar operaciones.
     * * @return El ObjectContainer activo, o null si no se ha abierto.
     */
    public ObjectContainer getDb() {
        return this.db;
    }
}