package boletin_2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UD2_Main {
	private static ConnectorMySQL miConector;

	public static void main(String[] args) {

		// Conectar con la BD
		miConector = new ConnectorMySQL("jdbc:mysql://192.168.13.182:3306/proyectos", "adminProyectos",
				"adminProyectos1234");

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

		// Consulta SQL que obtiene todos los datos de Departamentos y la cantidad de
		// empleados
		String sql = "SELECT d.dept_no, d.dnombre, d.loc, COUNT(e.emp_no) AS num_empleados " + "FROM departamento d "
				+ "LEFT JOIN empleado e ON d.dept_no = e.dept_no " + "GROUP BY d.dept_no, d.dnombre, d.loc "
				+ "ORDER BY d.dept_no";

		try (Statement st = miConector.getConnect().createStatement(); ResultSet rs = st.executeQuery(sql)) {

			// Mostrar resultados
			while (rs.next()) {
				String deptNo = rs.getString("dept_no");
				String nombre = rs.getString("dnombre");
				String loc = rs.getString("loc");
				int numEmpleados = rs.getInt("num_empleados");

				System.out.printf("Departamento %s: %s, Ubicación: %s, Número de empleados: %d%n", deptNo, nombre, loc,
						numEmpleados);
			}

		} catch (SQLException e) {
			System.err.println("Error al mostrar los departamentos");
			e.printStackTrace();
		}
	}

	private static Departamento UD2_B2_T4_getDepartamento(String _dept_no) throws SQLException {
		System.out.println("- getDepartamento(" + _dept_no + ") -");
		String sql = "SELECT * FROM departamento WHERE dept_no=" + _dept_no;

		java.sql.Statement st = miConector.getConnect().createStatement();

		ResultSet rs = st.executeQuery(sql);
		Departamento dept = null;

		while (rs.next()) {
			int deptNo = rs.getInt("dept_no");
			String nombre = rs.getString("dnombre");
			String localizacion = rs.getString("loc");

			dept = new Departamento(deptNo, nombre, localizacion);
		}

		rs.close();

		return dept;
	}

	private static Empleado UD2_B2_T5_getEmpleado(String _emp_no) throws SQLException {

		String sql = "SELECT * FROM empleado WHERE emp_no = " + _emp_no;

		try (Statement st = miConector.getConnect().createStatement(); ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				int empNo = rs.getInt("emp_no");
				String apellido = rs.getString("apellido");
				String oficio = rs.getString("oficio");
				int dir = rs.getInt("dir");
				java.sql.Date fechaAlt = rs.getDate("fecha_alt");
				float salario = rs.getFloat("salario");
				float comision = rs.getFloat("comision");
				int deptNo = rs.getInt("dept_no");

				return new Empleado(empNo, apellido, oficio, dir, fechaAlt, salario, comision, deptNo);
			} else {
				System.out.println("No se encontró el empleado con emp_no = " + _emp_no);
				return null;
			}
		}
	}

	private static void UD2_B2_T5_ReducirSalario(String _emp_no, double _desc) throws SQLException {
		System.out.println("- ReducirSalario(" + _emp_no + ", " + _desc + ")");

		// Validación de porcentaje
		if (_desc < 0 || _desc >= 1) {
			System.out.println("El porcentaje debe estar entre 0 y 1. Valor recibido: " + _desc);
			return;
		}

		// Obtener el empleado
		Empleado emp = UD2_B2_T5_getEmpleado(_emp_no);

		if (emp == null) {
			System.out.println("No se pudo aplicar la reducción: el empleado no existe.");
			return;
		}

		double salarioAnterior = emp.getSalario();
		double nuevoSalario = salarioAnterior * (1 - _desc);

		// Actualizar en la BD
		String sqlUpdate = "UPDATE empleado SET salario = " + nuevoSalario + " WHERE emp_no = " + _emp_no;

		try (Statement st = miConector.getConnect().createStatement()) {
			int filas = st.executeUpdate(sqlUpdate);

			if (filas > 0) {
				System.out.printf("Salario actualizado para el empleado %s:%n", _emp_no);
				System.out.printf("Salario anterior: %.2f €%n", salarioAnterior);
				System.out.printf("Nuevo salario:    %.2f €%n", nuevoSalario);
			} else {
				System.out.println("No se pudo actualizar el salario (empleado no encontrado).");
			}
		}
	}

	private static void UD2_B2_T6_CadenaDeDireccion() throws SQLException {
	    System.out.println("- CadenaDeJefes()\n");

	    String sql = "SELECT * FROM empleado";

	    try (Statement st = miConector.getConnect().createStatement();
	         ResultSet rs = st.executeQuery(sql)) {

	        while (rs.next()) {
	            String apellido = rs.getString("apellido");
	            int dir = rs.getInt("dir");

	            StringBuilder cadena = new StringBuilder();

	            // Si el empleado tiene jefe
	            if (dir != 0) {
	                Empleado jefe = UD2_B2_T5_getEmpleado(String.valueOf(dir));

	                // Ir subiendo por la jerarquía
	                while (jefe != null) {
	                    // Añadir el apellido del jefe al final de la cadena
	                    if (cadena.length() > 0) {
	                        cadena.append(" > ");
	                    }
	                    cadena.append(jefe.getApellido());

	                    // Si no tiene más jefe, termina
	                    if (jefe.getDir() == 0)
	                        break;

	                    jefe = UD2_B2_T5_getEmpleado(String.valueOf(jefe.getDir()));
	                }
	            }

	            // Mostrar el resultado
	            if (cadena.length() == 0) {
	                // El empleado no tiene jefe
	                System.out.println(apellido);
	            } else {
	                // Mostrar cadena: empleado > jefe > jefe del jefe ...
	                System.out.println(apellido + " > " + cadena);
	            }
	            System.out.println();
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al obtener la cadena de jefes");
	        e.printStackTrace();
	    }
	}


}// Fin UD2_B2_main