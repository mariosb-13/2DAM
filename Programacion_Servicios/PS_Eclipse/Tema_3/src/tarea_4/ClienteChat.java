package tarea_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Escribe la ip del servidor:");
			String host = sc.next();
			System.out.println("Escribe el puerto al que te vas a conectar:");
			int puerto = sc.nextInt();
			
			sc.nextLine();

			Socket socket = new Socket(host, puerto);

			PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			if (!socket.isClosed()) {
				while (true) {
					System.out.println("Escribe mensaje");
					System.out.print(">");
					String mensaje = sc.nextLine();
					salida.println(mensaje);

				}

			} else {
				System.out.println("No se ha podido conectar con el servidor");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
