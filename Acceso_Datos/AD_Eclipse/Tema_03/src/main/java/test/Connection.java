package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Connection {

    public static void main(String[] args) {
        
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            // 1. Cargar el archivo de configuracion
            System.out.println("--- CARGANDO CONFIGURACION DE HIBERNATE ---");
            // Hibernate busca por defecto en 'src/main/resources/hibernate.cfg.xml'
            registry = new StandardServiceRegistryBuilder()
                    .configure() // Si el archivo se llama hibernate.cfg.xml no hace falta poner la ruta
                    .build();

            // 2. Configurar la conexión (SessionFactory)
            System.out.println("--- CARGANDO FACTORIA DE SESIONES ---");
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            // 3. Abrir una conexion (Session)
            System.out.print("--- CONECTANDO A LA BD CON HIBERNATE ---");
            session = sessionFactory.openSession();
            System.out.println(" OK ---");

            // AQUÍ IRÍA TU CÓDIGO DE USO (guardar, borrar, consultar...)
            // Ejemplo: session.beginTransaction(); ...

        } catch (Exception e) {
            System.err.println(" ERROR EN HIBERNATE ---");
            e.printStackTrace();
            
            // Si falla la creación de la factoría, hay que destruir el registro
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        } finally {
            // 4. Cerrar la sesión
            if (session != null && session.isOpen()) {
                try {
                    System.out.print("--- CERRANDO SESION ---");
                    session.close();
                    System.out.println(" OK ---");
                } catch (Exception e) {
                    System.err.println(" ERROR CERRANDO SESION ---");
                    e.printStackTrace();
                }
            }

            // 5. Cerrar la factoría al salir del programa
            if (sessionFactory != null && sessionFactory.isOpen()) {
                System.out.println("--- CERRANDO SESSION FACTORY ---");
                sessionFactory.close();
            }
        }
    }
}