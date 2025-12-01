package es.iescarrillo.proyectosqlite.entidades;

public class Marca {

    int id_marca;
    String nombre,pais,telefono;


    public Marca(int id_marca, String nombre, String pais, String telefono) {
        this.id_marca = id_marca;
        this.nombre = nombre;
        this.pais = pais;
        this.telefono = telefono;
    }

    public Marca(){}

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
