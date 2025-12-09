package evaluable;

public class Material {
	public int id;
	static int cont = 1;

	public Material() {
		this.id = cont++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return " [id=" + id + "]";
	}

}
