package tarea_4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ServidorChat {
    private static Map<String, PrintWriter> clientes = new HashMap<>();

    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor Iniciado: Escuchando en el puerto " + puerto);

            while (true) {
                // Esperar nueva conexión
                Socket socketCliente = servidor.accept();
                System.out.println("Nuevo cliente conectado desde: " + socketCliente.getLocalSocketAddress());

                // Creamos un hilo manejador para este cliente
                ManejadorCliente manejador = new ManejadorCliente(socketCliente);
                Thread hilo = new Thread(manejador);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método sincronizado para añadir clientes al mapa
    public static synchronized boolean agregarCliente(String nick, PrintWriter out) {
        if (clientes.containsKey(nick)) {
            return false; // El nick ya existe
        }
        clientes.put(nick, out);
        return true;
    }

    // Método sincronizado para eliminar clientes
    public static synchronized void eliminarCliente(String nick) {
        if (nick != null) {
            clientes.remove(nick);
        }
    }

    public static synchronized void difundirMensaje(String mensaje, String remitente) {
        for (Map.Entry<String, PrintWriter> entrada : clientes.entrySet()) {
            String nickDestino = entrada.getKey();
            PrintWriter outDestino = entrada.getValue();

            // No muestra el texto que envía un cliente a sí mismo
            if (!nickDestino.equals(remitente)) {
                outDestino.println(mensaje);
            }
        }
    }

    /**
     * Método para crear el log
     * @param mensaje
     */
    public static synchronized void guardarLog(String mensaje) {
        LocalDate hoy = LocalDate.now();
        String nombreFichero = "chat_" + hoy.toString() + ".txt";
        
        try (FileWriter fw = new FileWriter(nombreFichero, true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            pw.println("[" + hora + "] " + mensaje);
            
        } catch (IOException e) {
            System.err.println("Error escribiendo en log: " + e.getMessage());
        }
    }
}