package ejercicio4;

public class Ping extends Thread{
	Pelota pelota;

	public Ping(Pelota pelota) {
		this.pelota = pelota;
	}

	@Override
	public void run() {
		try {
			while (true) {
				this.pelota.ping();
				sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
