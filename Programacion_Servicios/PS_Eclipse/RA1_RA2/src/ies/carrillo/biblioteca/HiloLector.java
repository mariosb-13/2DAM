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
	        // 1. Pedir prestado. Si no hay, el hilo se quedará PAUSADO en esta misma línea.
	        Libro libroPrestado = b1.prestarLibro();

	        // 2. Si pasa de la línea anterior, seguro que tiene un libro
	        try {
	            System.out.println(getName() + " está leyendo: " + libroPrestado.nombre);
	            sleep(libroPrestado.paginas * 100L); 
	            
	            // 3. Devolver (esto disparará el notifyAll por dentro)
	            b1.devolverLibro(libroPrestado);
	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // 4. Descanso
	        try {
	            sleep(250);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
}