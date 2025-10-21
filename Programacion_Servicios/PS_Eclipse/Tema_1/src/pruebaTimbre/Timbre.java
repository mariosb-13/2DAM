package pruebaTimbre;

public class Timbre {

	public synchronized void tocar() {
		try {
			synchronized (this) {
				System.out.print("Ding....");
				Thread.sleep(1000);
				System.out.println("Dong....");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
