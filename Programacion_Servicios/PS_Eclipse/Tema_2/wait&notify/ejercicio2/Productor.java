package ejercicio2;

public class Productor extends Thread {

	Caja caja;

	public Productor(Caja caja) {
		this.caja = caja;
	}

	@Override
	public void run() {
		try {
			System.out.println("No hay datos por favor espere...");
			sleep(2000);
			System.out.println("Productor: Se ha repuesto 42");
			this.caja.poner(42);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
