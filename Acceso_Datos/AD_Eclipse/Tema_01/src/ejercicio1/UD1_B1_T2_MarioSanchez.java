package ejercicio1;

import java.io.File;

public class UD1_B1_T2_MarioSanchez {

	public static void main(String[] args) {
		        // Si no se pasa ningún argumento, usar la ruta por defecto
		        String ruta;
		        if (args.length > 0) {
		            ruta = args[0];
		        } else {
		            ruta = System.getProperty("user.home") + File.separator + "Escritorio";
		        }

		        File directorio = new File(ruta);

		        // Comprobar que existe y es un directorio
		        if (!directorio.exists() || !directorio.isDirectory()) {
		            System.out.println("La ruta especificada no existe o no es un directorio: " + ruta);
		            return;
		        }

		        // Obtener los ficheros y directorios dentro
		        File[] elementos = directorio.listFiles();

		        if (elementos != null && elementos.length > 0) {
		            for (File f : elementos) {
		                if (f.isDirectory()) {
		                    System.out.println("[DIRECTORIO]  " + f.getName());
		                } else {
		                    System.out.println("[FICHERO]  " + f.getName());
		                }
		            }
		        } else {
		            System.out.println("El directorio está vacío.");
		        }
		    }
	}