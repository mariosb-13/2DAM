package prueba;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LeerConf {

	public static void main(String[] args) {
		// Definir ficheros - Leer
		File f = new File(System.getProperty("user.dir") + "/conf.ini");
		// Trato de abrir Stream
		FileReader fr = null;
		if (f.canRead()) {
			try {
				fr = new FileReader(f);
				// Leer los datos
				Properties configuracion = new Properties();
				configuracion.load(fr);// Uso el Stream
				System.out.println("user = " + configuracion.getProperty("user"));
				System.out.println("password = " + configuracion.getProperty("password"));
				System.out.println("server = " + configuracion.getProperty("server"));
				System.out.println("port = " + configuracion.getProperty("port"));
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				// Por si acaso, me aseguro de cerrar el stream
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
