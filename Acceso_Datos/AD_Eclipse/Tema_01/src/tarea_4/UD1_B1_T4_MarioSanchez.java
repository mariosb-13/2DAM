package tarea_4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UD1_B1_T4_MarioSanchez {

    public static void main(String[] args) {
        try {
            // Creamos algunos ingredientes
            List<Ingrediente> ingredientes = new ArrayList<>();
            ingredientes.add(new Ingrediente("Harina", "200g"));
            ingredientes.add(new Ingrediente("Azúcar", "100g"));
            ingredientes.add(new Ingrediente("Huevos", "2 unidades"));
            ingredientes.add(new Ingrediente("Leche", "250ml"));

            // Creamos la receta
            Receta receta = new Receta(
                    "Bizcocho casero",
                    ingredientes,
                    "Mezcla todos los ingredientes, vierte en un molde y hornea a 180°C durante 30 minutos.",
                    "45 minutos"
            );

            // Creamos el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Elemento raíz <receta>
            Element raiz = doc.createElement("receta");
            doc.appendChild(raiz);

            // Título
            anadeElemento(doc, raiz, "titulo", receta.getTitulo());

            // Ingredientes
            Element ingredientesElem = doc.createElement("ingredientes");
            raiz.appendChild(ingredientesElem);

            for (Ingrediente ing : receta.getIngredientes()) {
                Element ingredienteElem = doc.createElement("ingrediente");
                ingredientesElem.appendChild(ingredienteElem);

                // Cada ingrediente tiene nombre y cantidad
                anadeElemento(doc, ingredienteElem, "nombre", ing.getNombreIngrediente());
                anadeElemento(doc, ingredienteElem, "cantidad", ing.getCantidad());
            }

            // Procedimiento y tiempo
            anadeElemento(doc, raiz, "procedimiento", receta.getProcedimiento());
            anadeElemento(doc, raiz, "tiempo", receta.getTiempo());

            // Guardamos el XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("miRecetaDOM.xml"));
            transformer.transform(source, result);

            System.out.println("Archivo 'miRecetaDOM.xml' creado correctamente.");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un elemento [clave] con el texto [valor] y lo añade como hijo de [raiz].
     * Retorna el elemento creado.
     * @param doc
     * @param raiz
     * @param clave
     * @param valor
     * @return
     */
    static Element anadeElemento(Document doc, Element raiz, String clave, String valor) {
        Element nuevoElemento = doc.createElement(clave);
        nuevoElemento.appendChild(doc.createTextNode(valor));
        raiz.appendChild(nuevoElemento);
        return nuevoElemento;
    }
}
