package tarea_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo2 extends Thread{
	
	PipedInputStream pis;
	
	public Hilo2(PipedInputStream pis) {
		this.pis=pis;	
	}
    public static void main(String[] args) {
    	       
    }
	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader bf = new BufferedReader(isr);
            
            String lineaTeclado = null;
            while( (lineaTeclado = bf.readLine()) != null){
                String datos[] = lineaTeclado.split(" ");
                int numeros[] = new int[datos.length];
                for (int i = 0; i < numeros.length; i++) {
                    numeros[i] = Integer.parseInt(datos[i]);
                }
                Arrays.sort(numeros);
                for (int i = 0; i < numeros.length; i++) {
                    System.out.print(numeros[i] + " ");
                }
                System.out.println("");
            }
        } catch (IOException ex) {
            Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    
}
