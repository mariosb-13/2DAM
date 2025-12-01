package tarea3;

public class VehiculoAgricola extends Thread {
    private LineaInspeccion linea;

    public VehiculoAgricola(LineaInspeccion linea) {
        this.linea = linea;
    }

    @Override
    public void run() {
        linea.revisar("Vehiculo Agrícola " + getId(), 2000);
    }
}