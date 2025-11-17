package examen;

public class Arbitro {
	
	int numJugadores;
	int numAdivinar;
	boolean juegoAcabado=false;
	
	
	/**
	 * Constructor que indica el numero de jugadores e inicializa el numero que hay que adivinar
	 * @param numJugadores
	 */
	public Arbitro(int numJugadores) {
		this.numJugadores = numJugadores;
		this.numAdivinar = generarAleatorio();
	}


	/**
	 * Método que genera el numero aleatorio que tienen que adivinar los jugadores
	 * @return
	 */
	public int generarAleatorio() {
		return 1+(int)(10*Math.random());
	}	
	
	/**
	 * Método que comprueba si el número que le da el jugador coincide
	 * @param num
	 * @throws InterruptedException 
	 */
	public synchronized void jugar (int num) throws InterruptedException {
		Thread.sleep(1000);
		if (!juegoAcabado) {
			if (this.numAdivinar==num) {
				System.out.println("El número " + num + " SI coincide ¡Enhorabuena! \n");
				juegoAcabado=true;
			}else {
				System.out.println("El número " + num +" NO coincide \n");

			}
		}
		
		
	}


	public boolean isJuegoAcabado() {
		return juegoAcabado;
	}
	
	

}
