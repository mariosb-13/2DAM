package tarea_4;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UD1_B1_T6_MarioSanchez {

    public static void main(String[] args) {
        try {
			File archivoXML = new File(System.getProperty("user.dir") + "/src/tarea_4/rss.aspx.xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            // Handler personalizado
            NoticiasHandler handler = new NoticiasHandler();
            saxParser.parse(archivoXML, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class NoticiasHandler extends DefaultHandler {

    private boolean esTitulo = false;
    private boolean esFecha = false;
    private boolean dentroItem = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            dentroItem = true; // Entramos en un item
        } else if (dentroItem && qName.equalsIgnoreCase("title")) {
            esTitulo = true;
        } else if (dentroItem && (qName.equalsIgnoreCase("pubDate") || qName.equalsIgnoreCase("dc:date"))) {
            esFecha = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            dentroItem = false; // Salimos del item
            System.out.println("------------------------------");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (esTitulo) {
            System.out.println("Título: " + new String(ch, start, length));
            esTitulo = false;
        } else if (esFecha) {
            System.out.println("Fecha: " + new String(ch, start, length));
            esFecha = false;
        }
    }
}
