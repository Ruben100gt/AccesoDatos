import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GestorAlumnos {

    // Definimos las rutas principales que vamos a usar en todo el programa
    private static final Path RUTA_DATOS = Path.of("datos");
    private static final Path FICHERO_TXT = Path.of("datos", "alumnos.txt");
    private static final Path FICHERO_XML = Path.of("datos", "alumnos.xml");
    private static final Path RUTA_COPIAS = Path.of("copias");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Comprobamos y creamos las carpetas necesarias al iniciar
        prepararDirectorios();

        do {
            // Imprimimos las opciones
            System.out.println("\n=================");
            System.out.println("GESTOR DE ALUMNOS");
            System.out.println("=================");
            System.out.println("1. Añadir alumno");
            System.out.println("2. Mostrar alumnos");
            System.out.println("3. Buscar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Exportar a XML");
            System.out.println("6. Importar desde XML");
            System.out.println("7. Crear copia de seguridad");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");

            try {
                // Leemos como texto y pasamos a número para evitar errores de saltos de línea
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        anadirAlumno(scanner);
                        break;
                    case 2:
                        mostrarAlumnos();
                        break;
                    case 3:
                        buscarAlumno(scanner);
                        break;
                    case 4:
                        eliminarAlumno(scanner);
                        break;
                    case 5:
                        exportarXML();
                        break;
                    case 6:
                        importarXML();
                        break;
                    case 7:
                        crearCopiaSeguridad();
                        break;
                    case 8:
                        System.out.println("Saliendo del gestor...");
                        break;
                    default:
                        System.out.println("Opción no válida. Introduce un número del 1 al 8.");
                }
            } catch (NumberFormatException e) {
                // Comprobamos errores de entrada si el usuario teclea letras en vez de números
                System.out.println("Error: Debes introducir un número válido.");
            }

        } while (opcion != 8);

        scanner.close();
    }


    // Métodos
    
    private static void prepararDirectorios() {
        try {
            // Creamos las carpetas si no existen
            if (!Files.exists(RUTA_DATOS)) {
                Files.createDirectory(RUTA_DATOS);
            }
            if (!Files.exists(RUTA_COPIAS)) {
                Files.createDirectory(RUTA_COPIAS);
            }
        } catch (IOException e) {
            System.out.println("Error al crear las carpetas: " + e.getMessage());
        }
    }

    private static void anadirAlumno(Scanner scanner) {
        try {
            System.out.print("Introduce ID: ");
            String id = scanner.nextLine();
            System.out.print("Introduce nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Introduce apellidos: ");
            String apellidos = scanner.nextLine();
            System.out.print("Introduce edad: ");
            String edad = scanner.nextLine();
            
            // Comprobación de errores para la nota
            String nota = "";
            boolean notaValida = false;
            
            while (!notaValida) {
                System.out.print("Introduce nota (0 - 10): ");
                nota = scanner.nextLine();
                
                try {
                    // Intentamos convertir el texto a número decimal
                    double notaNumerica = Double.parseDouble(nota);
                    
                    // Comprobamos la nota
                    if (notaNumerica >= 0 && notaNumerica <= 10) {
                        notaValida = true;
                    } else {
                        System.out.println("Error: La nota debe estar entre 0 y 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debes introducir un número válido (ejemplo: 7.5).");
                }
            }

            // Preparamos la línea con el formato separado por punto y coma
            String linea = id + ";" + nombre + ";" + apellidos + ";" + edad + ";" + nota;

            // Añadimos la línea al fichero de texto
            Files.write(FICHERO_TXT, List.of(linea), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Alumno añadido correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de entrada de datos.");
        }
    }

    private static void mostrarAlumnos() {
        if (!Files.exists(FICHERO_TXT)) {
            System.out.println("Aún no hay alumnos guardados.");
            return;
        }

        try (BufferedReader br = Files.newBufferedReader(FICHERO_TXT)) {
            String linea;
            System.out.println("\n--- Lista de Alumnos ---");
            
            // Recorremos las líneas del fichero
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                System.out.println("ID: " + datos[0] + " | Nombre: " + datos[1] + " " + datos[2] + " | Edad: " + datos[3] + " | Nota: " + datos[4]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void buscarAlumno(Scanner scanner) {
        if (!Files.exists(FICHERO_TXT)) {
            System.out.println("No hay datos para buscar.");
            return;
        }

        System.out.print("Introduce el ID del alumno a buscar: ");
        String idBuscado = scanner.nextLine();
        boolean encontrado = false;

        try (BufferedReader br = Files.newBufferedReader(FICHERO_TXT)) {
            String linea;
            
            // Recorremos el fichero buscando el ID
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(idBuscado)) {
                    System.out.println("\nAlumno encontrado:");
                    System.out.println("ID: " + datos[0]);
                    System.out.println("Nombre: " + datos[1]);
                    System.out.println("Apellidos: " + datos[2]);
                    System.out.println("Edad: " + datos[3]);
                    System.out.println("Nota: " + datos[4]);
                    encontrado = true;
                    break; // Salimos del bucle si ya lo hemos encontrado
                }
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado ningún alumno con el ID: " + idBuscado);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void eliminarAlumno(Scanner scanner) {
        if (!Files.exists(FICHERO_TXT)) {
            System.out.println("No hay alumnos para eliminar.");
            return;
        }

        System.out.print("Introduce el ID del alumno a eliminar: ");
        String idEliminar = scanner.nextLine();
        boolean eliminado = false;
        
        try {
            // Leemos todas las líneas
            List<String> lineas = Files.readAllLines(FICHERO_TXT);
            List<String> nuevasLineas = new ArrayList<>();

            // Recorremos y guardamos solo los que NO sean el ID que queremos eliminar
            for (String linea : lineas) {
                String[] datos = linea.split(";");
                if (!datos[0].equals(idEliminar)) {
                    nuevasLineas.add(linea);
                } else {
                    eliminado = true;
                }
            }

            // Sobrescribimos el fichero con la nueva lista
            Files.write(FICHERO_TXT, nuevasLineas);

            if (eliminado) {
                System.out.println("Alumno eliminado correctamente.");
            } else {
                System.out.println("No se ha encontrado el ID, no se ha borrado nada.");
            }

        } catch (IOException e) {
            System.out.println("Error al intentar eliminar: " + e.getMessage());
        }
    }

    private static void exportarXML() {
        if (!Files.exists(FICHERO_TXT)) {
            System.out.println("No hay datos en texto para exportar.");
            return;
        }

        try {
            // Creamos un documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            // Creamos la etiqueta raíz <alumnos>
            Element raiz = documento.createElement("alumnos");
            documento.appendChild(raiz);

            // Leemos el fichero de texto
            List<String> lineas = Files.readAllLines(FICHERO_TXT);

            // Recorremos los alumnos del texto
            for (String linea : lineas) {
                String[] datos = linea.split(";");

                Element alumno = documento.createElement("alumno");

                // Creamos las sub-etiquetas con la información
                Element id = documento.createElement("id");
                id.setTextContent(datos[0]);
                alumno.appendChild(id);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(datos[1]);
                alumno.appendChild(nombre);

                Element apellidos = documento.createElement("apellidos");
                apellidos.setTextContent(datos[2]);
                alumno.appendChild(apellidos);

                Element edad = documento.createElement("edad");
                edad.setTextContent(datos[3]);
                alumno.appendChild(edad);

                Element nota = documento.createElement("nota");
                nota.setTextContent(datos[4]);
                alumno.appendChild(nota);

                // Añadimos el alumno a la raíz
                raiz.appendChild(alumno);
            }

            // Generamos el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(FICHERO_XML.toString()));

            // Guardamos el documento
            transformer.transform(source, result);
            System.out.println("Fichero XML exportado correctamente en: " + FICHERO_XML);

        } catch (Exception e) {
            System.out.println("Error al generar el XML: " + e.getMessage());
        }
    }

    private static void importarXML() {
        if (!Files.exists(FICHERO_XML)) {
            System.out.println("El fichero XML no existe. No se puede importar.");
            return;
        }

        try {
            // Cargamos el archivo XML
            File archivo = new File(FICHERO_XML.toString());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            // Obtenemos la lista de todas las etiquetas <alumno>
            NodeList alumnosNodeList = documento.getElementsByTagName("alumno");
            List<String> alumnosTexto = new ArrayList<>();

            // Recorremos los alumnos del XML
            for (int i = 0; i < alumnosNodeList.getLength(); i++) {
                Element alumno = (Element) alumnosNodeList.item(i);

                String id = alumno.getElementsByTagName("id").item(0).getTextContent();
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
                String apellidos = alumno.getElementsByTagName("apellidos").item(0).getTextContent();
                String edad = alumno.getElementsByTagName("edad").item(0).getTextContent();
                String nota = alumno.getElementsByTagName("nota").item(0).getTextContent();

                // Montamos la línea formato txt
                alumnosTexto.add(id + ";" + nombre + ";" + apellidos + ";" + edad + ";" + nota);
            }

            // Sobrescribimos el archivo txt con los datos del XML
            Files.write(FICHERO_TXT, alumnosTexto);
            System.out.println("Datos importados del XML correctamente.");

        } catch (Exception e) {
            System.out.println("Error al leer el fichero XML: " + e.getMessage());
        }
    }

    private static void crearCopiaSeguridad() {
        if (!Files.exists(FICHERO_TXT)) {
            System.out.println("No hay fichero de alumnos para hacer copia.");
            return;
        }

        try {
            String fecha = LocalDate.now().toString();
            // Generamos el nombre con la fecha (ej: alumnos_2026-10-02.txt)
            Path ficheroCopia = Path.of(RUTA_COPIAS.toString(), "alumnos_" + fecha + ".txt");

            // Copiamos el archivo
            Files.copy(FICHERO_TXT, ficheroCopia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copia de seguridad creada correctamente en: " + ficheroCopia);

        } catch (IOException e) {
            System.out.println("Error al realizar la copia de seguridad: " + e.getMessage());
        }
    }
}