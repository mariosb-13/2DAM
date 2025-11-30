package tarea2;

import java.io.FileReader;
import java.io.IOException;

// Clase que extiende de Thread
class Conteo extends Thread {
    private final String nombreFichero;

    public Conteo(String nombreFichero) { 
        this.nombreFichero = nombreFichero;
    }

    @Override
    public void run() {
        int cont = 0;
        try (FileReader fr = new FileReader(nombreFichero)) { 
            int caract;
            // Lee carácter por carácter hasta el final del archivo (-1)
            while ((caract = fr.read()) != -1) {
                cont++;
            }
            System.out.println("Número de carácteres de " + nombreFichero + " (Hilo): " + cont);
        } catch (IOException e) {
            System.err.println("Error al leer el fichero " + nombreFichero + " en hilo: " + e.getMessage());
        }
    }
}