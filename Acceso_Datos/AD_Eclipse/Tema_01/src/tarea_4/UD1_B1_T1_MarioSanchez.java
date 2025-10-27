package tarea_4;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UD1_B1_T1_MarioSanchez {
    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
			File archivoXML = new File(System.getProperty("user.dir") + "/tarea_4/peliculas.xml");
            
            // Crear el parser DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Parsear el archivo y obtener el documento DOM
            Document documento = builder.parse(archivoXML);
            documento.getDocumentElement().normalize();
            
            // Obtener todas las etiquetas <Pelicula>
            NodeList listaPeliculas = documento.getElementsByTagName("Pelicula");
            
            // Recorrer la lista de películas
            for (int i = 0; i < listaPeliculas.getLength(); i++) {
                Node nodoPelicula = listaPeliculas.item(i);
                
                if (nodoPelicula.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoPelicula = (Element) nodoPelicula;
                    
                    // Obtener datos básicos
                    String titulo = elementoPelicula.getElementsByTagName("Titulo").item(0).getTextContent();
                    String fecha = elementoPelicula.getElementsByTagName("Fecha").item(0).getTextContent();
                    String director = elementoPelicula.getElementsByTagName("Director").item(0).getTextContent();
                    
                    System.out.println("------------------------------------------------");
                    System.out.println("Título: " + titulo);
                    System.out.println("Año: " + fecha);
                    System.out.println("Director: " + director);
                    System.out.println("Actores principales:");
                    
                    // Obtener actores
                    NodeList listaActores = elementoPelicula.getElementsByTagName("Actor");
                    for (int j = 0; j < listaActores.getLength(); j++) {
                        System.out.println("  - " + listaActores.item(j).getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
