package tarea_7;

import java.io.IOException;
import java.io.PipedOutputStream;

public class Hilo1 extends Thread {

    private final PipedOutputStream pos;

    public Hilo1(PipedOutputStream pos) {
        this.pos = pos;
    }

    public static int generaNumeroAleatorio(int minimo,int maximo){
       return (int)(Math.random()*(maximo-minimo+1)+(minimo));
    }

    @Override
    public void run() {
        int cantidadGenerados = 40;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cantidadGenerados; i++) {
            int num = generaNumeroAleatorio(0,100);
            sb.append(num).append(" ");
        }

        try {
            pos.write(sb.toString().getBytes());
            pos.close(); // importante: cerrar para que Hilo2 sepa que terminó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
