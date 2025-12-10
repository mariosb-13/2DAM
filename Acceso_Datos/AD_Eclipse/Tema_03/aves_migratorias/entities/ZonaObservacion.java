package entities;

import javax.persistence.*;

@Entity
@Table(name = "ZONA_OBSERVACION")
public class ZonaObservacion {

	@Id
	@Column(name = "CODIGO_Z", length = 10)
	private String codigoZ;

	@Column(name = "NOMBRE", nullable = false, length = 30)
	private String nombre;

	@Column(name = "TIPO", nullable = false, length = 30)
	private String tipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCIA", nullable = false)
	private Provincia provincia;

	public ZonaObservacion() {
	}

	public ZonaObservacion(String codigoZ, String nombre, String tipo, Provincia provincia) {
		this.codigoZ = codigoZ;
		this.nombre = nombre;
		this.tipo = tipo;
		this.provincia = provincia;
	}

	// Getters y Setters
	public String getCodigoZ() {
		return codigoZ;
	}

	public void setCodigoZ(String codigoZ) {
		this.codigoZ = codigoZ;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "ZonaObservacion [codigoZ=" + codigoZ + ", nombre=" + nombre + "]";
	}
}