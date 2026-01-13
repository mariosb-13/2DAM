package tarea_1;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteWeb2 {
	public static void main(String[] args) {
		System.out.println("Iniciando...");
		String destino = "192.168.12.218";
		int puertoDestino = 80;
		Socket socket = new Socket();
		//Montamos la dirección con la IP y el Puerto
		InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);
		try {
			socket.connect(direccion);
			System.out.println("Conexión establecida con exito");
			// Si llegamos aquí es que la conexión
			// sí se hizo.

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// Flujos que manejan caracteres
			InputStreamReader isr = new InputStreamReader(is);
			OutputStreamWriter osw = new OutputStreamWriter(os);

			// Flujos de líneas
			BufferedReader bReader = new BufferedReader(isr);
			PrintWriter pWriter = new PrintWriter(osw);

			pWriter.println("GET /otraPagina.html");
			pWriter.flush();
			String linea;
			FileWriter escritorArchivo = new FileWriter("otraPagina.html");
			while ((linea = bReader.readLine()) != null) {
				escritorArchivo.write(linea);
			}
			
			escritorArchivo.close();

			
			pWriter.close();
			bReader.close();
			socket.close();
			isr.close();
			osw.close();
			is.close();
			os.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
