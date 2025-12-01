package tarea3;

public class Coche extends Thread{
	double tiempoPaso;

	public Coche() {
		this.tiempoPaso = 1;
	}

	@Override
	public void run() {
		System.out.println("Soy un Coche");
	}
	
	 
}
