package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto")
public class Proyecto {
	
	@Id
	@Column(name = "proyecto_no")
	private Integer proyecto_no; // PRIMARY KEY
	
	@Column(name = "pnombre")
	private String pnombre;

	// Constructor sin parámetros
	public Proyecto() {
	}

	// Constructor con todos los parámetros
	public Proyecto(Integer proyecto_no, String pnombre) {
		this.proyecto_no = proyecto_no;
		this.pnombre = pnombre;
	}

	// Getters y Setters
	public Integer getProyecto_no() {
		return proyecto_no;
	}

	public void setProyecto_no(Integer proyecto_no) {
		this.proyecto_no = proyecto_no;
	}

	public String getPnombre() {
		return pnombre;
	}

	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}

	@Override
	public String toString() {
		return "Proyecto [proyecto_no=" + proyecto_no + ", pnombre=" + pnombre + "]";
	}
}