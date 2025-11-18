package ejercicio2;

public class Consumidor extends Thread {

	Caja caja;

	public Consumidor(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void run() {
		int num = this.caja.tomar();
		System.out.println("Se ha tomado " + num);
	}

}
