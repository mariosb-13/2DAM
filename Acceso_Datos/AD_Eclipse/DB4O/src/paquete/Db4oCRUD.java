package paquete;

import java.text.SimpleDateFormat;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Db4oCRUD {

	// Formato de fecha para parsear los datos del script SQL
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");

	// --- CREATE / INSERT ---
	public static void inicializarBD(ObjectContainer db) {
		System.out.println("\n--- 1. Inicializando (INSERT) ---");
		try {
			// Departamentos
			db.store(new Departamento(10, "CONTABILIDAD", "SEVILLA"));
			db.store(new Departamento(20, "INVESTIGACIÓN", "MADRID"));
			db.store(new Departamento(30, "VENTAS", "BARCELONA"));
			db.store(new Departamento(40, "PRODUCCIÓN", "BILBAO"));

			// Empleados (Ejemplos basados en el script DML)
			db.store(new Empleado(7839, "REY", "PRESIDENTE", 0, SDF.parse("1991/11/17"), 4100.0, null, 10));
			db.store(new Empleado(7782, "CEREZO", "DIRECTOR", 7839, SDF.parse("1991/06/09"), 2885.0, null, 10));
			db.store(new Empleado(7369, "SÁNCHEZ", "EMPLEADO", 7902, SDF.parse("1990/12/17"), 1040.0, null, 20));

			// Proyecto
			db.store(new Proyecto(1, "Comparadores"));

			// Trabaja
			db.store(new Trabaja(7782, 1, 30));

			db.commit();
			System.out.println("Datos iniciales cargados (Commit).");
		} catch (Exception e) {
			System.err.println("Error al cargar datos: " + e.getMessage());
		}
	}

	// --- SELECT (Query by Example) ---
	public static void consultarEmpleados(ObjectContainer db) {
		System.out.println("\n--- 2. Consultando Empleados (SELECT) ---");

		// Criterio de búsqueda: Todos los empleados (objeto vacío)
		Empleado ejemplo = new Empleado(0, null, null, 0, null, 0.0, null, 0);

		// Consulta
		ObjectSet<Empleado> resultados = db.queryByExample(ejemplo);

		System.out.println("Total de empleados: " + resultados.size());
		while (resultados.hasNext()) {
			System.out.println("- " + resultados.next());
		}
	}

	// --- UPDATE / MODIFY ---
	public static void modificarSalario(ObjectContainer db, String apellido, double incremento) {
		System.out.println("\n--- 3. Modificando (UPDATE) ---");
		Empleado ejemplo = new Empleado(0, apellido, null, 0, null, 0.0, null, 0);
		ObjectSet<Empleado> resultados = db.queryByExample(ejemplo);

		if (resultados.hasNext()) {
			Empleado emp = resultados.next();
			System.out.println("Salario anterior de " + apellido + ": " + emp.getSalario());

			// Modificación en memoria
			emp.setSalario(emp.getSalario() + incremento);

			// Almacenar el objeto modificado
			db.store(emp);
			db.commit();
			System.out.println("Salario actualizado. Nuevo salario: " + emp.getSalario());
		} else {
			System.out.println("Empleado " + apellido + " no encontrado para actualizar.");
		}
	}

	// --- DELETE ---
	public static void eliminarEmpleado(ObjectContainer db, String apellido) {
		System.out.println("\n--- 4. Eliminando (DELETE) ---");
		Empleado ejemplo = new Empleado(0, apellido, null, 0, null, 0.0, null, 0);
		ObjectSet<Empleado> resultados = db.queryByExample(ejemplo);

		if (resultados.hasNext()) {
			Empleado emp = resultados.next();
			db.delete(emp);
			db.commit();
			System.out.println("Empleado " + apellido + " eliminado.");
		} else {
			System.out.println("Empleado " + apellido + " no encontrado para eliminar.");
		}
	}
}