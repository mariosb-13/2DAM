package tarea_1;

import java.io.File;
import java.io.IOException;

public class UD1_B1_T4_MarioSanchez {
	public static void main(String[] args) {
		try {
			File nuevoDir = new File("NUEVODIR");
			if (!nuevoDir.exists()) {
				if (nuevoDir.mkdir()) {
					System.out.println("Directorio creado: " + nuevoDir.getAbsolutePath());
				} else {
					System.out.println("No se pudo crear el directorio.");
					return;
				}
			} else {
				System.out.println("El directorio ya existía: " + nuevoDir.getAbsolutePath());
			}

			File fichero1 = new File(nuevoDir, "fichero1.txt");
			File fichero2 = new File(nuevoDir, "fichero2.txt");

			if (fichero1.createNewFile()) {
				System.out.println("Fichero creado: " + fichero1.getName());
			} else {
				System.out.println("El fichero ya existía: " + fichero1.getName());
			}

			if (fichero2.createNewFile()) {
				System.out.println("Fichero creado: " + fichero2.getName());
			} else {
				System.out.println("El fichero ya existía: " + fichero2.getName());
			}

			File ficheroRenombrado = new File(nuevoDir, "fichero1_renombrado.txt");
			if (fichero1.renameTo(ficheroRenombrado)) {
				System.out.println("Fichero renombrado a: " + ficheroRenombrado.getName());
			} else {
				System.out.println("No se pudo renombrar el fichero: " + fichero1.getName());
			}

			if (fichero2.delete()) {
				System.out.println("Fichero borrado: " + fichero2.getName());
			} else {
				System.out.println("No se pudo borrar el fichero: " + fichero2.getName());
			}

		} catch (IOException e) {
			System.out.println("Error de entrada/salida: " + e.getMessage());
		} catch (SecurityException e) {
			System.out.println("Error de permisos: " + e.getMessage());
		}
	}
}

/*
 * ¿Dónde se ha creado el directorio si se ejecuta desde un IDE? Se crea en el
 * directorio de trabajo actual del proyecto.
 */
