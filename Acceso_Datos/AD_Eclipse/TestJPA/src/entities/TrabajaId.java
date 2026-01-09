package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrabajaId implements Serializable {
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "proyecto_no")
    private Integer proyectoNo;

    public TrabajaId() {}
    public TrabajaId(Integer empNo, Integer proyectoNo) {
        this.empNo = empNo;
        this.proyectoNo = proyectoNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrabajaId)) return false;
        TrabajaId that = (TrabajaId) o;
        return Objects.equals(empNo, that.empNo) && Objects.equals(proyectoNo, that.proyectoNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, proyectoNo);
    }
}