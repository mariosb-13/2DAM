package tarea_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private String nick;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Inicializar flujos
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Pedir y registrar el Nick
            salida.println("Bienvenido. Por favor, introduce tu Nick:");
            String nick = entrada.readLine();
            
            while (!ServidorChat.agregarCliente(nick, salida)) {
                salida.println("El nick ya está en uso. Introduce otro:");
                nick = entrada.readLine();
            }
            
            this.nick = nick;
            System.out.println("Cliente registrado como: " + nick);
            
            // Avisar a todos que entró alguien
            String mensajeBienvenida = ">>> " + nick + " ha entrado al chat.";
            ServidorChat.difundirMensaje(mensajeBienvenida, nick);
            ServidorChat.guardarLog(mensajeBienvenida);

            String mensajeRecibido;
            while ((mensajeRecibido = entrada.readLine()) != null) {
                String mensajeFormateado = nick + ": " + mensajeRecibido;
                
                ServidorChat.difundirMensaje(mensajeFormateado, nick);
                ServidorChat.guardarLog(mensajeFormateado);
            }

        } catch (IOException e) {
            System.out.println("Error en conexión con cliente: " + e.getMessage());
        } finally {
            ServidorChat.eliminarCliente(nick);
            
            if (nick != null) {
                String mensajeSalida = "<<< " + nick + " ha abandonado el chat.";
                ServidorChat.difundirMensaje(mensajeSalida, nick);
                ServidorChat.guardarLog(mensajeSalida);
                System.out.println(nick + " se ha desconectado.");
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}