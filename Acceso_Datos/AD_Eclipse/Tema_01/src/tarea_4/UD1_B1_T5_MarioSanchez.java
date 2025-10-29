package tarea_4;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class UD1_B1_T5_MarioSanchez {
	public static void main(String[] args) {
		try {
			// Ruta del XML
			File archivoXML = new File(System.getProperty("user.dir") + "/src/tarea_4/peliculas.xml");
			// Crear el parser SAX
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			// Implementación
			DefaultHandler manejador = new DefaultHandler() {
				boolean enTitulo = false;
				boolean enFecha = false;
				boolean enDirector = false;
				boolean enActor = false;

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					if (qName.equalsIgnoreCase("Pelicula")) {
						System.out.println("------------------------------------------------");
					} else if (qName.equalsIgnoreCase("Titulo")) {
						enTitulo = true;
					} else if (qName.equalsIgnoreCase("Fecha")) {
						enFecha = true;
					} else if (qName.equalsIgnoreCase("Director")) {
						enDirector = true;
					} else if (qName.equalsIgnoreCase("Actor")) {
						enActor = true;
					}
				}

				@Override
				public void characters(char[] ch, int start, int length) {
					String texto = new String(ch, start, length).trim();

					if (texto.length() > 0) {
						if (enTitulo) {
							System.out.println("Título: " + texto);
							enTitulo = false;
						} else if (enFecha) {
							System.out.println("Año: " + texto);
							enFecha = false;
						} else if (enDirector) {
							System.out.println("Director: " + texto);
							System.out.println("Actores principales:");
							enDirector = false;
						} else if (enActor) {
							System.out.println("  - " + texto);
							enActor = false;
						}
					}
				}

				@Override
				public void endElement(String uri, String localName, String qName) {
					// No es necesario hacer nada al cerrar etiquetas
				}
			};

			// Parsear el archivo XML con el manejador
			saxParser.parse(archivoXML, manejador);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
