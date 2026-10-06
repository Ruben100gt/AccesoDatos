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

public class Ejercicio10 {
    public static void main(String[] args) {
        // Creamos la lista para guardar los alumnos
        List<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno(1, "Miriam García", 18, 4.3));
        listaAlumnos.add(new Alumno(2, "Iván Terroba", 19, 6.7));

        try {
            // Creamos un documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            // Creamos la etiqueta raíz <alumnos>
            Element raiz = documento.createElement("alumnos");
            documento.appendChild(raiz);

            // Recorremos los alumnos
            for (Alumno a : listaAlumnos) {
                Element alumno = documento.createElement("alumno");

                // Creamos las sub-etiquetas con la información
                Element id = documento.createElement("id");
                id.setTextContent(String.valueOf(a.getId()));
                alumno.appendChild(id);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(a.getNombre());
                alumno.appendChild(nombre);

                Element edad = documento.createElement("edad");
                edad.setTextContent(String.valueOf(a.getEdad()));
                alumno.appendChild(edad);

                Element nota = documento.createElement("nota");
                nota.setTextContent(String.valueOf(a.getNota()));
                alumno.appendChild(nota);

                // Añadimos el alumno a la raíz
                raiz.appendChild(alumno);
            }

            // Generamos el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documento);
            
            // Formateamos el XML
            StreamResult result = new StreamResult(new File("src/alumnos2.xml"));
            
            // Guardamos el documento
            transformer.transform(source, result);

            System.out.println("Archivo alumnos2.xml crado.");

        } catch (Exception e) {
            System.out.println("Error al generar el XML: " + e.getMessage());
        }
    }
}