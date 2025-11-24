package ejercicio4;

public class Pong extends Thread{
	
	Pelota pelota;

	public Pong(Pelota pelota) {
		this.pelota = pelota;
	}

	@Override
	public void run() {
		try {
			this.pelota.pong();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
