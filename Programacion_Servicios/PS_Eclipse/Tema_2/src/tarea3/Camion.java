package tarea3;

public class Camion extends Thread {
    LineaInspeccion linea;

    public Camion(LineaInspeccion linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        // El camión tarda 1.5 segundos (1500 ms)
        linea.revisar("Camión " + getId(), 1500);
    }
}