package prueba;

public class Sumador {
	public static int Suma(int n1, int n2) {
		int resultado = 0;

		for (int i = n1; i <= n2; i++) {
			resultado += i;
		}
		return resultado;
	}
	
	public static void main(String[] args) {
		Sumador sum = new Sumador();
		System.out.println(sum.Suma(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
	}
}
