package ejercicio1;

import java.io.File;

public class Lanzador {
	public void lanzarSumador(int n1, int n2) {
		ProcessBuilder pb;
		try {
			String clase = "prueba.App";

			pb = new ProcessBuilder("java", clase, String.valueOf(n1), String.valueOf(n2));
			
			
			pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("src/prueba/output.txt")));
			
			pb.redirectError(ProcessBuilder.Redirect.appendTo(new File("src/prueba/errores.txt")));

			pb.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Lanzador l = new Lanzador();

	}
}
