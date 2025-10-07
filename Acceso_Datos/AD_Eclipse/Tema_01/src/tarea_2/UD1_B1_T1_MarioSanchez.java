package tarea_2;

import java.io.*;

public class UD1_B1_T1_MarioSanchez {

    public static void main(String[] args) {
        File dirActual = new File(System.getProperty("user.dir"));
        File original = new File(dirActual, "EstoDefinitivamenteNoEsUnVirus.jar");

        if (!original.exists()) {
            System.out.println("No se encontró el archivo: " + original.getName());
            return;
        }

        // Determinar desde qué número continuar
        int numInicio = 1;
        File[] archivos = dirActual.listFiles();
        if (archivos != null) {
            for (File f : archivos) {
                String nombre = f.getName();
                if (nombre.startsWith("EstoDefinitivamenteNoEsUnVirus_COPIA") && nombre.endsWith(".jar")) {
                    // extraer el número de copia
                    try {
                        int num = Integer.parseInt(nombre.replaceAll("\\D+", ""));
                        if (num >= numInicio) numInicio = num + 1;
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        // Crear 10 copias nuevas
        for (int i = 0; i < 10; i++) {
            File copia = new File(dirActual, "EstoDefinitivamenteNoEsUnVirus_COPIA" + (numInicio + i) + ".jar");
            copia(original, copia);
            System.out.println("Copia creada: " + copia.getName());
        }

        System.out.println("Proceso completado. Se han creado 10 nuevas copias.");
    }

    /**
     * Copia el contenido binario de un archivo a otro usando buffers.
     * @param origen
     * @param destino
     */
    public static void copia(File origen, File destino) {
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(origen));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destino))
        ) {
            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesLeidos);
            }
        } catch (IOException e) {
            System.err.println("Error copiando " + origen.getName() + " → " + destino.getName());
            e.printStackTrace();
        }
    }
}
