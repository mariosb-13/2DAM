package prueba;

public class Lanzador {
	public void lanzarSumador(int n1, int n2) {
		ProcessBuilder pb;
		try {
			String clase = "prueba.Sumador";
			pb = new ProcessBuilder("java", clase, String.valueOf(n1), String.valueOf(n2));
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Lanzador l = new Lanzador();
		l.lanzarSumador(1, 50);
		Thread.sleep(500);
		l.lanzarSumador(51, 100);

	}
}
