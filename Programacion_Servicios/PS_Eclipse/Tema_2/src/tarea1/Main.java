package tarea1;

public class Main {

	public static void main(String[] args) {
		CuentaCorriente cuenta = new CuentaCorriente(1000);
				
		Usuario u1 = new Usuario(cuenta);
		Usuario u2 = new Usuario(cuenta);
		Usuario u3 = new Usuario(cuenta);


		u1.start();
		u2.start();
		u3.start();
	}

}
