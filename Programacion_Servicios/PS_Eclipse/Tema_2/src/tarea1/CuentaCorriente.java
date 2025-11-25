package tarea1;

public class CuentaCorriente {
	double saldo;

	public CuentaCorriente(double saldo) {
		this.saldo = saldo;
	}
	
	public int generarAleatorio() {
		return (int) (2000 * Math.random())+250;
	}

	public void addSaldo(double ingreso) {
		System.out.println("\nEl saldo actual es: " + this.saldo + "€");
		System.out.println("Saldo a sumar:" + ingreso);
		this.saldo = this.saldo + ingreso;
		System.out.println("El saldo total es de: " + (this.saldo) + "€");
	}

	public double getSaldo() throws InterruptedException {
		Thread.sleep(generarAleatorio());
		return saldo;
	}

	public void setSaldo(double saldo) throws InterruptedException {
		Thread.sleep(generarAleatorio());
		this.saldo = saldo;
	}

	

}
