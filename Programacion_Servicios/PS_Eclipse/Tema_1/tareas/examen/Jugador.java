package examen;

public class Jugador extends Thread{
	int id;
	Arbitro arbitro1;
	
	/**
	 * Constructor de jugador
	 * @param id
	 * @param arbitro1
	 */
	public Jugador(int id, Arbitro arbitro1) {
		this.id = id;
		this.arbitro1 = arbitro1;
	}

	/**
	 * Llama al método del arbitro y comprueba 
	 */
	public void run() {
		try {
			arbitro1.jugar(generarAleatorioJugador());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que genera el numero aleatorio
	 * @return
	 */
	public int generarAleatorioJugador() {
		return 1+(int)(10*Math.random());
	}
	
	
}
