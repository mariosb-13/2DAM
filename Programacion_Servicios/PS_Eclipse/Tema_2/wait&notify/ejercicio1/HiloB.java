package ejercicio1;

public class HiloB extends Thread {
	private Monitor monitor;

	public HiloB(Monitor monitor) {
		super();
		this.monitor = monitor;
	}

	@Override
	public void run() {
		try {
			sleep(2000);
			System.out.println("Hilo B avisando");
			this.monitor.despertar();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
