package es.iescarrillo.appfirebaseejemplo;

import androidx.annotation.NonNull;

public class Luchador {
    private int id;
    private String nombre;

    public Luchador(){}

    public Luchador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }

}
