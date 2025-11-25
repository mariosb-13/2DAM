package ejercicio8;

public class Main {

	public static void main(String[] args) {
		ArchivoCompartido ac = new ArchivoCompartido();

		Lector l1 = new Lector(1, ac);
		Lector l2 = new Lector(2, ac);
		
		
		Escritor e1 = new Escritor(1, ac);
		Escritor e2 = new Escritor(2, ac);

		l1.start();
		l2.start();
	
		
		e1.start();
		e2.start();

	}

}
