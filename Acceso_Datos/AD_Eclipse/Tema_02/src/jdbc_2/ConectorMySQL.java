package jdbc_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorMySQL {
	private Connection connect;
	private String schema;
	private String url;
	private String user;
	private String pass;

	public Connection getConnect() {
		return this.connect;
	}

	public ConectorMySQL(String _url, String _schema, String _user, String _pass) {
		try {
			this.schema = _schema;
			this.url = _url + _schema;
			this.user = _user;
			this.pass = _pass;
			System.out.print("--- CONECTANDO A LA BD ---");
			// 2. Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 3. Establecer la conexión
			this.connect = DriverManager.getConnection(url, user, pass);
			System.out.println(" OK ---");

		} catch (ClassNotFoundException e) {// Cargar el driver
			System.err.println("No se ha encontrado el Driver");
			e.printStackTrace();
		} catch (SQLException e) {// Establecer la conexión o ejecutar sentencias
			System.err.println("Error en la conexion de la BD");
			e.printStackTrace();
		}
	}

	public void Release() {
		try {// 5. Finalizar la conexión
			System.out.print("--- CERRANDO CONEXION ---");
			if (this.connect != null) {
				this.connect.close();
			}
			this.connect = null;
			this.schema = null;
			this.url = null;
			this.user = null;
			this.pass = null;
			System.out.println(" OK ---");

		} catch (SQLException e) {
			System.err.println("No se ha podido cerrar la conexion con la BD");
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.Release();
	}

}