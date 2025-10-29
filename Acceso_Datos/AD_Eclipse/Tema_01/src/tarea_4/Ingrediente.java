package tarea_4;

public class Ingrediente {
	private String nombreIngrediente;
	private String cantidad;
	
	public Ingrediente(String nombreIngrediente, String cantidad) {
		super();
		this.nombreIngrediente = nombreIngrediente;
		this.cantidad = cantidad;
	}

	public String getNombreIngrediente() {
		return nombreIngrediente;
	}

	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Ingrediente [nombreIngrediente=" + nombreIngrediente + ", cantidad=" + cantidad + "]";
	}
	
	
}
