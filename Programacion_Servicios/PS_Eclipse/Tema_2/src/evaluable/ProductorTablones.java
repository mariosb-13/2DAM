package evaluable;

public class ProductorTablones extends Thread {
    Almacen almacen;

    public ProductorTablones(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                almacen.crearMaterial();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}