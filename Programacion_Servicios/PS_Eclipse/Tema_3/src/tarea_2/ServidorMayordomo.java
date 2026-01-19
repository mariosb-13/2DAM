package tarea_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMayordomo {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Arrancado el servidor en el puerto 5000");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(5000);
		} catch (IOException e) {
			System.out.println("No se pudo poner un socket " + "a escuchar en TCP 5000");
			return;
		}
		while (true) {
			Socket conexion = socketEscucha.accept();
			System.out.println("Conexion recibida!");
			//Debemos mandarle esto al cliente
			System.out.println("Hola, soy tu servidor. ¿En qué puedo ayudarte?");
			InputStream is = conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(isr);
			String linea = bf.readLine();
			
			System.out.println(linea);
			if (linea.equalsIgnoreCase("hora")) {
				System.out.println("Pusiste Hora");
			}else if (linea.equalsIgnoreCase("nombre")) {
				System.out.println("Pusiste nombre");
			}else if (linea.equalsIgnoreCase("adios")) {
				System.out.println("pusiste adios");
			}else {
				System.out.println("No lo entendí");
			}

			OutputStream os = conexion.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("Soy el servidor");
			pw.flush();
		}
	}
}
