package com.carrillo.biblioteca;

public class Libro {
	public int id;
	public String nombre;
	public int paginas;

	/**
	 * Constructor que se le pasa el nombre y el nº de paginas, id es único y autoincremental
	 * @param nombre
	 * @param paginas
	 */
	public Libro(String nombre, int paginas) {
		this.nombre = nombre;
		this.paginas = paginas;
		this.id=id++;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", paginas=" + paginas + "]";
	}
	
	 
	
	 
}
