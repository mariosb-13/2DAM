package practica1;

public class LanzadorTablaMulti {

    private int numero;

    // Constructor que recibe el número
    public LanzadorTablaMulti(int numero) {
        this.numero = numero;
    }

    // Método para mostrar la tabla de multiplicar
    public void mostrarTabla() {
        System.out.println("Tabla de multiplicar del " + numero + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Por favor, ingresa un número como argumento.");
                return;
            }

            int num = Integer.parseInt(args[0]);
            LanzadorTablaMulti tabla = new LanzadorTablaMulti(num);
            tabla.mostrarTabla();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
