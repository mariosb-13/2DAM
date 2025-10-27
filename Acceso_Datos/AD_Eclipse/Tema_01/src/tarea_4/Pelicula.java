package tarea_4;

import java.util.ArrayList;

public class Pelicula {
	private String titulo;
	private Integer duracion;
	private String genero;
	private String sinopsis;
	private ArrayList<String> actores;
	private Integer fecha;
	private String director;

	// Constructor vacío
	public Pelicula() {
		this.actores = new ArrayList<>();
	}

	// Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public ArrayList<String> getActores() {
		return actores;
	}

	public void setActores(ArrayList<String> actores) {
		this.actores = actores;
	}

	public Integer getFecha() {
		return fecha;
	}

	public void setFecha(Integer fecha) {
		this.fecha = fecha;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Título: " + titulo + "\nDuración: " + (duracion != null ? duracion : "null") + "\nGénero: "
				+ (genero != null ? genero : "null") + "\nSinopsis: " + (sinopsis != null ? sinopsis : "null")
				+ "\nActores: " + (actores.isEmpty() ? "[]" : actores) + "\nAño: " + (fecha != null ? fecha : "null")
				+ "\nDirector: " + (director != null ? director : "null")
				+ "\n------------------------------------------------";
	}
}
