package tarea_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {
	public static void main(String[] args) {
		int puerto = 5000;

		try (ServerSocket servidor = new ServerSocket(puerto)) {
			System.out.println("Servidor Iniciado: Escuchando en el puerto " + puerto);

			while (true) {
				Socket socketCliente = servidor.accept();
				System.out.println("Cliente conectado desde: " + socketCliente.getLocalSocketAddress());

				BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

				String mensajeRecibido = entrada.readLine();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
