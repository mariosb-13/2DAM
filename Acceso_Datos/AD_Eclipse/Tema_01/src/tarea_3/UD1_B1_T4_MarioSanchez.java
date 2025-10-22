package tarea_3;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class UD1_B1_T4_MarioSanchez {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            File f = new File(System.getProperty("user.home") + File.separator + "AD" + File.separator + "misPropiedades.props");

            // Crear carpeta AD si no existe
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            menu(f);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void menu(File f) throws InterruptedException {
        int opc;
        do {
            System.out.println("\n===== MENÚ DE PROPIEDADES =====");
            System.out.println("1. Ver propiedades");
            System.out.println("2. Introducir propiedad");
            System.out.println("3. Eliminar propiedad");
            System.out.println("4. Salir");
            System.out.print("> ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, introduce un número válido: ");
                sc.next();
            }
            opc = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opc) {
                case 1:
                    MostrarPropiedades(f);
                    break;
                case 2:
                    IntroducirPropiedad(f);
                    break;
                case 3:
                    EliminarPropiedad(f);
                    break;
                case 4:
                    System.out.print("Saliendo del programa");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.println(".");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opc != 4);
    }

    // Muestra todas las propiedades del archivo
    public static void MostrarPropiedades(File f) {
        Properties p = cargar(f);
        if (p.isEmpty()) {
            System.out.println("No hay propiedades guardadas.");
        } else {
            System.out.println("Propiedades actuales:");
            for (String key : p.stringPropertyNames()) {
                System.out.println(key + " = " + p.getProperty(key));
            }
        }
    }

    /**
     * Introduce o actualiza una propiedad
     * @param f
     */
    public static void IntroducirPropiedad(File f) {
        Properties p = cargar(f);
        System.out.print("Introduce el nombre de la propiedad: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el valor: ");
        String valor = sc.nextLine();

        p.setProperty(nombre, valor);
        guardar(p, f);
        System.out.println("Propiedad guardada correctamente.");
    }

    /**
     * Elimina una propiedad si existe
     * @param f
     */
    public static void EliminarPropiedad(File f) {
        Properties p = cargar(f);
        System.out.print("Introduce el nombre de la propiedad a eliminar: ");
        String nombre = sc.nextLine();

        if (p.containsKey(nombre)) {
            p.remove(nombre);
            guardar(p, f);
            System.out.println("Propiedad eliminada correctamente.");
        } else {
            System.out.println("No existe una propiedad con ese nombre.");
        }
    }

    /**
     * Carga las propiedades desde el archivo (si no existe, devuelve un objeto vacío)
     * @param f
     * @return
     */
    private static Properties cargar(File f) {
        Properties p = new Properties();
        if (f.exists()) {
            try (FileInputStream fis = new FileInputStream(f)) {
                p.load(fis);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de propiedades: " + e.getMessage());
            }
        }
        return p;
    }

    /**
     * Guarda las propiedades en el archivo
     * @param p
     * @param f
     */
    private static void guardar(Properties p, File f) {
        try (FileOutputStream fos = new FileOutputStream(f)) {
            p.store(fos, "Archivo de propiedades de usuario");
        } catch (IOException e) {
            System.out.println("Error al guardar las propiedades: " + e.getMessage());
        }
    }
}
