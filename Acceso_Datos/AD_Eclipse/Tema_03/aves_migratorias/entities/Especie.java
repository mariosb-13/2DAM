package entities;

import javax.persistence.*;

@Entity
@Table(name = "ESPECIE")
public class Especie {

	@Id
	@Column(name = "NOMBRE_CIENTIFICO", length = 30)
	private String nombreCientifico;

	@Column(name = "NOMBRE_VULGAR", nullable = false, unique = true, length = 30)
	private String nombreVulgar;

	@Column(name = "DESCRIPCION", nullable = false, length = 30)
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOMBRE_G", nullable = false)
	private GrupoOrnitologico grupoOrnitologico;

	public Especie() {
	}

	public Especie(String nombreCientifico, String nombreVulgar, String descripcion, GrupoOrnitologico grupo) {
		this.nombreCientifico = nombreCientifico;
		this.nombreVulgar = nombreVulgar;
		this.descripcion = descripcion;
		this.grupoOrnitologico = grupo;
	}

	// Getters y Setters
	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public String getNombreVulgar() {
		return nombreVulgar;
	}

	public void setNombreVulgar(String nombreVulgar) {
		this.nombreVulgar = nombreVulgar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public GrupoOrnitologico getGrupoOrnitologico() {
		return grupoOrnitologico;
	}

	public void setGrupoOrnitologico(GrupoOrnitologico grupoOrnitologico) {
		this.grupoOrnitologico = grupoOrnitologico;
	}

	@Override
	public String toString() {
		return "Especie [nombreCientifico=" + nombreCientifico + "]";
	}
}