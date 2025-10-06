package tarea_2;

import java.io.*;
import java.util.Scanner;

public class UD1_B1_T4_MarioSanchez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ruta = System.getProperty("user.dir");
		String file = "datosFibonacci.dat";
		
		File f= new File(ruta, file); 
		int opc;

		do {

			System.out.println("Elija una opción: ");
			System.out.println("1. Escribir 10 números:");
//		Comprueba que el archivo exista y no esté vacío
			if (!f.exists() && f.getTotalSpace()!=0) {
				System.out.println("2. Mostrar con salto X");

			}
			System.out.println("3. Salir");
			System.out.print(">");
			opc = sc.nextInt();

			switch (opc) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;

			default:
				System.out.println("Opcion no encontrada");
				break;
			}

		} while (opc != 3);
	}

	public static void Escribir10() {

	}

	public static void MostrarConSalto(int salto) {

	}

}
