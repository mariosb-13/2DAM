package tarea_4;

import java.util.ArrayList;
import java.util.List;

public class Receta {
	private String titulo;
	private List<Ingrediente> ingredientes=new ArrayList<Ingrediente>();
	private String procedimiento;
	private String tiempo;
	
	public Receta(String titulo, List<Ingrediente> ingredientes, String procedimiento, String tiempo) {
		super();
		this.titulo = titulo;
		this.ingredientes = ingredientes;
		this.procedimiento = procedimiento;
		this.tiempo = tiempo;
	}
	
	public String getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	@Override
	public String toString() {
		return "Receta [titulo=" + titulo + ", ingredientes=" + ingredientes + "]";
	}
	
	
}
