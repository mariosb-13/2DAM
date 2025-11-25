package tarea1;

public class Usuario extends Thread {

	CuentaCorriente cc;
	double saldo;
	
	
	public Usuario(CuentaCorriente cc) {
		this.cc = cc;
	}


	@Override
	public void run() {
		cc.addSaldo(150);
	}

}
