package main.pack;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MainTests {
    public static void main(String[] args) {
        // 1. Iniciar conexión
        MongoConnection connection = new MongoConnection();
        MongoDatabase db = connection.getDatabase();
        
        // Obtener la colección (tabla)
        MongoCollection<Document> collection = db.getCollection("empleados");

        System.out.println("--- INICIO DE PRUEBAS CRUD ---");

        // CREATE (Insertar)
        Document nuevoEmpleado = new Document()
                .append("nombre", "Carlos Perez")
                .append("puesto", "Desarrollador Junior")
                .append("salario", 25000)
                .append("activo", true);

        collection.insertOne(nuevoEmpleado);
        System.out.println("Documento insertado. ID: " + nuevoEmpleado.get("_id"));

        // READ (Leer)
        System.out.println("Buscando empleado 'Carlos Perez':");
        Document encontrado = collection.find(Filters.eq("nombre", "Carlos Perez")).first();
        
        if (encontrado != null) {
            System.out.println("    Datos: " + encontrado.toJson());
        }

        // UPDATE (Actualizar)
        System.out.println("Actualizando salario y puesto...");
        collection.updateOne(
                Filters.eq("nombre", "Carlos Perez"),
                Updates.combine(
                        Updates.set("salario", 30000),
                        Updates.set("puesto", "Desarrollador Mid")
                )
        );
        
        // Verificación de actualización
        Document actualizado = collection.find(Filters.eq("nombre", "Carlos Perez")).first();
        System.out.println("    Dato actualizado: " + actualizado.toJson());

        // DELETE (Borrar)
        System.out.println("Eliminando empleado...");
        collection.deleteOne(Filters.eq("nombre", "Carlos Perez"));
        
        // Verificación de borrado
        long count = collection.countDocuments(Filters.eq("nombre", "Carlos Perez"));
        System.out.println("    Empleados restantes con ese nombre: " + count);

        // Cerrar conexión
        connection.close();
    }
}