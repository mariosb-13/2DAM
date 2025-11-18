package ejercicio1;

public class Main {

	public static void main(String[] args) {
		 Monitor monitor = new Monitor();
		 
		 //Hilos
		 HiloA hiloA = new HiloA(monitor);
		 HiloB hilob = new HiloB(monitor);

		 hiloA.start();
		 hilob.start();
		 
	}

}
