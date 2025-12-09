package evaluable;

public class Mueble {
	public int id;
	static int cont = 1;

	public Mueble() {
		this.id = cont++;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return " [id=" + id + "]";
	}
}