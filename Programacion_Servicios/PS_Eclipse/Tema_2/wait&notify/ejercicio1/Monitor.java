package ejercicio1;

public class Monitor {
	public synchronized void esperar() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public synchronized void despertar() {
		this.notify();
	}
}
