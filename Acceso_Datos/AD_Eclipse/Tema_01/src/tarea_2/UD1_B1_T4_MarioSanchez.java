package tarea_2;

import java.io.*;
import java.util.Scanner;

public class UD1_B1_T4_MarioSanchez {

	static String fileName = "datosFibonacci.dat";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		File f = new File(fileName);
		int opc;

		do {
			int cantidad = contarNumeros(f);

			System.out.println("\nActualmente hay " + cantidad + " números guardados");
			System.out.println("1. Escribir 10 números");

			// Se muestra la opción 2 si el archivo existe y no está vacío
			if (f.exists() && f.length() > 0) {
				System.out.println("2. Mostrar con salto X");
			}
			System.out.println("3. Salir");
			System.out.print("> ");
			opc = sc.nextInt();

			switch (opc) {
			case 1:
				Escribir10();
				break;
			case 2:
				if (f.exists() && f.length() > 0) {
					System.out.print("Introduce el salto X: ");
					int salto = sc.nextInt();
					MostrarConSalto(salto);
				} else {
					System.out.println("No hay datos para mostrar.");
				}
				break;
			case 3:
				System.out.println("Saliendo...");
				break;

			default:
				System.out.println("Opción no válida.");
				break;
			}

		} while (opc != 3);
		sc.close();
	}

	/**
	 * Genera 10 numeros y los vuelca en el archivo los números
	 */
	public static void Escribir10() {
		try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
			long numElementos = raf.length() / 8; // Cada long ocupa 8 bytes
			long a, b;

			if (numElementos == 0) {
				// Si el archivo está vacío, escribimos los dos primeros números
				a = 0;
				b = 1;
				raf.writeLong(a);
				raf.writeLong(b);
				numElementos = 2;
			} else {
				// Nos posicionamos en los dos últimos números
				raf.seek((numElementos - 2) * 8);
				a = raf.readLong();
				b = raf.readLong();
			}

			// Escribimos 10 nuevos números
			for (int i = 0; i < 10; i++) {
				long c = a + b;
				raf.writeLong(c);
				a = b;
				b = c;
			}

			System.out.println("Se han escrito 10 nuevos números en el archivo.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra los números del archivo saltando de X en X posiciones.
	 */
	public static void MostrarConSalto(int salto) {
		try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
			long total = raf.length() / 8;
			System.out.println("\nContenido del archivo (salto " + salto + "):");

			for (int i = 0; i < total; i += salto) {
				raf.seek(i * 8);
				long n = raf.readLong();
				System.out.println("Posición " + i + ": " + n);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cuenta cuántos números hay guardados actualmente en el archivo.
	 */
	public static int contarNumeros(File f) {
		if (!f.exists())
			return 0;
		return (int) (f.length() / 8); // Cada número (long) ocupa 8 bytes
	}
}
