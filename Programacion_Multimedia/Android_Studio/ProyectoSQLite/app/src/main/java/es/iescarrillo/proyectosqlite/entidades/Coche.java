package es.iescarrillo.proyectosqlite.entidades;

public class Coche {
    private int id_motor;
    private String matricula, modelo;
    private double precio_venta;

    public Coche(int id, String matricula, String modelo, double precio_venta) {
        this.id_motor = id;
        this.matricula = matricula;
        this.modelo = modelo;
        this.precio_venta = precio_venta;
    }

    public Coche(){}


    public int getId_Motor() {return id_motor;}

    public void setId(int id) {
        this.id_motor = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
}
