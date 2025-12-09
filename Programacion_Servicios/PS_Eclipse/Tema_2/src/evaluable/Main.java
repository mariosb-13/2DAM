package evaluable;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//Se crea un mismo almacen para todos
		Almacen almacenMain = new Almacen();
		ProductorTablones pt1 = new ProductorTablones(almacenMain);
		
		//Se crean los 3 consumidores
		ConsumidorTablones ct1 = new ConsumidorTablones(almacenMain);
		ConsumidorTablones ct2 = new ConsumidorTablones(almacenMain);
		ConsumidorTablones ct3 = new ConsumidorTablones(almacenMain);
		
		
		pt1.start();
		
		ct1.start();
		ct2.start();
		ct3.start();		
		
	}
}
