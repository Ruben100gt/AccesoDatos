import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio8 {
    public static void main(String[] args){
        try {
            // Cargamos el archivo XML
            File archivo = new File("src/alumnos.xml");

            // Leemos el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            // Obtenemos la lista de todas las etiquetas <alumno>
            NodeList alumnos = documento.getElementsByTagName("alumno");

            // Recorremos los alumnos
            for (int i = 0; i < alumnos.getLength(); i++){
                Element alumno = (Element) alumnos.item(i);

                // Sacamos la información de cada sub-etiqueta
                String id = alumno.getElementsByTagName("id").item(0).getTextContent();
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
                String edad = alumno.getElementsByTagName("edad").item(0).getTextContent();
                String nota = alumno.getElementsByTagName("nota").item(0).getTextContent();

                // Imprimimos los datos
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Nota: " + nota);
                System.out.println("----------------");

            }

        } catch (Exception e){
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}