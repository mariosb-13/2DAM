package evaluable;

public class ConsumidorTablones extends Thread {
    Almacen almacen;

    public ConsumidorTablones(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                almacen.crearMueble();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}