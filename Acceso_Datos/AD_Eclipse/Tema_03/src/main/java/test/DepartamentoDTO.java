package test;

import entities.Departamento;

public class DepartamentoDTO {
    
    private Integer dept_no;
    private String dnombre;
    private String loc;

    public DepartamentoDTO() {}

    // Constructor que convierte Entidad -> DTO
    public DepartamentoDTO(Departamento dept) {
        this.dept_no = dept.getDept_no();
        this.dnombre = dept.getDnombre();
        this.loc = dept.getLoc();
    }

    // Getters y Setters
    public Integer getDept_no() { return dept_no; }
    public void setDept_no(Integer dept_no) { this.dept_no = dept_no; }
    public String getDnombre() { return dnombre; }
    public void setDnombre(String dnombre) { this.dnombre = dnombre; }
    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }

    // Equals y HashCode son VITALES para que el Set filtre duplicados automáticamente
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dept_no == null) ? 0 : dept_no.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        DepartamentoDTO other = (DepartamentoDTO) obj;
        if (dept_no == null) {
            if (other.dept_no != null) return false;
        } else if (!dept_no.equals(other.dept_no)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "DepartamentoDTO [id=" + dept_no + ", nombre=" + dnombre + "]";
    }
}