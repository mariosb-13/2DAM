package entities;

import javax.persistence.*;

@Entity
@Table(name = "GRUPO_ORNITOLOGICO")
public class GrupoOrnitologico {

	@Id
	@Column(name = "NOMBRE_G", length = 30)
	private String nombreG;

	@Column(name = "PATAS", length = 30)
	private String patas;

	@Column(name = "DEDOS", length = 30)
	private String dedos;

	@Column(name = "PICO", length = 30)
	private String pico;

	public GrupoOrnitologico() {
	}

	public GrupoOrnitologico(String nombreG, String patas, String dedos, String pico) {
		this.nombreG = nombreG;
		this.patas = patas;
		this.dedos = dedos;
		this.pico = pico;
	}

	// Getters y Setters
	public String getNombreG() {
		return nombreG;
	}

	public void setNombreG(String nombreG) {
		this.nombreG = nombreG;
	}

	public String getPatas() {
		return patas;
	}

	public void setPatas(String patas) {
		this.patas = patas;
	}

	public String getDedos() {
		return dedos;
	}

	public void setDedos(String dedos) {
		this.dedos = dedos;
	}

	public String getPico() {
		return pico;
	}

	public void setPico(String pico) {
		this.pico = pico;
	}

	@Override
	public String toString() {
		return "GrupoOrnitologico [nombreG=" + nombreG + "]";
	}
}