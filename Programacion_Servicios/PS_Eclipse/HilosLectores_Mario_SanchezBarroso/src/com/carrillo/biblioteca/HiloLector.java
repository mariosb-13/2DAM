package com.carrillo.biblioteca;

public class HiloLector extends Thread {

	public String nombre;
	public Biblioteca b1;

	public HiloLector(String nombre, Biblioteca biblioteca) {
		this.nombre = nombre;
		b1 = biblioteca;
	}

	@Override
	public void run() {
		System.out.println("El hilo '" + nombre + "' comenzó su ejecución");
		while (true) {
			if (b1.prestarLibro() != null) {
				try {
					sleep(b1.prestarLibro().paginas * 100);

					//Se ha subido ha 1000 el sleep
					sleep(1000);
					b1.imprimirEstado();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			} else {
				
				b1.devolverLibro(b1.libros.get(0));

			}
		}
	}

}
