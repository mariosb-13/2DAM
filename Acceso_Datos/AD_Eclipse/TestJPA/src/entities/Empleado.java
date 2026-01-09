package entities;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "empleado")
@NamedQuery(name = "Empleado.empleadosPorDepto", 
            query = "SELECT e FROM Empleado e WHERE e.dept.dept_no = :dept_no")
public class Empleado {
    @Id
    private Integer emp_no;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "oficio")
    private String oficio;

    @OneToOne
    @JoinColumn(name = "dir", referencedColumnName = "emp_no")
    private Empleado dir;

    @Column(name = "fecha_alt")
    private Date fecha_alt;

    @Column(name = "salario")
    private Float salario;

    @Column(name = "comision")
    private Float comision;

    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", nullable = false)
    private Departamento dept;

    // Relación para acceder a los proyectos en los que trabaja
    @OneToMany(mappedBy = "empleado")
    private List<Trabaja> participaciones;

    public Empleado() {}

    // Getters y Setters
    public Integer getEmp_no() { return emp_no; }
    public void setEmp_no(Integer emp_no) { this.emp_no = emp_no; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getOficio() { return oficio; }
    public void setOficio(String oficio) { this.oficio = oficio; }
    public Empleado getDir() { return dir; }
    public void setDir(Empleado dir) { this.dir = dir; }
    public Date getFecha_alt() { return fecha_alt; }
    public void setFecha_alt(Date fecha_alt) { this.fecha_alt = fecha_alt; }
    public Float getSalario() { return salario; }
    public void setSalario(Float salario) { this.salario = salario; }
    public Float getComision() { return comision; }
    public void setComision(Float comision) { this.comision = comision; }
    public Departamento getDept() { return dept; }
    public void setDept(Departamento dept) { this.dept = dept; }
    public List<Trabaja> getParticipaciones() { return participaciones; }
    public void setParticipaciones(List<Trabaja> participaciones) { this.participaciones = participaciones; }

    @Override
    public String toString() {
        return "Empleado [emp_no=" + emp_no + ", apellido=" + apellido + ", salario=" + salario + "]";
    }
}