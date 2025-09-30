package practica1;

import java.io.IOException;

public class LanzadorPaint {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("mspaint");

            pb.start();
            System.out.println("Paint se ha abierto correctamente.");
        } catch (IOException e) {
            System.out.println("Error al intentar abrir Paint.");
            e.printStackTrace();
        }
    }
}
