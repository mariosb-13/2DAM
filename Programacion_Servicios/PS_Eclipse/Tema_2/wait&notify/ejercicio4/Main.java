package ejercicio4;

public class Main {

	public static void main(String[] args) {
		Pelota pelota = new Pelota();

		Ping ping = new Ping(pelota);
		Pong pong = new Pong(pelota);
		
		ping.start();
		pong.start();

	}

}
