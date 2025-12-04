package es.iescarrillo.proyectosqlite.entidades;

import java.io.Serializable;

public class Coche implements Serializable {
    private int id_coche;
    private int id_motor;
    private int id_marca;
    private int id_proveedor;
    private String matricula, modelo;
    private double precio_venta;

    // Constructor completo
    public Coche(int id_coche, String matricula, String modelo, double precio_venta,
                 int id_motor, int id_marca, int id_proveedor) {
        this.id_coche = id_coche;
        this.matricula = matricula;
        this.modelo = modelo;
        this.precio_venta = precio_venta;
        this.id_motor = id_motor;
        this.id_marca = id_marca;
        this.id_proveedor = id_proveedor;
    }

    public Coche() {}

    // Getters y Setters
    public int getId_coche() {
        return id_coche;
    }

    public void setId_coche(int id_coche) {
        this.id_coche = id_coche;
    }

    public int getId_Motor() {
        return id_motor;
    }

    public void setId_Motor(int id_motor) {
        this.id_motor = id_motor;
    }

    public int getId_Marca() {
        return id_marca;
    }

    public void setId_Marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getId_Proveedor() {
        return id_proveedor;
    }

    public void setId_Proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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
