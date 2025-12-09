package evaluable;

import java.util.ArrayList;

public class Almacen {
	private ArrayList<Material> listaMateriales = new ArrayList<>();
	private ArrayList<Mueble> listaMuebles = new ArrayList<>();

	private final int CAPACIDAD_MAXIMA = 5;

	public synchronized void crearMaterial() throws InterruptedException {
		while (listaMateriales.size() >= CAPACIDAD_MAXIMA) {
			System.out.println("Almacén LLENO de materiales. Productor esperando...");
			wait();
		}

		//Tiempo simulado
		Thread.sleep(2000);
		//Creamos material
		Material m = new Material();
		//Se añade a la lista
		listaMateriales.add(m);
		
		System.out.println("PRODUCIDO: " + m + " | Total Materiales: " + listaMateriales.size());

		notifyAll();
	}

	public synchronized void crearMueble() throws InterruptedException {
		while (listaMateriales.isEmpty()) {
			System.out.println("Almacén VACÍO. Esperando materiales...");
			wait();
		}

		notifyAll();

		//Tiempo de creacion del mueble
		Thread.sleep(1000);

		//Creamos el mueble
		Mueble mueble = new Mueble();
		
		//Lo añadimos a la lista
		listaMuebles.add(mueble);
		
		//Quitamos un material de la lista (el material que ha consumido el mueble)
		listaMateriales.remove(0);
		
		System.out.println("Se ha creado un mueble" + mueble);
	}
}