package tarea_1;

import java.io.File;
import java.io.IOException;

public class UD1_B1_T3_MarioSanchez {
	public static void main(String[] args) {
		String ruta;

		if (args.length > 0) {
			ruta = args[0];
		} else {
			// Carpeta por defecto
			ruta = System.getProperty("user.home") + File.separator + "AD";
			File dir = new File(ruta);
			if (!dir.exists()) {
				dir.mkdirs(); // crea el directorio si no existe
			}
			// Archivo de ejemplo
			File ejemplo = new File(dir, "ejemplo.txt");
			if (!ejemplo.exists()) {
				try {
					if (ejemplo.createNewFile()) {
						System.out.println("Archivo de ejemplo creado en: " + ejemplo.getAbsolutePath());
					}
				} catch (IOException e) {
					System.out.println("No se pudo crear el archivo de ejemplo: " + e.getMessage());
				}
			}
			ruta = ejemplo.getPath();
		}

		File fichero = new File(ruta);

		if (!fichero.exists()) {
			System.out.println("El fichero o directorio no existe: " + ruta);
			return;
		}

		System.out.println("Información del fichero/directorio:	\n");
		System.out.println("Nombre: 		" + fichero.getName());
		System.out.println("Ruta: 			" + fichero.getPath());
		System.out.println("Ruta absoluta: 		" + fichero.getAbsolutePath());
		System.out.println("Es directorio: 		" + fichero.isDirectory());
		System.out.println("Es fichero: 		" + fichero.isFile());
		System.out.println("Se puede leer: 		" + fichero.canRead());
		System.out.println("Se puede escribir: 	" + fichero.canWrite());
		System.out.println("Tamaño (bytes): 	" + fichero.length());
		System.out.println("Directorio padre: 	" + fichero.getParent());
	}
}
