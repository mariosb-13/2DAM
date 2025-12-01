package tarea3;

public class Motocicleta extends Thread{
	double tiempoPaso;

	public Motocicleta() {
		this.tiempoPaso = 0.5;
	}

	@Override
	public void run() {
		System.out.println("Soy una moto");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
