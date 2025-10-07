package tarea_2;

import java.io.*;

public class UD1_B1_T5_MarioSanchez {

	public static void main(String[] args) {
		File f = new File("pedidos.dat");

		// Crear un array de 3 pedidos iniciales
		Pedido[] pedidosIniciales = { new Pedido("Camiseta", 10, 15.5), new Pedido("Pantalón", 5, 35.0),
				new Pedido("Zapatillas", 8, 60.0) };

		// Escribir los pedidos en el archivo
		escribirPedidos(pedidosIniciales, f);

		// Mostrar los pedidos guardados
		leerPedidos(f);

		System.out.println("-------------");

		// Crear dos pedidos adicionales
		Pedido[] nuevosPedidos = { new Pedido("Sudadera", 3, 45.0), new Pedido("Calcetines", 20, 3.0) };

		// Añadirlos al final del archivo
		annadePedidos(nuevosPedidos, f);

		// Mostrar nuevamente el contenido del archivo
		leerPedidos(f);
	}

	/**
	 * Escribe un array de pedidos en el archivo (sobrescribe el contenido anterior)
	 * @param pedidos
	 * @param f
	 */
	public static void escribirPedidos(Pedido[] pedidos, File f) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
			for (Pedido p : pedidos) {
				oos.writeObject(p);
			}
			System.out.println("Pedidos escritos correctamente en el archivo.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lee y muestra los pedidos guardados en el archivo
	 * @param f
	 */
	public static void leerPedidos(File f) {
		if (!f.exists()) {
			System.out.println("El archivo no existe.");
			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			System.out.println("Pedidos guardados en el archivo:");
			while (true) {
				Pedido p = (Pedido) ois.readObject();
				System.out.println(p);
			}
		} catch (EOFException e) {
			// Fin del archivo, es normal
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Añade nuevos pedidos al final del archivo sin sobrescribir los existentes
	 * @param pedidos
	 * @param f
	 */
	public static void annadePedidos(Pedido[] pedidos, File f) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f, true)) {
			// Evitamos escribir la cabecera de nuevo
			@Override
			protected void writeStreamHeader() throws IOException {
				reset();
			}
		}) {
			for (Pedido p : pedidos) {
				oos.writeObject(p);
			}
			System.out.println("Pedidos añadidos correctamente al final del archivo.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
