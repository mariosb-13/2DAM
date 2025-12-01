package tarea3;

import java.util.concurrent.Semaphore;

public class ITV {
	public static void main(String[] args) {
		Semaphore sf = new Semaphore(1);
		LineaInspeccion li = new LineaInspeccion(sf, 24);
		int cont=1;
		do {
			
			cont++;
		} while (cont<24);
	}
}
