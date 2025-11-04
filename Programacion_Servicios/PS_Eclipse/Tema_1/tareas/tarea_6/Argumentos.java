package tarea_6;

public class Argumentos {

	public static void main(String[] args) {
		// Si no hay argumentos
		if (args.length < 1) {
			System.exit(1);
		}

		String arg = args[0];

		try {
			int numero = Integer.parseInt(arg);
			if (numero < 0) {
				System.exit(3);
			} else {
				System.exit(0);
			}
		} catch (NumberFormatException e) {
			// No es un número entero
			System.exit(2);
		}
	}
}
