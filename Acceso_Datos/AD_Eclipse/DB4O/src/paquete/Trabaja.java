package paquete;

// Representa la tabla de relación N:M
public class Trabaja {
    private int emp_no;
    private int proyecto_no;
    private int horas;

    // Constructor
    public Trabaja(int emp_no, int proyecto_no, int horas) {
        this.emp_no = emp_no;
        this.proyecto_no = proyecto_no;
        this.horas = horas;
    }

    // Getters
    public int getEmp_no() { return emp_no; }
    public int getProyecto_no() { return proyecto_no; }
    public int getHoras() { return horas; }

    // Setters
    public void setHoras(int horas) { this.horas = horas; }
    
    @Override
    public String toString() {
        return "Trabaja [Emp=" + emp_no + ", Proy=" + proyecto_no + ", Horas=" + horas + "]";
    }
}