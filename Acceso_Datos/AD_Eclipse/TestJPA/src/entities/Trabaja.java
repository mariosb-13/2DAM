package entities;

import javax.persistence.*;

@Entity
@Table(name = "trabaja")
public class Trabaja {
    @EmbeddedId
    private TrabajaId id;

    @ManyToOne
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    private Empleado empleado;

    @ManyToOne
    @MapsId("proyectoNo")
    @JoinColumn(name = "proyecto_no")
    private Proyecto proyecto;

    private Integer horas;

    public Trabaja() {}

    public TrabajaId getId() { return id; }
    public void setId(TrabajaId id) { this.id = id; }
    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }
}