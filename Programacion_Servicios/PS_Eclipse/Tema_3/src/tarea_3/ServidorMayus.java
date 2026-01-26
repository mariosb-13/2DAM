package tarea_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMayus {
    public static void main(String[] args) {
        int puerto = 6000; // Puerto de escucha

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("SERVIDOR INICIADO: Escuchando en el puerto " + puerto);

            while (true) {
                // Espera la conexión del cliente
                Socket socketCliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress());

                // Crea flujos de entrada y salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

                // Lee el mensaje del cliente
                String mensajeRecibido = entrada.readLine();
                
                if (mensajeRecibido != null) {
                    // Convierte a mayúsculas y envia de vuelta
                    String respuesta = mensajeRecibido.toUpperCase();
                    salida.println(respuesta);
                    System.out.println("Procesado: " + mensajeRecibido + " -> " + respuesta);
                }

                // Cierra la conexión con este cliente específico
                socketCliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}