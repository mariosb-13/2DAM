package entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "departamento")
@NamedQuery(name = "Departamento.getDepartamentosImplicados", 
            query = "SELECT DISTINCT d FROM Departamento d JOIN d.empleados e JOIN e.participaciones p WHERE p.id.proyectoNo = :proyecto_no")
public class Departamento {
    @Id
    @Column(name = "dept_no")
    private Integer dept_no;

    @Column(name = "dnombre")
    private String dnombre;

    @Column(name = "loc")
    private String loc;

    @OneToMany(mappedBy = "dept")
    private List<Empleado> empleados;

    public Departamento() {}

    // Getters y Setters
    public Integer getDept_no() { return dept_no; }
    public void setDept_no(Integer dept_no) { this.dept_no = dept_no; }
    public String getDnombre() { return dnombre; }
    public void setDnombre(String dnombre) { this.dnombre = dnombre; }
    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }
    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }

    @Override
    public String toString() {
        return "Departamento [dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + "]";
    }
}