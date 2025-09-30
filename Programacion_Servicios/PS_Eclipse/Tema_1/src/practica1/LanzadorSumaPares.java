package practica1;

public class LanzadorSumaPares {

    private int inicio;
    private int fin;

    // Constructor que recibe los dos números
    public LanzadorSumaPares(int inicio, int fin) {
        // Aseguramos que inicio sea menor que fin
        if (inicio > fin) {
            int temp = inicio;
            inicio = fin;
            fin = temp;
        }
        this.inicio = inicio;
        this.fin = fin;
    }

    // Método para sumar los números pares en el rango
    public int sumarPares() {
        int suma = 0;
        for (int i = inicio; i <= fin; i++) {
            if (i % 2 == 0) {
                suma += i;
            }
        }
        return suma;
    }

    // Método para mostrar el resultado
    public void mostrarSuma() {
        System.out.println("La suma de los números pares entre " + inicio + " y " + fin + " es: " + sumarPares());
    }

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Por favor, ingresa dos números como argumentos.");
                return;
            }

            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);

            LanzadorSumaPares sp = new LanzadorSumaPares(num1, num2);
            sp.mostrarSuma();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
