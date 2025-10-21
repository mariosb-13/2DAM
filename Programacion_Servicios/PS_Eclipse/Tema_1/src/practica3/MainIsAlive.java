package practica3;

public class MainIsAlive {

	public static void main(String[] args) {
		Hilo h1 = new Hilo("H1");
		Hilo h2 = new Hilo("H2");
		
		h1.start();
		
		while (h1.isAlive()) {
			
		}
		
		h2.start();
	}
	

}
