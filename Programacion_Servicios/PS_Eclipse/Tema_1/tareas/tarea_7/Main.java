package tarea_7;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		PipedInputStream pis = new PipedInputStream();
	    PipedOutputStream pos = new PipedOutputStream();
	    try {
			pos.connect(pis);

			Hilo1 h1= new Hilo1(pos);
			Hilo2 h2 = new Hilo2(pis);
			
			h1.start();
			h2.start();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
