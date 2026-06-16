package ies.carrillo.biblioteca;

import java.util.ArrayList;
import java.util.Random;

public class Biblioteca {
	ArrayList<Libro> libros;

	public Biblioteca() {
		libros = new ArrayList<Libro>();
		libros.add(new Libro("Manolito", 10)); // He bajado las páginas para que las pruebas no tarden horas
		libros.add(new Libro("Juego de Tronos", 25));
		libros.add(new Libro("Diario", 8));
		libros.add(new Libro("Survival", 41));
		libros.add(new Libro("Juegos del hambre", 43));
	}

	synchronized public Libro prestarLibro() {
	    // 1. Bucle WHILE en vez de IF
	    while (libros.isEmpty()) {		
	        try {
	            System.out.println("No hay libros. " + Thread.currentThread().getName() + " se queda esperando...");
	            wait(); // 2. El hilo se congela aquí hasta que alguien haga notifyAll()
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    } 
	    
	    // Si el código baja hasta aquí, es MATEMÁTICAMENTE SEGURO que hay libros
	    Random random = new Random();
	    int numAleatorio = random.nextInt(libros.size());
	    return libros.remove(numAleatorio);
	}

	synchronized public void devolverLibro(Libro libro) {
	    libros.add(libro);
	    System.out.println("Se ha devuelto el libro: " + libro.nombre);
	    
	    // 3. Pegar el grito para despertar a los que hicieron wait()
	    notifyAll(); 
	}

	public void imprimirEstado() {
		System.out.println("Libros disponibles: " + libros.size());
	}
}