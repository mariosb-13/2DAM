package practica2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EscribirFicheroPerfil {

    public static void main(String[] args) {
        String rutaPerfil = System.getProperty("user.home");

        // Formateo de fecha-hora
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        String fecha = LocalDateTime.now().format(formato);

        // Nombre del archivo
        String nombreArchivo = "content_" + fecha + ".log";

        File archivo = new File(nombreArchivo);

        try (FileWriter fw = new FileWriter(archivo)) {
            File perfil = new File(rutaPerfil);
            File[] lista = perfil.listFiles();

            if (lista != null) {
                for (File f : lista) {
                    fw.write(f.getName());
                    fw.write(System.lineSeparator());
                }
            }

            System.out.println("Contenido guardado en " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
