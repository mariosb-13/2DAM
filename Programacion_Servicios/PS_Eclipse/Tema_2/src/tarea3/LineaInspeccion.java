package tarea3;

import java.util.concurrent.Semaphore;

public class LineaInspeccion {
    // El semáforo controla cuántos pasan a la vez
    private Semaphore semaforo;

    public LineaInspeccion(int cantidadLineas) {
        this.semaforo = new Semaphore(cantidadLineas);
    }

    public void revisar(String nombre, int tiempoEnMilisegundos) {
        try {
            // Intentar entrar (resta 1 permiso)
            semaforo.acquire();
            System.out.println(nombre + " está pasando la revisión...");

            // Simular el tiempo de revisión
            Thread.sleep(tiempoEnMilisegundos);

            System.out.println(nombre + " ha TERMINADO.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Salir y liberar la línea (suma 1 permiso) para el siguiente
            semaforo.release();
        }
    }
}