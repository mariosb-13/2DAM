package tarea_7;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.Arrays;

public class Hilo2 extends Thread {

	private final PipedInputStream pis;

	public Hilo2(PipedInputStream pis) {
		this.pis = pis;
	}

	@Override
	public void run() {
		try {
			StringBuilder sb = new StringBuilder();
			int dato;

			// Leer todos los datos enviados por Hilo1
			while ((dato = pis.read()) != -1) {
				sb.append((char) dato);
			}
			pis.close();

			// Convertir la cadena a números
			String[] partes = sb.toString().trim().split("\\s+");
			int[] numeros = new int[partes.length];
			for (int i = 0; i < partes.length; i++) {
				numeros[i] = Integer.parseInt(partes[i]);
			}

			// Ordenar los números
			Arrays.sort(numeros);

			System.out.println("Números ordenados:");
			for (int n : numeros) {
				System.out.print(n + " ");
			}
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
