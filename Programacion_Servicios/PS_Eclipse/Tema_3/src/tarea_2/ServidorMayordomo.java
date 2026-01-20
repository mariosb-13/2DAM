package tarea_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServidorMayordomo {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Arrancado el servidor en el puerto 5000");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(5000);
		} catch (IOException e) {
			System.out.println("No se pudo poner un socket a escuchar en TCP 5000");
			return;
		}
		
		while (true) {
			Socket conexion = socketEscucha.accept();
			System.out.println("Conexion recibida!");
			
			InputStream is = conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(isr);
			
			OutputStream os = conexion.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			
			// Enviar mensaje de bienvenida al cliente
			pw.println("Hola, soy tu servidor. ¿En qué puedo ayudarte?");
			pw.flush();
			
			boolean conversacionActiva = true;
			
			while (conversacionActiva) {
				String linea = bf.readLine();
				System.out.println("Cliente dice: " + linea);
				
				
				if (linea.equalsIgnoreCase("HORA")) {
					pw.println(new Date());
				} else if (linea.equalsIgnoreCase("NOMBRE")) {
					pw.println(InetAddress.getLocalHost().getHostName());
				} else if (linea.equalsIgnoreCase("ADIOS")) {
					pw.println("Hasta pronto");
					conversacionActiva = false;
				} else {
					pw.println("No entiendo ese comando");
				}
				pw.flush(); // Enviar respuesta inmediatamente
			}
			
			// Cerramos la conexión con este cliente específico
			conexion.close();
		}
	}
}