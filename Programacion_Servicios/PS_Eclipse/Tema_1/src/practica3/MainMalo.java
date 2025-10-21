package practica3;

public class MainMalo {

	public static void main(String[] args) throws InterruptedException {
		Hilo h1 = new Hilo("H1");
		Hilo h2 = new Hilo("H2");
		
		h1.start();		
		h2.start();
	}
	

}
