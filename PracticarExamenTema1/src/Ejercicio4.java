import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio4 {
    public static void main(String[] args){
        
        try {
            // 1. Cargar el archivo físico. CLAVE: DOM usa File, no Path.[cite: 6]
            File archivoXML = new File("src/alumnos-exportados.xml");

            // 2. Preparar el lector XML y cargar el documento en memoria[cite: 6]
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivoXML);

            // 3. Buscar todas las etiquetas <alumno> y meterlas en una lista[cite: 6]
            NodeList alumnos = documento.getElementsByTagName("alumno");

            // 4. Preparar la ruta para el fichero TXT[cite: 1, 5]
            Path ficheroTxt = Path.of("src/resumen-alumnos.txt");

            // 5. Abrir el escritor de TXT con auto-cierre[cite: 4, 5]
            try (BufferedWriter bw = Files.newBufferedWriter(ficheroTxt)){
                
                // CLAVE: NodeList obliga a usar un bucle clásico con "i" y ".getLength()"[cite: 7]
                for (int i=0; i < alumnos.getLength(); i++) {
                    
                    // Extraer el nodo actual y convertirlo a Element[cite: 7]
                    Element alumno = (Element) alumnos.item(i);

                    // Buscar las sub-etiquetas <id> y <nombre>, coger la primera (.item(0)) y sacar su texto[cite: 7]
                    String id = alumno.getElementsByTagName("id").item(0).getTextContent();
                    String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();

                    // Escribir en el TXT montando el formato y saltando línea[cite: 4, 5]
                    bw.write(id + " - " + nombre);
                    bw.newLine();
                }
            } 

            System.out.println("Datos de XML en el TXT correctamente.");

        } catch (Exception e) { // Capturar errores de XML o del TXT interior
            System.out.println("Error procesando los archivos: " + e.getMessage());
        }
    }
}