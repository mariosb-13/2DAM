package pruebaTimbre;

public class MainTocarTimbre {

	public static void main(String[] args) throws InterruptedException {
		Timbre t = new Timbre();
		
		Invitado v1 = new Invitado(t);
		Invitado v2 = new Invitado(t);
		
		v1.start();
//		v1.join();
		
		v2.start();
	}

}
