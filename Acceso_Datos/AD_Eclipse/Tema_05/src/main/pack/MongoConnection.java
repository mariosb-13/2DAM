package main.pack;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String CONNECTION_STRING = "mongodb://root:ad1234@192.168.13.182:27017";
    private static final String DATABASE_NAME = "mi_empresa";
   
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoConnection() {
        try {
            this.mongoClient = MongoClients.create(CONNECTION_STRING);
            this.database = mongoClient.getDatabase(DATABASE_NAME);
            
            this.database.runCommand(new org.bson.Document("ping", 1));
            
            System.out.println(">> Conexión a MongoDB exitosa: " + DATABASE_NAME);
        } catch (MongoException e) {
            System.err.println(">> Error conectando a MongoDB: " + e.getMessage());
            throw e; 
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
            System.out.println(">> Conexión cerrada.");
        }
    }
}