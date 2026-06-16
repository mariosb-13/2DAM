package ies.carrillo.biblioteca;

public class Libro {
	private static int generadorId = 1; // Variable estática compartida
	public int id;
	public String nombre;
	public int paginas;

	public Libro(String nombre, int paginas) {
		this.nombre = nombre;
		this.paginas = paginas;
		this.id = generadorId++; // Asigna y luego incrementa
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

}