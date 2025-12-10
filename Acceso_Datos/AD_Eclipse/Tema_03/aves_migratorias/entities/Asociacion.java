package entities;

import javax.persistence.*;

@Entity
@Table(name = "ASOCIACION")
public class Asociacion {

	@Id
	@Column(name = "NOMBRE_ASO", length = 30)
	private String nombreAso;

	@Column(name = "DIRECCION", nullable = false, length = 30)
	private String direccion;

	@Column(name = "TELEFONO", nullable = false, unique = true)
	private Long telefono;

	public Asociacion() {
	}

	public Asociacion(String nombreAso, String direccion, Long telefono) {
		this.nombreAso = nombreAso;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	// Getters y Setters
	public String getNombreAso() {
		return nombreAso;
	}

	public void setNombreAso(String nombreAso) {
		this.nombreAso = nombreAso;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Asociacion [nombreAso=" + nombreAso + ", telefono=" + telefono + "]";
	}
}