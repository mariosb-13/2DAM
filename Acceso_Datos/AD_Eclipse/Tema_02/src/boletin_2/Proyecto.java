package boletin_2;

public class Proyecto {
	int proyecto_no; //PRIMARY KEY
	String pnombre;
	
	public Proyecto(int proyecto_no, String pnombre) {
		super();
		this.proyecto_no = proyecto_no;
		this.pnombre = pnombre;
	}
	@Override
	public String toString() {
		return "Proyecto [proyecto_no=" + proyecto_no + ", pnombre=" + pnombre + "]";
	}
	public int getProyecto_no() {
		return proyecto_no;
	}
	public void setProyecto_no(int proyecto_no) {
		this.proyecto_no = proyecto_no;
	}
	public String getPnombre() {
		return pnombre;
	}
	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}
}