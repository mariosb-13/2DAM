package tarea_2;

import java.io.*;
import java.util.regex.*;

public class UD1_B1_T1_MarioSanchez {

    // Método copia que usa buffering tanto en lectura como en escritura
    public static void copia(File origen, File destino) throws IOException {
        // Asegurarse de que el directorio destino existe
        File parent = destino.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException("No se pudo crear el directorio: " + parent);
            }
        }

        try (
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(origen));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destino))
        ) {
            byte[] buffer = new byte[8192]; // 8KB buffer
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        }
    }

    public static void main(String[] args) {
        String targetName = "EstoDefinitivamenteNoEsUnVirus.jar";
        File cwd = new File(System.getProperty("user.dir"));
        File targetFile = new File(cwd, targetName);

        if (!targetFile.exists()) {
            System.out.println("No se encontró el fichero \"" + targetName + "\" en: " + cwd.getAbsolutePath());
            System.out.println("Coloca el fichero y vuelve a ejecutar el programa.");
            return;
        }

        // Buscar copias existentes para continuar la numeración
        // Buscamos archivos con patrón: EstoDefinitivamenteNoEsUnVirus_COPIA<number>.jar
        final String base = targetName.substring(0, targetName.length() - ".jar".length()); // sin .jar
        final Pattern p = Pattern.compile(Pattern.quote(base) + "_COPIA(\\d+)\\.jar", Pattern.CASE_INSENSITIVE);

        String[] files = cwd.list();
        int maxExisting = 0;
        if (files != null) {
            for (String f : files) {
                Matcher m = p.matcher(f);
                if (m.matches()) {
                    try {
                        int n = Integer.parseInt(m.group(1));
                        if (n > maxExisting) maxExisting = n;
                    } catch (NumberFormatException e) {
                    	e.printStackTrace();
                    }
                }
            }
        }

        int start = maxExisting + 1;
        int copiesToMake = 10;

        System.out.println("Origen: " + targetFile.getAbsolutePath());
        System.out.println("Se encontraron " + maxExisting + " copias existentes. Empezando en COPIA" + start);
        for (int i = 0; i < copiesToMake; i++) {
            int copiaNum = start + i;
            String destName = base + "_COPIA" + copiaNum + ".jar";
            File destFile = new File(cwd, destName);

            // Si por alguna razón ya existe (p. ej. concurrencia), saltar o sobreescribir
            if (destFile.exists()) {
                System.out.println("El fichero destino ya existe (se sobreescribirá): " + destFile.getName());
            }

            try {
                copia(targetFile, destFile);
                System.out.println("Creada copia: " + destFile.getName());
            } catch (IOException ex) {
                System.err.println("Error al copiar a " + destFile.getName() + ": " + ex.getMessage());
            }
        }

        System.out.println("Operación finalizada. Se han intentado crear " + copiesToMake + " copias (numeración continua).");
    }
}
