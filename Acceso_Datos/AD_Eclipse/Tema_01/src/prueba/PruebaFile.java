package prueba;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PruebaFile {

	public static void main(String[] args) {
		// Definir ficheros – Escribir FileWriter
		File f = new File(System.getProperty("user.dir") + "/test.txt");
		
		System.out.println(f);

		if (f.canWrite()) {
			// Trato de abrir Stream
			FileWriter fw = null;
			try {
				fw = new FileWriter(f);
				// Uso el Stream
				fw.write("Esto es una linea del fichero?");
				fw.write("Y esto es otra?");
				fw.write(97);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// Cierro el Stream
				System.out.println("Escritura completada con exito");
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

