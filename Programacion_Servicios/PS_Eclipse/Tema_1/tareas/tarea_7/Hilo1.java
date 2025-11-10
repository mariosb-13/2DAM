package tarea_7;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 *
 * @author Usuario
 */
public class Hilo1 extends Thread{
	
	PipedOutputStream pos;

	public Hilo1(PipedOutputStream pos) {
		this.pos=pos;
		
	}

    public static void main(String[] args) {
       
    }
    
    public static int generaNumeroAleatorio(int minimo,int maximo){
       int num=(int)(Math.random()*(maximo-minimo+1)+(minimo));
       return num;
    }
    
    @Override
	public void run() {
    	  int cantidadGenerados = 40;
          
          for (int i = 0; i < cantidadGenerados; i++) {
              System.out.print(generaNumeroAleatorio(0,100) + " ");
              int num=generaNumeroAleatorio(0, 100);
              try {
				pos.write((byte)num);
	              pos.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
    }
}
