package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proyecto")
public class Proyecto {
    
    @Id
    @Column(name = "proyecto_no")
    private Integer proyecto_no; 
    
    @Column(name = "pnombre")
    private String pnombre;

    // --- NUEVA RELACIÓN PARA NAVEGAR HACIA ABAJO ---
    // Enlazamos con la tabla 'trabaja' usando la columna 'proyecto_no'
    // 'insertable=false' es importante porque la entidad Trabaja ya gestiona ese ID.
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_no", referencedColumnName = "proyecto_no", insertable = false, updatable = false)
    private List<Trabaja> trabajos = new ArrayList<>();

    public Proyecto() {
    }

    public Proyecto(Integer proyecto_no, String pnombre) {
        this.proyecto_no = proyecto_no;
        this.pnombre = pnombre;
    }

    public Integer getProyecto_no() { return proyecto_no; }
    public void setProyecto_no(Integer proyecto_no) { this.proyecto_no = proyecto_no; }

    public String getPnombre() { return pnombre; }
    public void setPnombre(String pnombre) { this.pnombre = pnombre; }
    
    // Getter para la lista
    public List<Trabaja> getTrabajos() { return trabajos; }
    public void setTrabajos(List<Trabaja> trabajos) { this.trabajos = trabajos; }

    @Override
    public String toString() {
        return "Proyecto [proyecto_no=" + proyecto_no + ", pnombre=" + pnombre + "]";
    }
}