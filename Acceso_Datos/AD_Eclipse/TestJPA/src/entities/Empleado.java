package entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {
	@Id
	private Integer emp_no; // PRIMARY KEY
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "oficio")
	private String oficio;
	@OneToOne
	@JoinColumn(name = "dir", referencedColumnName = "emp_no")
	private Empleado dir; // REFERENCES empleado(emp_no),
	@Column(name = "fecha_alt")
	private Date fecha_alt;
	@Column(name = "salario")
	private Float salario;
	@Column(name = "comision")
	private Float comision;
	@ManyToOne
	@JoinColumn(name = "dept_no", referencedColumnName = "dept_no", nullable = false)
	private Departamento dept; // REFERENCES departamento(dept_no)
//Constructor sin parametros

	public Empleado() {
	}

	public Integer getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Empleado getDir() {
		return dir;
	}

	public void setDir(Empleado dir) {
		this.dir = dir;
	}

	public Date getFecha_alt() {
		return fecha_alt;
	}

	public void setFecha_alt(Date fecha_alt) {
		this.fecha_alt = fecha_alt;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Float getComision() {
		return comision;
	}

	public void setComision(Float comision) {
		this.comision = comision;
	}

	public Departamento getDept() {
		return dept;
	}

	public void setDept(Departamento dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
	    return "Empleado [emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir
	            + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept=" + dept
	            + "]";
	}
	
	

	
}