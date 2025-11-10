package boletin_2;

import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UD2_Main {
	private static ConnectorMySQL miConector;

	public static void main(String[] args) {

		// Conectar con la BD
		miConector = new ConnectorMySQL("jdbc:mysql://192.168.13.182:3306/proyectos","adminProyectos", "adminProyectos1234");

		// Usar la conexion
		if (miConector.getConnect() != null) {
			try {
				System.out.println("---------------------------------------");
				UD2_B2_T3_MostrarDepartamentos();
				System.out.println("---------------------------------------");
				System.out.println("--- Departamento 10: " + UD2_B2_T4_getDepartamento("10"));
				System.out.println("--- Departamento 0: " + UD2_B2_T4_getDepartamento("0"));
				System.out.println("---------------------------------------");
				UD2_B2_T5_ReducirSalario("7369", 0.2);
				UD2_B2_T5_ReducirSalario("7369", -0.25);
				System.out.println("---------------------------------------");
				UD2_B2_T6_CadenaDeDireccion();

				
			} catch (SQLException e) {
				System.err.println("Error en la conexion de la BD");
				e.printStackTrace();
			}

		} else {
			System.err.println("Error en la conexión");
		}

		// Liberar los recursos
		miConector.Release();
	}// Fin main

	private static void UD2_B2_T3_MostrarDepartamentos() throws SQLException {
	    System.out.println("- INFORMACION DE LOS DEPARTAMENTOS -");

	    // Consulta SQL que obtiene todos los datos de Departamentos y la cantidad de empleados
	    String sql = "SELECT d.dept_no, d.dnombre, d.loc, COUNT(e.emp_no) AS num_empleados " +
	                 "FROM departamento d " +
	                 "LEFT JOIN empleado e ON d.dept_no = e.dept_no " +
	                 "GROUP BY d.dept_no, d.dnombre, d.loc " +
	                 "ORDER BY d.dept_no";

	    try (PreparedStatement pst = miConector.getConnect().prepareStatement(sql);
	         java.sql.ResultSet rs = pst.executeQuery()) {

	        // Mostrar resultados
	        while (rs.next()) {
	            String deptNo = rs.getString("dept_no");
	            String nombre = rs.getString("dnombre");
	            String loc = rs.getString("loc");
	            int numEmpleados = rs.getInt("num_empleados");

	            System.out.printf("Departamento %s: %s, Ubicación: %s, Número de empleados: %d%n",
	                    deptNo, nombre, loc, numEmpleados);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al mostrar los departamentos");
	        e.printStackTrace();
	    }
	}

	

	private static Departamento UD2_B2_T4_getDepartamento(String _dept_no) throws SQLException {
		System.out.println("- getDepartamento(" + _dept_no + ") -");
		String sql="SELECT * FROM departamento WHERE dept_no="+_dept_no;
		
		java.sql.Statement st=miConector.getConnect().createStatement();
		
		ResultSet rs= st.executeQuery(sql);
		Departamento dept = null;
		
		while (rs.next()) {
			int deptNo=rs.getInt("dept_no");
			String nombre=rs.getString("dnombre");
			String localizacion=rs.getString("loc");
			
			dept= new Departamento(deptNo,nombre,localizacion);
		}
		
		rs.close();
		
		
		return dept;
	}

	private static Empleado UD2_B2_T5_getEmpleado(String _emp_no) throws SQLException {
		// Obtener los datos del empleado
		//TODO
		return null;
	}

	private static void UD2_B2_T5_ReducirSalario(String _emp_no, double _desc) throws SQLException {
		System.out.println("- ReducirSalario(" + _emp_no + ", " + _desc + ")");
		//TODO

	}

	private static void UD2_B2_T6_CadenaDeDireccion() throws SQLException {
		System.out.println("- CadenaDeJefes()");
		//TODO
	}
	

}// Fin UD2_B2_main