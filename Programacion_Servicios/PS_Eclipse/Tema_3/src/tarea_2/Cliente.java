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
		System.out.println("Introduzca lo que quiera decirle al servidor: ");

		BufferedReader bfr = Cliente.getFlujo(socket.getInputStream());
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		String respuesta = userInput.nextLine();
		
		pw.print(respuesta);
		pw.print("\n");
		pw.flush();
		String resultado = bfr.readLine();
		System.out.println("El resultado fue:" + resultado);
		socket.close();
	}

	public static BufferedReader getFlujo(InputStream is) {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		return bfr;
	}
}