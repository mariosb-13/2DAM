package paquete;

public class Proyecto {
    private int proyecto_no;
    private String pnombre;

    // Constructor
    public Proyecto(int proyecto_no, String pnombre) {
        this.proyecto_no = proyecto_no;
        this.pnombre = pnombre;
    }

    // Getters
    public int getProyecto_no() { return proyecto_no; }
    public String getPnombre() { return pnombre; }
    
    // Setters
    public void setPnombre(String pnombre) { this.pnombre = pnombre; }

    @Override
    public String toString() {
        return "Proyecto [No=" + proyecto_no + ", Nombre=" + pnombre + "]";
    }
}