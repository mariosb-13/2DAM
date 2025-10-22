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
			System.out.print("Introduzca una vocal: ");
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
		}
		return false;
	}

	public static void copiaConVocal(File f1, File f2, char c) {
		f2 = new File(System.getProperty("user.dir") + "/refranes_CON_" + c + ".txt");

		try {
			FileReader fr = new FileReader(f1);
			FileWriter fw = new FileWriter(f2);

			int caracter;
			char vocal = Character.toLowerCase(c);

			while ((caracter = fr.read()) != -1) {
				char ch = (char) caracter;

				// Si es una vocal (considerando tildes), la reemplazamos por la vocal elegida
				if (esVocal(ch)) {
					// Mantener mayúsculas si el original lo es
					if (Character.isUpperCase(ch)) {
						fw.write(Character.toUpperCase(vocal));
					} else {
						fw.write(vocal);
					}
				} else {
					// Si no es vocal, escribimos el carácter tal cual
					fw.write(ch);
				}
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
