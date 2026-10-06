import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio9 {
    public static void main(String[] args){
        // Creamos la lista
        List <Alumno> listaAlumnos = new ArrayList<>();

        try {
            // Cargamos el archivo XML
            File archivo = new File("src/alumnos.xml");

            // Leemos el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            // Obtenemos la lista de todas las etiquetas
            NodeList alumnos = documento.getElementsByTagName("alumno");

            // Recorremos los alumnos
            for (int i = 0; i < alumnos.getLength(); i++){
                Element alumno = (Element) alumnos.item(i);

                // Sacamos la información y la convertimos
                int id = Integer.parseInt(alumno.getElementsByTagName("id").item(0).getTextContent());
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
                int edad = Integer.parseInt(alumno.getElementsByTagName("edad").item(0).getTextContent());
                double nota = Double.parseDouble(alumno.getElementsByTagName("nota").item(0).getTextContent());

                // Creamos el objeto y lo añadimos a la lista
                Alumno nuevoAlumno = new Alumno(id, nombre, edad, nota);
                listaAlumnos.add(nuevoAlumno);
            }

            // Imprimimos los datos
            for (Alumno a : listaAlumnos) {
                System.out.println(a);
            }

        } catch (Exception e){
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}