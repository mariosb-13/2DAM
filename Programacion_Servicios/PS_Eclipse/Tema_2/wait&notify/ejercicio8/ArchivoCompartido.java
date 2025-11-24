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
			wait();
		}
		numLectores++;

	}

	public synchronized void salirLeer() {
		numLectores--;
	}

	public synchronized void comenzarEscribir() throws InterruptedException {
		while (estaEscribiendo || numLectores > 0) {
			wait();
		}
		this.estaEscribiendo = true;
	}

	public synchronized void salirEscribir() {
		this.estaEscribiendo = false;
		notifyAll();
	}
}
