package ejercicio2;

public class Caja {
	private int valor=-1;

	public synchronized void poner(int valor) {
		if (valor > 0) {
			this.valor = valor;
			this.notify();
		}
	}

	public synchronized int tomar() {
		while (valor < 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.valor;

	}
}
