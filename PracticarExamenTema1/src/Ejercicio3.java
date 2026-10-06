import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio3 {
    public static void main(String[] args){
        // 1. Preparar los datos en memoria
        List<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno(1, "Ana", "Garcia", 20, 8.5));
        listaAlumnos.add(new Alumno(2, "Luis", "Perez", 21, 7.0));

        try {
            // 2. Fábrica para crear el documento XML vacío[cite: 6, 7]
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            // 3. Crear etiqueta raíz <alumnos> y pegarla al documento[cite: 7]
            Element raiz = documento.createElement("alumnos");
            documento.appendChild(raiz);

            // 4. Recorrer nuestro ArrayList para generar un <alumno> por cada uno
            for (Alumno a : listaAlumnos) {
                Element alumno = documento.createElement("alumno");

                // Crear <id>. CLAVE: XML solo admite texto, usamos String.valueOf() para el int[cite: 7]
                Element id = documento.createElement("id");
                id.setTextContent(String.valueOf(a.getId()));
                alumno.appendChild(id); // Pegar el <id> dentro de <alumno>

                // Crear <nombre> y pegarlo dentro de <alumno>[cite: 7]
                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(a.getNombre());
                alumno.appendChild(nombre);

                // Pegar el <alumno> terminado dentro de la raíz <alumnos>[cite: 7]
                raiz.appendChild(alumno);
            }

            // 5. Fábrica para guardar el documento en un archivo físico[cite: 8, 9]
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            // Indicar qué documento guardar (source) y dónde guardarlo (result)[cite: 8]
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File("src/alumnos-exportados.xml"));

            // Ejecutar el guardado[cite: 9]
            transformer.transform(source, result);
            System.out.println("Archivo XML creado correctamente.");
            
        } catch (Exception e) { // Capturar cualquier fallo creando el XML
            System.out.println("Error al generar el fichero XML: " + e.getMessage());
        }
    }
}