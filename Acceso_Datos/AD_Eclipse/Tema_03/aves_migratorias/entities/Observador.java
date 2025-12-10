package entities;

import javax.persistence.*;

@Entity
@Table(name = "OBSERVADOR")
public class Observador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_OBS")
	private Integer codObs;

	@Column(name = "DNI", nullable = false, unique = true, length = 9)
	private String dni;

	@Column(name = "NOMBRE", nullable = false, length = 30)
	private String nombre;

	@Column(name = "DIRECCION", nullable = false, length = 30)
	private String direccion;

	@Column(name = "SITUACION", length = 8)
	private String situacion = "ACTIVO";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOMBRE_ASO")
	private Asociacion asociacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_Z", nullable = false)
	private ZonaObservacion zonaObservacion;

	public Observador() {
	}

	public Observador(String dni, String nombre, String direccion, Asociacion aso, ZonaObservacion zona) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.asociacion = aso;
		this.zonaObservacion = zona;
	}

	// Getters y Setters
	public Integer getCodObs() {
		return codObs;
	}

	public void setCodObs(Integer codObs) {
		this.codObs = codObs;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public ZonaObservacion getZonaObservacion() {
		return zonaObservacion;
	}

	public void setZonaObservacion(ZonaObservacion zonaObservacion) {
		this.zonaObservacion = zonaObservacion;
	}

	@Override
	public String toString() {
		return "Observador [codObs=" + codObs + ", nombre=" + nombre + "]";
	}
}