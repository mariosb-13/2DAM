package ejercicio4;

public class Pelota {
	boolean turnoPing = true;

	public synchronized void ping() throws InterruptedException {
		while (!turnoPing) {
			wait();
		}
		
		System.out.println("Ping");
		this.turnoPing = false;
		notifyAll();
	}

	public synchronized void pong() throws InterruptedException {
		while (turnoPing) {
			wait();
		}
		
		System.out.println("Pong");
		this.turnoPing = true;
		notifyAll();
	}
}
