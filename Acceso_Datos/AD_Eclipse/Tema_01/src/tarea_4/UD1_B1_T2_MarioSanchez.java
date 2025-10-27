package tarea_4;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UD1_B1_T2_MarioSanchez {

	public static class LectorPeliculas {
		public static void main(String[] args) {
			File archivoXML = new File(System.getProperty("user.dir") + "/tarea_4/peliculas.xml");
			ArrayList<Pelicula> peliculas = LectorPeliculas.cargarPeliculas(archivoXML);

			for (Pelicula p : peliculas) {
				System.out.println(p);
			}
		}

		/**
		 * Funcion que carga la película
		 * 
		 * @param f
		 * @return
		 */
		public static ArrayList<Pelicula> cargarPeliculas(File f) {
			ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document documento = builder.parse(f);
				documento.getDocumentElement().normalize();

				NodeList listaNodos = documento.getElementsByTagName("Pelicula");

				for (int i = 0; i < listaNodos.getLength(); i++) {
					Node nodo = listaNodos.item(i);
					if (nodo.getNodeType() == Node.ELEMENT_NODE) {
						Element elem = (Element) nodo;
						Pelicula p = new Pelicula();

						// Título (obligatorio)
						String titulo = elem.getElementsByTagName("Titulo").item(0).getTextContent();
						if (titulo == null || titulo.trim().isEmpty()) {
							throw new Exception("La película no tiene título.");
						}
						p.setTitulo(titulo);

						// Duración
						try {
							String durStr = getTexto(elem, "Duracion");
							p.setDuracion(durStr != null ? Integer.parseInt(durStr) : null);
						} catch (NumberFormatException e) {
							p.setDuracion(null);
						}

						// Género
						p.setGenero(getTexto(elem, "Genero"));

						// Sinopsis (quitando saltos de línea)
						String sinopsis = getTexto(elem, "sinopsis");
						if (sinopsis != null)
							sinopsis = sinopsis.replaceAll("\\s+", " ").trim();
						p.setSinopsis(sinopsis);

						// Actores
						NodeList actoresNodos = elem.getElementsByTagName("Actor");
						ArrayList<String> actores = new ArrayList<>();
						for (int j = 0; j < actoresNodos.getLength(); j++) {
							actores.add(actoresNodos.item(j).getTextContent());
						}
						p.setActores(actores);

						// Fecha
						try {
							String fechaStr = getTexto(elem, "Fecha");
							p.setFecha(fechaStr != null && !fechaStr.equals("0") ? Integer.parseInt(fechaStr) : null);
						} catch (NumberFormatException e) {
							p.setFecha(null);
						}

						// Director
						p.setDirector(getTexto(elem, "Director"));

						listaPeliculas.add(p);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return listaPeliculas;
		}

		/**
		 * Función auxiliar para obtener texto de un nodo, devuelve null si no existe
		 * 
		 * @param elem
		 * @param tag
		 * @return
		 */
		private static String getTexto(Element elem, String tag) {
			NodeList nodos = elem.getElementsByTagName(tag);
			if (nodos.getLength() > 0 && nodos.item(0).getTextContent() != null
					&& !nodos.item(0).getTextContent().trim().isEmpty()) {
				return nodos.item(0).getTextContent();
			}
			return null;
		}
	}

}
