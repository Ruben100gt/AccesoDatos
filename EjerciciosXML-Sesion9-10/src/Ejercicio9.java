import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio9 {
    public static void main(String[] args){
        try {

            File archivo = new File("src/alumnos.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            NodeList alumnos = documento.getElementsByTagName("alumno");

            for (int i = 0; i < alumnos.getLength(); i++){
                Element alumno = (Element) alumnos.item(i);

                String id = alumno.getElementsByTagName("id").item(0).getTextContent();
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
                String edad = alumno.getElementsByTagName("edad").item(0).getTextContent();
                String nota = alumno.getElementsByTagName("nota").item(0).getTextContent();

                List <Alumno> listaAlumnos = new ArrayList<>();

                

            }

        } catch (Exception e){
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}