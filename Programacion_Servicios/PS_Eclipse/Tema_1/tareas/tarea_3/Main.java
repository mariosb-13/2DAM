package tarea_3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        HiloCiudades hCiudades = new HiloCiudades();
        hCiudades.start();
        hCiudades.join();

        System.out.println("\nSelecciona el número de la ciudad:");
        System.out.print("> ");
        int numeroSeleccionado = sc.nextInt();

        // Obtener el nombre real de la ciudad según el número
        String ciudadSeleccionada = hCiudades.getCiudadesMostradas().get(numeroSeleccionado - 1);
        System.out.println("Ciudad seleccionada: " + ciudadSeleccionada);

        // Pasar el nombre de la ciudad al hilo de población
        HiloPoblacion hPoblacion = new HiloPoblacion(ciudadSeleccionada);
        hPoblacion.start();
        hPoblacion.join();
        
        sc.close();
    }
}
