package ejercicio8;

public class Lector extends Thread {

	int id;
	ArchivoCompartido ac;

	public Lector(int id, ArchivoCompartido ac) {
		this.id = id;
		this.ac = ac;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				System.out.println("LECTOR " + this.id + ": Intentando leer");
				this.ac.comenzarLeer();
				sleep(1000);
			}
			this.ac.salirLeer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
