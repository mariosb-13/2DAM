package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class TrabajaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Los nombres de los campos deben coincidir con las FKs de la tabla trabaja
	private Integer emp_no; 
	private Integer proyecto_no;

	// Constructor sin argumentos
	public TrabajaPK() {
	}
	
	// Constructor con argumentos
	public TrabajaPK(Integer emp_no, Integer proyecto_no) {
		this.emp_no = emp_no;
		this.proyecto_no = proyecto_no;
	}

	// Getters y Setters (Necesarios para la persistencia)
	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getProyecto_no() {
		return proyecto_no;
	}

	public void setProyecto_no(Integer proyecto_no) {
		this.proyecto_no = proyecto_no;
	}

	// MÉTODOS OBLIGATORIOS para claves compuestas
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_no == null) ? 0 : emp_no.hashCode());
		result = prime * result + ((proyecto_no == null) ? 0 : proyecto_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrabajaPK other = (TrabajaPK) obj;
		if (emp_no == null) {
			if (other.emp_no != null)
				return false;
		} else if (!emp_no.equals(other.emp_no))
			return false;
		if (proyecto_no == null) {
			if (other.proyecto_no != null)
				return false;
		} else if (!proyecto_no.equals(other.proyecto_no))
			return false;
		return true;
	}
}