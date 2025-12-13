package es.iescarrillo.diseofigma;

public class LineaPedido {
    private Postre postre;
    private int cantidad;

    public LineaPedido(Postre postre, int cantidad) {
        this.postre = postre;
        this.cantidad = cantidad;
    }

    public Postre getPostre() { return postre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // Calcula el precio total de esta línea (Precio * Cantidad)
    public double getTotalLinea() {
        return postre.getPrecio() * cantidad;
    }
}