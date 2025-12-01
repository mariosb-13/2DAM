package tarea3;

public class Camion extends Thread{
	double tiempoPaso;

	public Camion() {
		this.tiempoPaso = 1.5;
	}

	@Override
	public void run() {
		System.out.println("Soy un camión");
	}
}
