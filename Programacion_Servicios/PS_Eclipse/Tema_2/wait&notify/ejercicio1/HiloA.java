package ejercicio1;

public class HiloA extends Thread {
	private Monitor monitor;

	public HiloA(Monitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		System.out.println("Hilo A esperando...");
		monitor.esperar();
		System.out.println("Hilo A despertado");
	}

}
