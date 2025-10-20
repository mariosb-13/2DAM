package practica3;

public class Ejercicio1 extends Thread {

	public static class Contador {
        public static int cont = 0;
    }

    private String nombre;

    public Ejercicio1(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contador.cont++;
        }
        System.out.println("Hilo " + nombre + " ha terminado.");
    }

    public static void main(String[] args) {
        Ejercicio1 h1 = new Ejercicio1("A");
        Ejercicio1 h2 = new Ejercicio1("B");

        h1.start();
        h2.start();

        System.out.println("Valor final del contador: " + Contador.cont);
    }
}
