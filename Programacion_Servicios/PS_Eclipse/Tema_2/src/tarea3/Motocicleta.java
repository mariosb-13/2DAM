package tarea3;

public class Motocicleta extends Thread {
    LineaInspeccion linea;

    public Motocicleta(LineaInspeccion linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        // La moto tarda 0.5 segundos (500 ms)
        linea.revisar("Moto " + getId(), 500);
    }
}