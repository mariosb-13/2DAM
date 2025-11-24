package test;

import org.hibernate.Session;

import entities.Departamento;
import entities.Empleado;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtils.openSession();
		// ACCEDER A LAS RELACIONES
		// Uso de la conexion
		session.beginTransaction(); // Crea la transaccion
		Departamento dep = session.get(Departamento.class, 10); // pesistente
		if (dep == null) {
			System.out.println("El departamento 10 no existe");
		} else {
			System.out.println("DEPARTAMENTO ->" + dep);
			System.out.println("EMPLEADOS:");
			for (Empleado e : dep.empleados) {
				System.out.println(e);
			}
		}
		session.getTransaction().commit(); // Valida la transacción

		session.close();
		HibernateUtils.closeSessionFactory();

	}

}
