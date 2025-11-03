package tarea_4;

import java.util.ArrayList;

import tarea_3.HiloPoblacion;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Obteniendo ciudades de España...");
        HiloCiudades hiloCiudades = new HiloCiudades();
        hiloCiudades.start();
        hiloCiudades.join(); // Esperamos a que termine de obtener las ciudades

        ArrayList<String> ciudades = hiloCiudades.getCiudadesMostradas();

        System.out.println("\nLanzando hilos de población...\n");

        ArrayList<HiloPoblacion> hilosPoblacion = new ArrayList<>();

        for (String ciudad : ciudades) {
            HiloPoblacion h = new HiloPoblacion(ciudad);
            // Se van creando hilos
            h.start(); 
            hilosPoblacion.add(h);
        }

        // Esperar a que todos terminen
        for (HiloPoblacion h : hilosPoblacion) {
            h.join();
        }

        System.out.println("\nTodas las consultas han finalizado. Programa terminado.");
    }
}
