package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

	// --- Atributos de la Entidad (Mapeados a Columnas) ---
	@Id
	@Column(name = "dept_no")
	private Integer dept_no; // PRIMARY KEY

	@Column(name = "dnombre")
	private String dnombre;

	@Column(name = "loc")
	private String loc;

	// --- Constructor sin parámetros (Requerido por JPA/Hibernate) ---
	public Departamento() {
	}

	// --- Constructor con todos los parámetros ---
	public Departamento(Integer dept_no, String dnombre, String loc) {
		this.dept_no = dept_no;
		this.dnombre = dnombre;
		this.loc = loc;
	}
	
	 // Relación 1-N Bidireccional
    // MappedBy indica la clave foránea está en la otra entidad (Empleado)
    @OneToMany(mappedBy = "dept")
	public Set<Empleado> empleados;

	// --- Getters y Setters ---
	public Integer getDept_no() {
		return dept_no;
	}

	public void setDept_no(Integer dept_no) {
		this.dept_no = dept_no;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	// --- Método toString() ---
	@Override
	public String toString() {
		return "Departamento [dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + "]";
	}
}