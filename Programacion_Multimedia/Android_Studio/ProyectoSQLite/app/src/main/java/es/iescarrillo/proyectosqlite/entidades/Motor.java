package es.iescarrillo.proyectosqlite.entidades;

public class Motor {
    private int id_motor;
    private String nombre,etiqueta_ambiental;
    private double cilindrada;


    public Motor(int id_motor, String nombre, String etiqueta_ambiental, double cilindrada){
        this.id_motor = id_motor;
        this.nombre = nombre;
        this.etiqueta_ambiental = etiqueta_ambiental;
        this.cilindrada = cilindrada;
    }

    public Motor(){}

    public int getId_motor() {
        return id_motor;
    }

    public void setId_motor(int id_motor) {
        this.id_motor = id_motor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEtiqueta_ambiental() {
        return etiqueta_ambiental;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }


    public void setEtiqueta_ambiental(String etiqueta_ambiental) {
        this.etiqueta_ambiental = etiqueta_ambiental;
    }
}
