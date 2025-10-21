package practica3;

public class Hilo extends Thread {

	private String nombre;

	public Hilo(String nombre) {
		this.nombre=nombre;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Contador.cont++;
				sleep(500);
				System.out.println("Hilo " + nombre + " ha aumentado contador a: " + Contador.cont);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Hilo " + nombre + " ha terminado.");
	}

}
