package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
	@Id
	@Column(name = "dept_no")
	private Integer dept_no; // PRIMARY KEY
	@Column(name = "dnombre")
	private String dnombre;
	@Column(name = "loc")
	private String loc;
	@OneToMany(mappedBy = "dept")
	private List<Empleado> empleados;

//Constructor sin parametros
	public Departamento() {
	}
	
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

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

}