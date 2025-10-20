package prueba;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class EscribirConf {

	public static void main(String[] args) {
		// Definir ficheros - Escribir
		File f = new File(System.getProperty("user.dir") + "/conf.ini");
		// Trato de abrir Stream
		FileWriter fw = null;
		if (f.canWrite()) {
			try {
				fw = new FileWriter(f);
				// Cargo los datos
				Properties configuracion = new Properties();
				configuracion.setProperty("user", "usuario");
				configuracion.setProperty("password", "micontrasena");
				configuracion.setProperty("server", "localhost");
				configuracion.setProperty("port", "3306");
				// Uso el Stream
				configuracion.store(fw, "Fichero de configuracion");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				// Por si acaso, me aseguro de cerrar el stream
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
