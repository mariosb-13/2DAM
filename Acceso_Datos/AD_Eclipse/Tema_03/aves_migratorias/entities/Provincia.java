package entities;

import javax.persistence.*;

@Entity
@Table(name = "PROVINCIA")
public class Provincia {

	@Id
	@Column(name = "NOMBRE", length = 30)
	private String nombre;

	@Column(name = "COMUNIDAD", nullable = false, length = 30)
	private String comunidad;

	public Provincia() {
	}

	public Provincia(String nombre, String comunidad) {
		this.nombre = nombre;
		this.comunidad = comunidad;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	@Override
	public String toString() {
		return "Provincia [nombre=" + nombre + ", comunidad=" + comunidad + "]";
	}
}