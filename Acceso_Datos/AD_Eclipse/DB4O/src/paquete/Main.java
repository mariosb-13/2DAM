// CLASE: Main.java
package paquete;

import com.db4o.ObjectContainer;

public class Main {
    public static void main(String[] args) {
        
        Db4oConnection dbConn = new Db4oConnection();

        if (dbConn.abrir()) {
            
            ObjectContainer db = dbConn.getDb(); 

            Db4oCRUD.inicializarBD(db);

            Db4oCRUD.consultarEmpleados(db);

            Db4oCRUD.modificarSalario(db, "SÁNCHEZ", 500.0);
            
            Db4oCRUD.eliminarEmpleado(db, "REY");
            
            Db4oCRUD.consultarEmpleados(db);

            dbConn.cerrar();
            
        } else {
            System.err.println("No se pudo iniciar la aplicación.");
        }
    }
}