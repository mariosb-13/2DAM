package practica2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerFichero {

    public static void main(String[] args) {
        // Ruta relativa desde la carpeta del proyecto
        String rutaFija = "src/practica2/carpetas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFija))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); // salida directa en consola
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
