package es.iescarrillo.diseofigma;

import java.util.ArrayList;
import java.util.List;

public class CarritoManager {
    private static CarritoManager instancia;
    private List<LineaPedido> cesta;

    private CarritoManager() {
        cesta = new ArrayList<>();
    }

    // Método para obtener la única instancia (Singleton)
    public static CarritoManager getInstance() {
        if (instancia == null) {
            instancia = new CarritoManager();
        }
        return instancia;
    }

    public List<LineaPedido> getCesta() {
        return cesta;
    }

    // Lógica inteligente: Si ya existe, suma cantidad; si no, crea nuevo
    public void agregarProducto(Postre postre) {
        for (LineaPedido linea : cesta) {
            if (linea.getPostre().getNombre().equals(postre.getNombre())) {
                linea.setCantidad(linea.getCantidad() + 1);
                return; // Ya lo encontramos, terminamos
            }
        }
        // Si llegamos aquí, es que no estaba en la cesta
        cesta.add(new LineaPedido(postre, 1));
    }

    public double calcularTotalGeneral() {
        double total = 0;
        for (LineaPedido linea : cesta) {
            total += linea.getTotalLinea();
        }
        return total;
    }

    // Método opcional por si quieres vaciar el carro al finalizar compra
    public void vaciarCarrito() {
        cesta.clear();
    }
}