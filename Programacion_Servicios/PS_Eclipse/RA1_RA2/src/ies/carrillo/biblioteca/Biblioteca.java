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
		if (libros.isEmpty()) {		
			return null;
		} 
		
		Random random = new Random();
		int numAleatorio = random.nextInt(libros.size());
		return libros.remove(numAleatorio);
	}

	synchronized public void devolverLibro(Libro libro) {
		System.out.println("Se ha devuelto el libro: " + libro.nombre);
		libros.add(libro);
	}

	public void imprimirEstado() {
		System.out.println("Libros disponibles: " + libros.size());
	}
}