package tarea_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UD1_B1_T1_MarioSanchez {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		if (args.length == 0) {
			File f = new File(System.getProperty("user.dir") + "/refranes.txt");
			menu(f);
		} else {
			File f = new File(args[0]);
			menu(f);
		}
	}

	/**
	 * Método que muestra el menu de opciones
	 * @param f
	 */
	public static void menu(File f) {
		System.out.println("¿Como quiere leer el archivo?");
		System.out.println("1. BufferedReader");
		System.out.println("2. FileReader");
		System.out.print(">");
		int opc = sc.nextInt();
		switch (opc) {
		case 1:
			leerBufferedReader(f);
			break;
		case 2:
			leerFileReader(f);
			break;
		default:
			break;
		}
	}

	/**
	 * Método que lee el fichero f mediante el método FileReader que se le pasa como parámetro
	 * @param f
	 */
	public static void leerFileReader(File f) {
		try {
			FileReader fr = new FileReader(f);
			int c = fr.read();

			while (c != -1) {
				System.out.print((char) c);
				c = fr.read();
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que utiliza BufferedReader para leer un archivo que pasa como parámetro (f)
	 * @param f
	 */
	public static void leerBufferedReader(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while (br.ready()) {
				System.out.println(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
