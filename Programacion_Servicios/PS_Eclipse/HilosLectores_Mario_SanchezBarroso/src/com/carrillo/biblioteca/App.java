package com.carrillo.biblioteca;

public class App {

	public static void main(String[] args) {
		//Creamos el recurso de la biblioteca
		Biblioteca bi = new Biblioteca();

		//Creamos todos los hilos con el recurso compartido
		HiloLector h1 = new HiloLector("Hilo 1", bi);
		HiloLector h2 = new HiloLector("Hilo 2", bi);
		HiloLector h3 = new HiloLector("Hilo 3", bi);
		HiloLector h4 = new HiloLector("Hilo 4", bi);
		HiloLector h5 = new HiloLector("Hilo 5", bi);
		HiloLector h6 = new HiloLector("Hilo 6", bi);
		HiloLector h7 = new HiloLector("Hilo 7", bi);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		h7.start();
		
		
	}
	
	
}
