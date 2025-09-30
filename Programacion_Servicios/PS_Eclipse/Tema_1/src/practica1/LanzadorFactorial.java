package practica1;

public class LanzadorFactorial {

    private int numero;

    // Constructor que recibe el número
    public LanzadorFactorial(int numero) {
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
        try {
            if (args.length < 1) {
                System.out.println("Por favor, ingresa un número como argumento.");
                return;
            }

            int num = Integer.parseInt(args[0]);

            if (num < 0) {
                System.out.println("No se puede calcular el factorial de un número negativo.");
                return;
            }

            LanzadorFactorial lf = new LanzadorFactorial(num);
            lf.mostrarFactorial();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
