package ejercicio8;

public class Escritor extends Thread {

	int id;
	ArchivoCompartido ac;

	public Escritor(int id, ArchivoCompartido ac) {
		this.id = id;
		this.ac = ac;
	}

	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				this.ac.comenzarEscribir();
				sleep(500);
			}
			this.ac.salirEscribir();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
