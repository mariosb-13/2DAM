package jdbc_2;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UD2_B3_mainHuecos {

	private static ConectorMySQL miConector;

	public static void main(String[] args) {

		// Conectar con la BD
		miConector = new ConectorMySQL("jdbc:mysql://192.168.13.182/", "proyectos", "adminProyectos", "adminProyectos1234");

		// Usar la conexion
		if (miConector.getConnect() != null) {
			try {
				System.out.println("--- Departamento 10 -------------------");
				System.out.println("--- Sin preparar: " + UD2_B3_getDepartamento("10"));
				System.out.println("--- 1) Sin preparar Injection: " + UD2_B3_T1_getDepartamento_SQLInjection("10"));
				System.out.println("--- 2) Preparada: " + UD2_B3_T2_getDepartamentoPreparado("10"));
				System.out.println("--- 3) Preparada Injection: " + UD2_B3_T3_getDepartamentoPreparado_SQLInjection("10"));
				System.out.println("--- 4) Para que se usan las consultas sin preparar?");
				System.out.println("---------------------------------------");
				System.out.println("--- Empleado 7369 -------------------");
				System.out.println("--- 5) Preparada: " + UD2_B3_T5_getEmpleadoPreparado("7369"));
				System.out.println("--- 6) Mostrar MetaDatos");
				UD2_B3_T6_getEmpleadoPreparadoMetadatos("7369");
			} catch (SQLException e) {
				System.err.println("Error en la conexion de la BD");
				e.printStackTrace();
			}

		} else {
			System.err.println("Error en la conexión");
		}

		// Liberar los recursos
		miConector.Release();
	}// Fin main


	private static Departamento UD2_B3_getDepartamento(String _dept_no) throws SQLException {
		//System.out.println("- getDepartamento(" + _dept_no + ") -");
		Statement sentenciaDepartamentos = miConector.getConnect().createStatement(); // Abrir sentencia
		String sqlSelect = "select *" + "from departamento " + "where dept_no=" + _dept_no + ";";
		// System.out.println("Consulta: " + sqlSelect);
		ResultSet resultadoDepartamentos = sentenciaDepartamentos.executeQuery(sqlSelect); // Abrir ResultSet

		if (resultadoDepartamentos.next()) {// Recorrer ResultSet
			int dept_no = resultadoDepartamentos.getInt("dept_no");
			String dnombre = resultadoDepartamentos.getString("dnombre");
			String loc = resultadoDepartamentos.getString("loc");

			resultadoDepartamentos.close();// Cerrar ResultSet
			sentenciaDepartamentos.close();// Cerrar Sentencia

			return new Departamento(dept_no, dnombre, loc);
		}

		resultadoDepartamentos.close();// Cerrar ResultSet
		sentenciaDepartamentos.close();// Cerrar Sentencia

		return null;
	}

	private static Departamento UD2_B3_T1_getDepartamento_SQLInjection(String _dept_no) throws SQLException {
	    // Modificamos el parámetro para provocar SQL Injection
	    String injection = _dept_no + " OR 1=1"; // Esto fuerza que la condición siempre sea verdadera

	    // Llamamos al método vulnerable
	    Departamento deptInyectado = UD2_B3_getDepartamento(injection);

	    // Mostrar el resultado por pantalla
	    if (deptInyectado != null) {
	        System.out.println("Resultado de SQL Injection: " + deptInyectado);
	    } else {
	        System.out.println("No se encontró ningún departamento (probablemente la inyección falló).");
	    }

	    return deptInyectado;
	}

	private static String UD2_B3_T2_getDepartamentoPreparado(String _dept_no) {
	    // Consulta preparada: el parámetro se enlaza y no se concatena en la cadena SQL,
	    // por lo que evita inyecciones SQL.
	    String sql = "SELECT * FROM departamento WHERE dept_no = ?";

	    // Convertimos el parámetro a int (dept_no es numérico en la tabla)
	    int deptNo;
	    try {
	        deptNo = Integer.parseInt(_dept_no);
	    } catch (NumberFormatException e) {
	        System.err.println("Parámetro dept_no no válido: " + _dept_no);
	        return null;
	    }

	    // Usamos try-with-resources para cerrar automáticamente PreparedStatement y ResultSet
	    try (PreparedStatement ps = miConector.getConnect().prepareStatement(sql)) {
	        // Enlazamos el parámetro en la posición 1
	        ps.setInt(1, deptNo);

	        // Ejecutamos la consulta
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // Construimos el objeto Departamento igual que en el método vulnerable
	                int dept_no = rs.getInt("dept_no");
	                String dnombre = rs.getString("dnombre");
	                String loc = rs.getString("loc");

	                Departamento dept = new Departamento(dept_no, dnombre, loc);

	                // Devolvemos la representación en String para que main la imprima
	                return dept.toString();
	            } else {
	                System.out.println("No existe departamento con dept_no = " + deptNo);
	                return null;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error en la ejecución de la consulta preparada:");
	        e.printStackTrace();
	        return null;
	    }
	}

	private static String UD2_B3_T3_getDepartamentoPreparado_SQLInjection(String _dept_no) {
		return UD2_B3_T2_getDepartamentoPreparado(_dept_no);// TODO
	}

	private static Empleado UD2_B3_T5_getEmpleadoPreparado(String _emp_no) {
	    // Consulta preparada para obtener el empleado según emp_no
	    String sql = "SELECT * FROM empleado WHERE emp_no = ?";

	    int empNo;
	    try {
	        // Validamos y convertimos el parámetro a entero
	        empNo = Integer.parseInt(_emp_no);
	    } catch (NumberFormatException e) {
	        System.err.println("Parámetro inválido para emp_no (no es un entero): \"" + _emp_no + "\"");
	        return null;
	    }

	    try (PreparedStatement ps = miConector.getConnect().prepareStatement(sql)) {
	        // Enlazamos el parámetro emp_no
	        ps.setInt(1, empNo);

	        // Ejecutamos la consulta
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // Obtenemos los datos del empleado
	                int emp_no = rs.getInt("emp_no");
	                String apellido = rs.getString("apellido");
	                String oficio = rs.getString("oficio");
	                int dir = rs.getInt("dir");
	                Date fecha_alt = rs.getDate("fecha_alt");
	                float salario = rs.getFloat("salario");
	                float comision = rs.getFloat("comision");
	                int dept_no = rs.getInt("dept_no");

	                // Creamos el objeto Empleado
	                Empleado emp = new Empleado(emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no);

	                return emp;
	            } else {
	                System.out.println("No existe empleado con emp_no = " + empNo);
	                return null;
	            }
	        }

	    } catch (SQLException e) {
	        System.err.println("[T5] Error en la ejecución de la consulta preparada:");
	        e.printStackTrace();
	        return null;
	    }
	}

	private static void UD2_B3_T6_getEmpleadoPreparadoMetadatos(String _emp_no) {
		// TODO
	}

}// Fin UD2_B3_main