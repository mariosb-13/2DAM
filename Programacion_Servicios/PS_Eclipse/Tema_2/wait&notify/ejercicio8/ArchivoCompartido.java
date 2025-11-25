package ejercicio8;

public class ArchivoCompartido {

	private int numLectores;
	private boolean estaEscribiendo;

	public ArchivoCompartido() {
		this.numLectores = 1;
		this.estaEscribiendo = false;
	}

	public synchronized void comenzarLeer() throws InterruptedException {
		while (estaEscribiendo) {
			System.out.println("Alguien esta escribiendo... Me espero");
			wait();
		}
		System.out.println("Comienzo lectura");
		numLectores++;
		
	}

	public synchronized void salirLeer() {
		numLectores--;
		notifyAll();			
		
	}

	public synchronized void comenzarEscribir() throws InterruptedException {
		while (estaEscribiendo || numLectores > 0) {
			System.out.println("No puedo escribir");
			wait();
		}
		System.out.println("Comienzo a escribir");
		this.estaEscribiendo = true;
	}

	public synchronized void salirEscribir() {
		this.estaEscribiendo = false;
		notifyAll();
	}
}
