package pruebaTimbre;

public class Invitado extends Thread{

	Timbre timbre;
	
	
	
	public Invitado(Timbre timbre) {
		this.timbre = timbre;
	}



	@Override
	public void run() {
		this.timbre.tocar();
	}

	
}
