package tarea_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {
		Scanner userInput = new Scanner(System.in);
		InetSocketAddress direccion = new InetSocketAddress("127.0.0.1", 5000);
		Socket socket = new Socket();
		socket.connect(direccion);
		
		BufferedReader bfr = Cliente.getFlujo(socket.getInputStream());
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		
		// Leer el mensaje de bienvenida del servidor antes de escribir nada
		System.out.println("Servidor: " + bfr.readLine());
		
		System.out.println("Escribe tus comandos (HORA, NOMBRE, ADIOS): ");
		
		boolean continuar = true;
		
		// Bucle para mantener la charla
		while(continuar) {
			String respuesta = userInput.nextLine();
			
			pw.println(respuesta);
			pw.flush();
			

			if (respuesta.equalsIgnoreCase("ADIOS")) {
				continuar = false;
			}
			
			// Leemos la respuesta del servidor
			String resultado = bfr.readLine();
			System.out.println(resultado);
			
			System.out.println("Vuelve a escribir tus comandos (HORA, NOMBRE, ADIOS): ");

		}
		
		socket.close();
		userInput.close();
	}

	public static BufferedReader getFlujo(InputStream is) {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		return bfr;
	}
}