package tarea_3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InfoDominios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entrada = "";

        System.out.println("--- Analizador de Dominios e IPs ---");
        System.out.println("Escribe una URL (ej: www.google.com) o IP.");
        System.out.println("Escribe 'localhost' para salir.");

        while (true) {
            System.out.print("\nIntroduce URL o IP: ");
            entrada = scanner.nextLine();

            // Condición de salida
            if (entrada.equalsIgnoreCase("localhost")) {
                System.out.println("Cerrando programa...");
                break;
            }

            try {
                // InetAddress resuelve la información del host
                InetAddress host = InetAddress.getByName(entrada);

                System.out.println(">> Información obtenida:");
                System.out.println("   Nombre del Host: " + host.getHostName());
                System.out.println("   Dirección IP:    " + host.getHostAddress());
                System.out.println("   Nombre Canónico: " + host.getCanonicalHostName());

            } catch (UnknownHostException e) {
                System.out.println("!! Error: No se pudo encontrar el host '" + entrada + "'. Verifica que esté bien escrito.");
            }
        }
        scanner.close();
    }
}