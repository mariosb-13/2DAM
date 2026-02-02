package tarea_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            
            // Configuración de conexión
            System.out.println("--- CLIENTE CHAT ---");
            System.out.print("Escribe la IP del servidor (local: localhost): ");
            String host = sc.nextLine();
            if(host.isEmpty()) host = "localhost";
            
            System.out.print("Escribe el puerto (ej: 5000): ");
            int puerto = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            Socket socket = new Socket(host, puerto);
            
            // Flujos de comunicación
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread hiloEscucha = new Thread(() -> {
                try {
                    String mensajeServidor;
                    while ((mensajeServidor = entrada.readLine()) != null) {
                        System.out.println(mensajeServidor);
                        System.out.print(">"); // Volver a pintar el prompt visual
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada por el servidor.");
                    System.exit(0);
                }
            });
            hiloEscucha.start();

            if (!socket.isClosed()) {
                System.out.println("Conectado. Esperando instrucciones...");
                                
                while (true) {
                    String mensaje = sc.nextLine();
                    salida.println(mensaje);
                }
            } 

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}