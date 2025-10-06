package tarea_1;

import java.io.File;

public class UD1_B1_T5_MarioSanchez {
	public static void main(String[] args) {
		File nuevoDir = new File("NUEVODIR");

		if (!nuevoDir.exists()) {
			System.out.println("El directorio " + nuevoDir.getName() + " no existe en el directorio actual.");
			return;
		}

		// Llamada recursiva para borrar
		if (borrarRecursivo(nuevoDir)) {
			System.out.println("Directorio '" + nuevoDir.getName() + "' borrado correctamente.");
		} else {
			System.out.println("No se pudo borrar completamente el directorio '" + nuevoDir.getName() + "'.");
		}
	}

	/**
	 * Borra un fichero o directorio recursivamente.
	 * 
	 * @param file Archivo o directorio a borrar
	 * @return true si se ha borrado correctamente, false si ha fallado en algún
	 *         punto
	 */
	private static boolean borrarRecursivo(File file) {
		boolean flag = true;

		if (file.isDirectory()) {
			File[] contenido = file.listFiles();
			if (contenido != null) {
				for (File f : contenido) {
					flag &= borrarRecursivo(f);
				}
			}
		}

		if (file.delete()) {
			System.out.println("Eliminado: " + file.getAbsolutePath());
		} else {
			System.out.println("No se pudo eliminar: " + file.getAbsolutePath());
			flag = false;
		}

		return flag;
	}
}
