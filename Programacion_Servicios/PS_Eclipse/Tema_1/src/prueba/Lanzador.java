package prueba;

import java.io.File;

public class Lanzador {
	public void lanzarSumador(int n1, int n2) {
		ProcessBuilder pb;
		try {
			String clase = "prueba.Sumador";
			
			
			pb = new ProcessBuilder("java", clase, String.valueOf(n1), String.valueOf(n2));
//			bin para que encuentre donde esta el archivo Sumador
			pb.directory(new File("bin"));
			
			
//			Estos método hace que escriba por pantalla
//			pb.inheritIO();
			
//			La salida
			pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("src/prueba/output.txt")));
//			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//			Los errores
			pb.redirectError(ProcessBuilder.Redirect.appendTo(new File("src/prueba/errores.txt")));
//			pb.redirectError(ProcessBuilder.Redirect.INHERIT);
			
			pb.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Lanzador l = new Lanzador();
		l.lanzarSumador(1, 50);
		Thread.sleep(500);
		l.lanzarSumador(51, 100);

	}
}
