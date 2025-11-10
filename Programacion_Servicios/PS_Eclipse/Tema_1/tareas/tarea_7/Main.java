package tarea_7;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {

	public static void main(String[] args) {
		try {
			PipedOutputStream pos = new PipedOutputStream();
			PipedInputStream pis = new PipedInputStream();

			pos.connect(pis);

			Hilo1 h1 = new Hilo1(pos);
			Hilo2 h2 = new Hilo2(pis);

			h2.start();
			h1.start();

			h1.join();
			h2.join();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
