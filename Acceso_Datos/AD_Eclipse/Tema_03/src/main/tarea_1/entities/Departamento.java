package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

    // --- Atributos de la Entidad ---
    @Id
    @Column(name = "dept_no")
    private Integer dept_no; // PRIMARY KEY

    @Column(name = "dnombre")
    private String dnombre;

    @Column(name = "loc")
    private String loc;

    // --- RELACIÓN 1-N (Un departamento tiene muchos empleados) ---
    // 1. 'mappedBy = "dept"' indica que el campo en la clase Empleado se llama 'dept'
    // 2. Usamos List<Empleado> (con el genérico) para que Hibernate sepa qué objeto es.
    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Empleado> empleados = new ArrayList<>();

    // --- Constructor vacío (Obligatorio Hibernate) ---
    public Departamento() {
    }

    // --- Constructor con datos ---
    public Departamento(Integer dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }

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

    // Getter y Setter para la relación
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "Departamento [dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + "]";
    }
}