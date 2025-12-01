package tarea3;

import java.util.concurrent.Semaphore;

public class LineaInspeccion {
	Semaphore semaforo;
	int cantidad;

	public LineaInspeccion(Semaphore semaforo,int cantidad) {
		this.semaforo = semaforo;
		this.cantidad= cantidad;
	}
	
	
}
