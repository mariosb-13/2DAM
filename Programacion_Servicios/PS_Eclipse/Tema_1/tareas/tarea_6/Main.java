package tarea_6;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "tarea_6.Argumentos");

			// Agregamos los argumentos pasados a este programa
			if (args.length > 0) {
				for (String arg : args) {
					pb.command().add(arg);
				}
			}
					
			pb.directory(new File("bin"));
			pb.inheritIO();


			Process p = pb.start();
			int exitCode = p.waitFor(); // Espera a que termine

			// Mostrar el resultado según el código de salida
			switch (exitCode) {
			case 0:
				System.out.println("El programa finalizó correctamente (código 0).");
				break;
			case 1:
				System.out.println("Error: No se proporcionaron argumentos (código 1).");
				break;
			case 2:
				System.out.println("Error: El argumento no es un número entero (código 2).");
				break;
			case 3:
				System.out.println("Error: El número entero es menor que 0 (código 3).");
				break;
			default:
				System.out.println("Error desconocido (código " + exitCode + ").");
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
