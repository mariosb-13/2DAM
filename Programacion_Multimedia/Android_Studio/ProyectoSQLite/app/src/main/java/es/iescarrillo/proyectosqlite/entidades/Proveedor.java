package es.iescarrillo.proyectosqlite.entidades;

public class Proveedor {
    private int id_proveedor;
    private String nombre,email;
    private int dias_entrega;

    public Proveedor(int id_proveedor, String nombre, String email, int dias_entrega) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.email = email;
        this.dias_entrega = dias_entrega;
    }

    public Proveedor(){}

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDias_entrega() {
        return dias_entrega;
    }

    public void setDias_entrega(int dias_entrega) {
        this.dias_entrega = dias_entrega;
    }
}
