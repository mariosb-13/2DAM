package tarea2;

import java.util.ArrayList;
import java.util.List;

public class Ficheros {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.err.println("Debe introducir argumentos al programa");
            return;
        }

        // Lista para guardar las referencias a todos los hilos (que son instancias de Conteo)
        List<Conteo> hilos = new ArrayList<>();

        // --- INICIO CÓDIGO DE MEDICIÓN DE TIEMPO (Apartado 3) ---
        long t_comienzo, t_fin;
        t_comienzo = System.currentTimeMillis();
        // --------------------------------------------------------

        for (String fichero : args) {
            Conteo hilo = new Conteo(fichero);
            hilo.start(); 
            hilos.add(hilo);
        }

     
        for (Conteo hilo : hilos) {
            hilo.join(); // Espera a que el hilo termine su ejecución
        }

        t_fin = System.currentTimeMillis();
        long tiempototal = t_fin - t_comienzo;
        System.out.println("\n*** Comparación de Tiempos ***");
        System.out.println("Tiempo total de ejecución (Concurrente): " + tiempototal + " ms");
        // ----------------------------------------------------
    }
}