package tarea_2;

import java.io.*;
import java.util.Scanner;

public class UD1_B1_T3_MarioSanchez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ruta = System.getProperty("user.dir");
		String nombreArchivo = "datosMatriz.dat";

		double[][] matriz = pedirDatosMatriz();
		escribirMatriz(ruta, nombreArchivo, matriz);

		System.out.println("\nMatriz escrita en el archivo correctamente.\n");

		double[][] matrizLeida = leerMatriz(ruta, nombreArchivo);

		System.out.println("Matriz leída desde el archivo:");
		mostrarMatriz(matrizLeida);

		sc.close();
	}

	/**
	 * Pide cuantas filas y columnas y posteriormente rellena la matriz con double
	 * @return matriz[i][j]
	 */
	public static double[][] pedirDatosMatriz() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el número de filas: ");
		int filas = sc.nextInt();
		System.out.print("Introduce el número de columnas: ");
		int columnas = sc.nextInt();

		double[][] matriz = new double[filas][columnas];

		System.out.println("\nIntroduce los valores de la matriz:");
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print("Elemento [" + i + "][" + j + "]: ");
				matriz[i][j] = sc.nextDouble();
			}
		}
		sc.close();
		return matriz;
	}

	/**
	 * 
	 * @param ruta
	 * @param nombreArchivo
	 * @param matriz
	 */
	public static void escribirMatriz(String ruta, String nombreArchivo, double[][] matriz) {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta + File.separator + nombreArchivo))) {

			int filas = matriz.length;
			int columnas = matriz[0].length;

			dos.writeInt(filas);
			dos.writeInt(columnas);

			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					dos.writeDouble(matriz[i][j]);
				}
			}

		} catch (IOException e) {
			System.out.println("Error al escribir la matriz: " + e.getMessage());
		}
	}

	/**
	 * Método que lee el archivo a partir de una ruta y un nombre
	 * @param ruta
	 * @param nombreArchivo
	 * @return
	 */
	public static double[][] leerMatriz(String ruta, String nombreArchivo) {
		double[][] matriz = null;

		try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta + File.separator + nombreArchivo))) {

			int filas = dis.readInt();
			int columnas = dis.readInt();

			matriz = new double[filas][columnas];

			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					matriz[i][j] = dis.readDouble();
				}
			}

		} catch (IOException e) {
			System.out.println("Error al leer la matriz: " + e.getMessage());
		}

		return matriz;
	}

	/**
	 * Muestra la matriz por pantalla
	 * @param matriz
	 */
	public static void mostrarMatriz(double[][] matriz) {
		if (matriz == null) {
			System.out.println("La matriz está vacía o no se pudo leer.");
			return;
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.printf("%8.2f", matriz[i][j]);
			}
			System.out.println();
		}
	}
}
