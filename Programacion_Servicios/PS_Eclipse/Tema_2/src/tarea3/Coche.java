package tarea3;

public class Coche extends Thread {
    LineaInspeccion linea;

    public Coche(LineaInspeccion linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        // El coche tarda 1 segundo (1000 ms)
        linea.revisar("Coche " + getId(), 1000);
    }
}