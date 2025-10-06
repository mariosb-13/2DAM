package tarea_1;

import java.io.File;

public class UD1_B1_T6_MarioSanchez {

	public static void main(String[] args) {
		String ruta;
		if (args.length > 0) {
			ruta = args[0];
		} else {
			ruta = System.getProperty("user.home") + File.separator + "Desktop";
		}

		File dir = new File(ruta);

		if (!dir.exists() || !dir.isDirectory()) {
			System.out.println("La ruta especificada no existe o no es un directorio: " + ruta);
			return;
		}

		listarDirectorio(dir, 0);
	}

	/**
	 * Lista el contenido de un directorio de forma recursiva y jerárquica
	 * 
	 * @param dir   Directorio a listar
	 * @param nivel Nivel de profundidad
	 */
	public static void listarDirectorio(File dir, int nivel) {
		File[] elementos = dir.listFiles();
		if (elementos == null)
			return;

		String indent = "    ".repeat(nivel);

		for (File f : elementos) {
			if (f.isFile()) {
				System.out.println(indent + f.getName());
			} else if (f.isDirectory()) {
				System.out.println(indent + "/" + f.getName());
				listarDirectorio(f, nivel + 1);
			}
		}
	}
}
