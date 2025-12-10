package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OBSERVACION")
public class Observacion {

	@EmbeddedId
	private ObservacionId id;

	// --- Relaciones que forman parte de la Clave Primaria ---

	// @MapsId("nombreCientifico") le dice a JPA: "La clave foránea
	// 'NOMBRE_CIENTIFICO'
	// rellena el atributo 'nombreCientifico' de mi clave compuesta (@EmbeddedId)"
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("nombreCientifico")
	@JoinColumn(name = "NOMBRE_CIENTIFICO")
	private Especie especie;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("codObs")
	@JoinColumn(name = "COD_OBS")
	private Observador observador;

	
	public Observacion() {
	}

	public Observacion(Especie especie, Observador observador, Date fecha) {
		// Al crear la observación, inicializamos la clave compuesta
		this.id = new ObservacionId(especie.getNombreCientifico(), observador.getCodObs(), fecha);
		this.especie = especie;
		this.observador = observador;
	}

	// Getters y Setters
	public ObservacionId getId() {
		return id;
	}

	public void setId(ObservacionId id) {
		this.id = id;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Observador getObservador() {
		return observador;
	}

	public void setObservador(Observador observador) {
		this.observador = observador;
	}

	// Helper para obtener fecha fácilmente
	public Date getFecha() {
		return (id != null) ? id.getFecha() : null; // Asumiendo que añadiste getter en ObservacionId
	}

	@Override
	public String toString() {
		return "Observacion [id=" + id + "]";
	}
}