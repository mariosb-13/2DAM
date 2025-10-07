package tarea_2;

public class Pedido {

	private String descripcion;
	private int numUnidades;
	private double precio;

	public Pedido(String descripcion, int numUnidades, double precio) {
		this.descripcion = descripcion;
		this.numUnidades = numUnidades;
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getNumUnidades() {
		return numUnidades;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Pedido{" + "descripcion='" + descripcion + '\'' + ", numUnidades=" + numUnidades + ", precio=" + precio
				+ '}';
	}
}
