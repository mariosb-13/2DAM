package tarea_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMayus {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Scanner sc = new Scanner(System.in);
             Socket socket = new Socket(host, puerto)) { // Conectar al servidor

            // Crear flujos
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Pide dato al usuario
            System.out.print("Introduce un mensaje para enviar al servidor: ");
            String mensaje = sc.nextLine();

            // Envia el mensaje
            salida.println(mensaje);

            // Recibe la respuesta
            String respuesta = entrada.readLine();
            System.out.println("RESPUESTA DEL SERVIDOR: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error: No se ha podido conectar con el servidor.");
            System.out.println("Asegúrate de ejecutar primero 'ServidorMayusculas'.");
        }
    }
}
