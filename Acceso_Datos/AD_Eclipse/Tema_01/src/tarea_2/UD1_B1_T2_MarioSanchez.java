package tarea_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UD1_B1_T2_MarioSanchez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String ruta = System.getProperty("user.dir");
		String file = "datos.dat";

//		Menu de opciones
		System.out.println("¿Que quiere hacer?");
		System.out.println("1. Escribir");
		System.out.println("2. Leer");
		System.out.print("> ");

		int opc = sc.nextInt();
		switch (opc) {
		case 1:
			escribir(ruta, file);
			break;
		case 2:
			leer(ruta, file);
			break;
		default:
			System.out.println("No ha seleccionado ninguna opción válida");
			break;
		}
		sc.close();
	}

	/**
	 * Método que escribe números a un archivo hasta que escriba el 0 
	 * @param ruta
	 * @param nombreArchivo
	 */
	public static void escribir(String ruta, String nombreArchivo) {
		try {
			Scanner sc = new Scanner(System.in);

			File f = new File(ruta, nombreArchivo);
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));

			int num;
			do {
				System.out.print("Escriba un número(0 para salir): ");
				num = sc.nextInt();
				bw.write(num + "\n");

			} while (num != 0);

			bw.close();
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que lee un archivo linea a linea, que se le pasa el nombre y la ruta por parámetro
	 * @param ruta
	 * @param nombreArchivo
	 */
	public static void leer(String ruta, String nombreArchivo) {
		try {
			File f = new File(ruta, nombreArchivo);
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String linea;
			while ((linea=br.readLine())!=null) {
//				Asi nos aseguramos que no imprima el 0 de salida
				if (linea.equals("0")) {
					break;
				}
				System.out.println(linea);
				
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