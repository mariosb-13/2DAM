package entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "trabaja")
@AssociationOverrides({
    @AssociationOverride(name = "id.emp_no", joinColumns = @JoinColumn(name = "emp_no")),
    @AssociationOverride(name = "id.proyecto_no", joinColumns = @JoinColumn(name = "proyecto_no"))
})
public class Trabaja {

	@EmbeddedId
	private TrabajaPK id = new TrabajaPK();

	@Column(name = "horas")
	private Integer horas;

	@Transient 
	@ManyToOne
	@JoinColumn(name = "emp_no", insertable = false, updatable = false)
	private Empleado empleado;

	// Relación con Proyecto (ManyToOne)
	@Transient 
	@ManyToOne
	@JoinColumn(name = "proyecto_no", insertable = false, updatable = false)
	private Proyecto proyecto;

	// Constructor sin parámetros (Obligatorio)
	public Trabaja() {
	}

	// Constructor con objetos relacionados
	public Trabaja(Empleado empleado, Proyecto proyecto, Integer horas) {
		this.empleado = empleado;
		this.proyecto = proyecto;
		this.horas = horas;
		this.id.setEmp_no(empleado.getEmp_no());
		this.id.setProyecto_no(proyecto.getProyecto_no());
	}

	// --- Getters y Setters ---
	
	public TrabajaPK getId() {
		return id;
	}

	public void setId(TrabajaPK id) {
		this.id = id;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}
    

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
		if (empleado != null) {
			this.id.setEmp_no(empleado.getEmp_no());
		}
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
		if (proyecto != null) {
			this.id.setProyecto_no(proyecto.getProyecto_no());
		}
	}

	@Override
	public String toString() {
		return "Trabaja [emp_no=" + this.id.getEmp_no() + ", proyecto_no=" + this.id.getProyecto_no() + ", horas=" + horas + "]";
	}
}