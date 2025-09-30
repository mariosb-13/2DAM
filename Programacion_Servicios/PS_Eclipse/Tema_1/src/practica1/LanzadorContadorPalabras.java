package practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LanzadorContadorPalabras {

    private String rutaArchivo;

    // Constructor que recibe la ruta del archivo
    public LanzadorContadorPalabras(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Método para contar palabras en el archivo
    public int contarPalabras() {
        int contador = 0;
        File archivo = new File(rutaArchivo);

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNext()) {
                scanner.next();
                contador++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaArchivo);
            return -1;
        }

        return contador;
    }

    // Método para mostrar el resultado
    public void mostrarConteo() {
        int total = contarPalabras();
        if (total != -1) {
            System.out.println("El archivo contiene " + total + " palabras.");
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Por favor, ingresa la ruta del archivo como argumento.");
                return;
            }

            String ruta = args[0];
            LanzadorContadorPalabras contador = new LanzadorContadorPalabras(ruta);
            contador.mostrarConteo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
