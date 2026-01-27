package com.carrillo.biblioteca;

import java.util.ArrayList;
import java.util.Random;

public class Biblioteca {
	ArrayList<Libro> libros;

	/**
	 * Constructor con sus libros creados
	 */
	public Biblioteca() {
		libros = new ArrayList<Libro>();

		Libro l1 = new Libro("Manolito", 1000);
		Libro l2 = new Libro("Juego de Tronos", 2500);
		Libro l3 = new Libro("Diario", 800);
		Libro l4 = new Libro("Survival", 4120);
		Libro l5 = new Libro("Juegos del hambre", 4320);

		libros.add(l1);
		libros.add(l2);
		libros.add(l3);
		libros.add(l4);
		libros.add(l5);

	}

	synchronized public Libro prestarLibro() {
		Random random = new Random();
		if (libros.size() <= 0) {		
			System.out.println("La lista se quedó vacia");
			return null;
		} else {
			int numAleatorio = random.nextInt(libros.size());
			while (libros.size() < numAleatorio) {
				System.out.println("Se ha desbordado el numero aleatorio");
				numAleatorio = random.nextInt(libros.size());
			}

			if (libros.get(numAleatorio) != null) {
				return libros.remove(numAleatorio);
			} else {
				return null;
			}
		}
	}

	synchronized public void devolverLibro(Libro libro) {
		System.out.println("Se va a añadir el libro " + libro + " a la lista");

		libros.add(libro);
	}

	/**
	 * Muestra los libros actuales
	 */
	public void imprimirEstado() {
		for (Libro libro : libros) {
			System.out.println(libro);
		}
	}

}
