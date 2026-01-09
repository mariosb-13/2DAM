package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @Column(name = "proyecto_no")
    private Integer proyectoNo;

    @Column(name = "pnombre")
    private String pnombre;

    @OneToMany(mappedBy = "proyecto")
    private List<Trabaja> participaciones;

    public Proyecto() {}

    // Getters y Setters
    public Integer getProyectoNo() { return proyectoNo; }
    public void setProyectoNo(Integer proyectoNo) { this.proyectoNo = proyectoNo; }
    public String getPnombre() { return pnombre; }
    public void setPnombre(String pnombre) { this.pnombre = pnombre; }
    public List<Trabaja> getParticipaciones() { return participaciones; }
    public void setParticipaciones(List<Trabaja> participaciones) { this.participaciones = participaciones; }
}