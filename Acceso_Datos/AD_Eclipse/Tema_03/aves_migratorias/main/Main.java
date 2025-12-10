package main;

import entities.*;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
		Main app = new Main();

		System.out.println("--- VACIANDO BD ---");
		app.vaciarBD();

		System.out.println("--- LLENANDO BD ---");
		app.llenarBD();

		// Cerrar SessionFactory al final de todo
		HibernateUtil.shutdown();
		System.out.println("--- FIN ---");
	}

	public void vaciarBD() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// Desactivar checks FK temporalmente si quisieras ser brusco,
			// pero lo haremos ordenadamente:

			// 1. Borrar hijos (Observaciones)
			session.createNativeQuery("DELETE FROM OBSERVACION").executeUpdate();

			// 2. Borrar Observadores
			session.createNativeQuery("DELETE FROM OBSERVADOR").executeUpdate();

			// 3. Borrar Especies y Zonas
			session.createNativeQuery("DELETE FROM ESPECIE").executeUpdate();
			session.createNativeQuery("DELETE FROM ZONA_OBSERVACION").executeUpdate();

			// 4. Borrar Padres
			session.createNativeQuery("DELETE FROM GRUPO_ORNITOLOGICO").executeUpdate();
			session.createNativeQuery("DELETE FROM ASOCIACION").executeUpdate();
			session.createNativeQuery("DELETE FROM PROVINCIA").executeUpdate();

			// Resetear auto_increment de Observador
			session.createNativeQuery("ALTER TABLE OBSERVADOR AUTO_INCREMENT = 1").executeUpdate();

			tx.commit();
			System.out.println(">> BD Vaciada.");
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void llenarBD() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// --- 1. PROVINCIAS ---
			Provincia p1 = new Provincia("Huelva", "Andalucía");
			Provincia p2 = new Provincia("Madrid", "Madrid");
			Provincia p3 = new Provincia("Caceres", "Extremadura");
			Provincia p4 = new Provincia("Asturias", "Asturias");
			Provincia p5 = new Provincia("Valencia", "Comunidad Valenciana");
			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.save(p4);
			session.save(p5);

			// --- 2. ZONAS ---
			ZonaObservacion z1 = new ZonaObservacion("Z01", "Doñana", "Humedal", p1);
			ZonaObservacion z2 = new ZonaObservacion("Z02", "El Pardo", "Bosque", p2);
			ZonaObservacion z3 = new ZonaObservacion("Z03", "Monfragüe", "Montaña", p3);
			ZonaObservacion z4 = new ZonaObservacion("Z04", "Picos Europa", "Alta Montaña", p4);
			ZonaObservacion z5 = new ZonaObservacion("Z05", "Albufera", "Lago", p5);
			session.save(z1);
			session.save(z2);
			session.save(z3);
			session.save(z4);
			session.save(z5);

			// --- 3. ASOCIACIONES ---
			Asociacion a1 = new Asociacion("SEO", "Madrid", 911111111L);
			Asociacion a2 = new Asociacion("Grup Balear", "Palma", 971111111L);
			Asociacion a3 = new Asociacion("Amigos Aves", "Valencia", 961111111L);
			Asociacion a4 = new Asociacion("Aguilas Unidas", "Caceres", 927111111L);
			Asociacion a5 = new Asociacion("Norte Aves", "Oviedo", 985111111L);
			session.save(a1);
			session.save(a2);
			session.save(a3);
			session.save(a4);
			session.save(a5);

			// --- 4. GRUPOS ORNITOLÓGICOS ---
			GrupoOrnitologico g1 = new GrupoOrnitologico("Rapaces", "Fuertes", "Garras", "Ganchudo");
			GrupoOrnitologico g2 = new GrupoOrnitologico("Zancudas", "Largas", "Finos", "Largo");
			GrupoOrnitologico g3 = new GrupoOrnitologico("Patos", "Cortas", "Palmeados", "Plano");
			GrupoOrnitologico g4 = new GrupoOrnitologico("Paseriformes", "Cortas", "Tres delante", "Fino");
			GrupoOrnitologico g5 = new GrupoOrnitologico("Marinas", "Cortas", "Palmeados", "Fuerte");
			session.save(g1);
			session.save(g2);
			session.save(g3);
			session.save(g4);
			session.save(g5);

			// --- 5. ESPECIES ---
			Especie e1 = new Especie("Aquila", "Aguila Real", "Grande", g1);
			Especie e2 = new Especie("Ciconia", "Cigueña", "Migratoria", g2);
			Especie e3 = new Especie("Anas", "Pato Real", "Comun", g3);
			Especie e4 = new Especie("Passer", "Gorrión", "Urbano", g4);
			Especie e5 = new Especie("Larus", "Gaviota", "Costera", g5);
			session.save(e1);
			session.save(e2);
			session.save(e3);
			session.save(e4);
			session.save(e5);

			// --- 6. OBSERVADORES ---
			Observador ob1 = new Observador("111A", "Juan", "Calle A", a1, z1);
			Observador ob2 = new Observador("222B", "Ana", "Calle B", a2, z2);
			Observador ob3 = new Observador("333C", "Luis", "Calle C", a3, z3);
			Observador ob4 = new Observador("444D", "Maria", "Calle D", a4, z4);
			Observador ob5 = new Observador("555E", "Pedro", "Calle E", a5, z5);
			session.save(ob1);
			session.save(ob2);
			session.save(ob3);
			session.save(ob4);
			session.save(ob5);

			// --- 7. OBSERVACIONES ---
			Date d1 = new GregorianCalendar(2023, 4, 15).getTime();
			Date d2 = new GregorianCalendar(2023, 5, 20).getTime();
			Date d3 = new GregorianCalendar(2023, 6, 10).getTime();
			Date d4 = new GregorianCalendar(2023, 7, 01).getTime();
			Date d5 = new GregorianCalendar(2023, 8, 12).getTime();

			Observacion o1 = new Observacion(e1, ob1, d1);
			Observacion o2 = new Observacion(e2, ob2, d2);
			Observacion o3 = new Observacion(e3, ob3, d3);
			Observacion o4 = new Observacion(e4, ob4, d4);
			Observacion o5 = new Observacion(e5, ob5, d5);
			session.save(o1);
			session.save(o2);
			session.save(o3);
			session.save(o4);
			session.save(o5);

			tx.commit();
			System.out.println(">> BD Poblada Correctamente.");
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}