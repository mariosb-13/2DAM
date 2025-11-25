package tarea2;

import java.io.FileReader;
import java.io.IOException;

public class Ficheros {
	public static void main(String[] args) throws InterruptedException {
		if (args.length == 0) {
			System.err.println("Debe introducir argumentos al programa");
		} else {

			for (String fichero : args) {
				int cont = 0;
				try {
					FileReader fr;
					int caract;
					fr = new FileReader(fichero);
					caract = fr.read();
					while (caract != -1) {
						caract = fr.read();
						cont++;
					}
					System.out.println("Número de carácteres de " + fichero + ": " + cont);
					
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
