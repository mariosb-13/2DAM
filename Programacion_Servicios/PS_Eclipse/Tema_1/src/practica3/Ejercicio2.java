package practica3;

public class Ejercicio2 extends Thread {

	public static class Contador {
		public static int cont = 0;
	}

	private String nombre;

	public Ejercicio2(String nombre) {
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
		Ejercicio2 h1 = new Ejercicio2("A");
		Ejercicio2 h2 = new Ejercicio2("B");

		h1.start();
		h2.start();

		while (h1.isAlive() || h2.isAlive()) {
		}

		System.out.println("Valor final del contador: " + Contador.cont);
	}
}
