package practica2;

import java.util.Scanner;

public class Ejercicio1 {

    private int numero;

    // Constructor que recibe el número
    public Ejercicio1(int numero) {
        this.numero = numero;
    }
    
    // Método para calcular el factorial
    public long calcularFactorial() {
        long factorial = 1;
        for (int i = 2; i <= numero; i++) {
            factorial *= i;
        }
        return factorial;
    }

    // Método para mostrar el factorial
    public void mostrarFactorial() {
        System.out.println("El factorial de " + numero + " es: " + calcularFactorial());
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce un número para calcular su factorial: ");
            int num = sc.nextInt();

            if (num < 0) {
                System.out.println("No se puede calcular el factorial de un número negativo.");
                return;
            }

            Ejercicio1 lf = new Ejercicio1(num);
            lf.mostrarFactorial();

        } catch (Exception e) {
            System.out.println("Error: entrada no válida.");
        }
    }
}
