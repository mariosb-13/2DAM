package practica3;

public class MainJoin {

	public static void main(String[] args) throws InterruptedException {
		Hilo h1 = new Hilo("H1");
		Hilo h2 = new Hilo("H2");
		
		h1.start();
		
		h1.join();
		
		h2.start();
	}
	

}
