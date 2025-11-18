package ejercicio2;

public class Main {

	public static void main(String[] args) {
		Caja caja = new Caja();

		Productor p = new Productor(caja);
		Consumidor c = new Consumidor(caja);

		c.start();
		p.start();
	}

}
