package tarea_2;

import java.io.Serializable;

public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1608806531564872175L;
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
