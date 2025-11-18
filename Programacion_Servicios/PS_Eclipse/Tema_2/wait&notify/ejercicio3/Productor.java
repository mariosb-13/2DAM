package ejercicio3;

public class Productor extends Thread{

	ArrayBuffer arrayBuffer;
	
	
	public Productor(ArrayBuffer arrayBuffer) {
		this.arrayBuffer=arrayBuffer;
	}
	@Override
	public void run() {
		synchronized (arrayBuffer) {
			for (int i = 0; i < arrayBuffer.lista.size(); i++) {
				arrayBuffer.lista.add(generarAleatorio());
			}
		}
	}
	
	public int generarAleatorio() {
		return 1+(int)(10*Math.random());
	}
}
