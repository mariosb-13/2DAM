package boletin_2;

public class Trabaja {
	int emp_no; 		//PRIMARY KEY 	//REFERENCES empleado(emp_no),
	int proyecto_no; 	//PRIMARY KEY	//REFERENCES proyecto(proyecto_no)
	int horas;
	
	public Trabaja(int emp_no, int proyecto_no, int horas) {
		super();
		this.emp_no = emp_no;
		this.proyecto_no = proyecto_no;
		this.horas = horas;
	}
	@Override
	public String toString() {
		return "Trabaja [emp_no=" + emp_no + ", proyecto_no=" + proyecto_no + ", horas=" + horas + "]";
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getProyecto_no() {
		return proyecto_no;
	}
	public void setProyecto_no(int proyecto_no) {
		this.proyecto_no = proyecto_no;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}

}