package entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "trabaja")
@IdClass(Trabaja.TrabajaId.class)
public class Trabaja {

    @Id
    @Column(name = "emp_no")
    private Integer emp_no;

    // --- CORRECCIÓN AQUÍ ---
    // La variable se llama 'proyecto' (para Java), pero en la BD es 'proyecto_no'
    @Id
    @Column(name = "proyecto_no") 
    private Integer proyecto;

    @Column(name = "horas")
    private Integer horas;

    public Trabaja() {
    }

    public Trabaja(Integer emp_no, Integer proyecto, Integer horas) {
        this.emp_no = emp_no;
        this.proyecto = proyecto;
        this.horas = horas;
    }

    // Constructor auxiliar para objetos
    public Trabaja(Empleado empleado, Proyecto proyecto, Integer horas) {
        this.emp_no = empleado.getEmp_no();
        this.proyecto = proyecto.getProyecto_no();
        this.horas = horas;
    }

    public Integer getEmp_no() { return emp_no; }
    public void setEmp_no(Integer emp_no) { this.emp_no = emp_no; }

    public Integer getProyecto() { return proyecto; }
    public void setProyecto(Integer proyecto) { this.proyecto = proyecto; }

    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }

    // --- CLASE INTERNA PARA PK COMPUESTA ---
    public static class TrabajaId implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        // Los nombres de variable deben coincidir con los de la clase principal
        private Integer emp_no;
        private Integer proyecto;

        public TrabajaId() {}

        public TrabajaId(Integer emp_no, Integer proyecto) {
            this.emp_no = emp_no;
            this.proyecto = proyecto;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TrabajaId trabajaId = (TrabajaId) o;
            return Objects.equals(emp_no, trabajaId.emp_no) &&
                   Objects.equals(proyecto, trabajaId.proyecto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(emp_no, proyecto);
        }
    }
}