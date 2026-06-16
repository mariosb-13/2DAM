package ies.carrillo.biblioteca;

public class HiloLector extends Thread {

	public Biblioteca b1;

	public HiloLector(String nombre, Biblioteca biblioteca) {
		super(nombre); // Asignación a la clase padre (Thread)
		this.b1 = biblioteca;
	}

	@Override
	public void run() {
		System.out.println("El hilo '" + getName() + "' comenzó su ejecución");
		
		while (true) {
			// 1. Pedir prestado (se guarda en una variable local)
			Libro libroPrestado = b1.prestarLibro();

			if (libroPrestado != null) {
				try {
					// 2. Leer
					System.out.println(getName() + " está leyendo: " + libroPrestado.nombre);
					sleep(libroPrestado.paginas * 100L); // L para asegurar que sea long
					
					// 3. Devolver
					b1.devolverLibro(libroPrestado);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 4. Descanso: Se hace siempre antes de volver a pedir
			try {
				sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}