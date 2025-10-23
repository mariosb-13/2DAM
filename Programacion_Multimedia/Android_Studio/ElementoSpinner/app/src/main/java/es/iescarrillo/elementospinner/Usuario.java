package es.iescarrillo.elementospinner;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String nombre;
    private String apellido1;

    private String apellido2;

    public Usuario() {
    }

    public Usuario(int id,  String nombre, String apellido1,String apellido2) {
        this.id = id;
        this.apellido1 = apellido1;
        this.nombre = nombre;
        this.apellido2 = apellido2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @Override
    public String toString() {
        return this.id + " " + this.nombre + " " + this.apellido1 + " " + this.apellido2;
    }
}
