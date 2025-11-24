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
    @Column(name = "emp_no")
    private Integer emp_no; // PRIMARY KEY
	
    @Column(name = "apellido")
    private String apellido;
	
    @Column(name = "oficio")
    private String oficio;
	
    // Relación 1-1 unidireccional con el jefe (otro Empleado)
    @OneToOne
    @JoinColumn(name = "dir", referencedColumnName = "emp_no")
    private Empleado dir; // REFERENCES empleado(emp_no)
	
    @Column(name = "fecha_alt")
    private Date fecha_alt;
	
    @Column(name = "salario")
    private float salario;
	
    @Column(name = "comision")
    private float comision;
	
    // Relación N-1 unidireccional con Departamento
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", nullable = false)
    private Departamento dept; // REFERENCES departamento(dept_no)
	
    // Constructor sin parámetros
    public Empleado() {
    }
	
   
	
    // El resto es normal (Constructor con todos los parámetros)
    public Empleado(Integer emp_no, String apellido, String oficio, Empleado dir, Date fecha_alt, float salario, float comision, Departamento dept) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept = dept;
    }
	
    // Getters y Setters
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

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
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
        return "Empleado [emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept.getDept_no() + "]";
    }
}