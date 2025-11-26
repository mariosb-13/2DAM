package paquete;

public class Departamento {
    private int dept_no;
    private String dnombre;
    private String loc;
    
    // Constructor
    public Departamento(int dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }
    
    // Getters
    public int getDept_no() { return dept_no; }
    public String getDnombre() { return dnombre; }
    public String getLoc() { return loc; }
    
    public void setDnombre(String dnombre) { this.dnombre = dnombre; }
    public void setLoc(String loc) { this.loc = loc; }

    @Override
    public String toString() {
        return "Departamento [No=" + dept_no + ", Nombre=" + dnombre + ", Loc=" + loc + "]";
    }
}