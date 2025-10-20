package tarea_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UD1_B1_T2_MarioSanchez {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		File f = new File(System.getProperty("user.dir") + "/refranes.txt");
		File f2 = null;

		if (!f.exists()) {
			System.out.println("No se ha encontrado el archivo especificado");
		} else {
			System.out.println("Introduzca una vocal: ");
			char vocal = sc.next().charAt(0);

			if (esVocal(vocal)) {
				copiaConVocal(f, f2, vocal);
			}

			sc.close();

		}

	}

	public static boolean esVocal(char c) {
		if (c == 'a' | c == 'e' | c == 'i' | c == 'o' | c == 'u' | c == 'á' | c == 'é' | c == 'í' | c == 'ó'
				| c == 'ú') {
			return true;
		} else {
			System.out.println("No se ha introducido una vocal");
			return false;
		}
	}

	public static void copiaConVocal(File f1, File f2, char c) {
		f2 = new File(System.getProperty("user.dir") + "/refranes_CON_" + c + ".txt");

		try {
			FileReader fr = new FileReader(f1);
			FileWriter fw = new FileWriter(f2);

			StringBuilder linea = new StringBuilder();
			int caracter;
			boolean contieneVocal = false;
			char vocal = Character.toLowerCase(c);

			// Leemos carácter a carácter
			while ((caracter = fr.read()) != -1) {
				char ch = (char) caracter;
				linea.append(ch);

				// Si el carácter es una vocal (comparamos en minúsculas)
				if (Character.toLowerCase(ch) == vocal) {
					contieneVocal = true;
				}

				// Cuando llegamos al final de una línea o del archivo
				if (ch == '\n') {
					// Si la línea contiene la vocal, la escribimos en el nuevo archivo
					if (contieneVocal) {
						fw.write(linea.toString());
					}
					// Reiniciamos para la siguiente línea
					linea.setLength(0);
					contieneVocal = false;
				}
			}

			// Caso especial: última línea si no termina en salto de línea
			if (linea.length() > 0 && contieneVocal) {
				fw.write(linea.toString());
			}

			fr.close();
			fw.close();

			System.out.println("Archivo creado correctamente: " + f2.getName());

		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo original.");
		} catch (IOException e) {
			System.out.println("Error al procesar el archivo: " + e.getMessage());
		}
	}

}
