package practica1;

public class LanzadorPrimo {

    private int inicio;
    private int fin;

    // Constructor que recibe los dos números
    public LanzadorPrimo(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    // Método para verificar si un número es primo
    private boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    // Método para mostrar todos los primos en el rango
    public void mostrarPrimos() {
        System.out.println("Números primos entre " + inicio + " y " + fin + ":");
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Supongamos que recibimos los dos números como argumentos
            if (args.length < 2) {
                System.out.println("Por favor, ingresa dos números como argumentos.");
                return;
            }

            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);

            LanzadorPrimo lp = new LanzadorPrimo(num1, num2);
            lp.mostrarPrimos();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
