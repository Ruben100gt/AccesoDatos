import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce una ruta a comprobar:");
        // 1. Crear la ruta a partir del texto introducido[cite: 1, 5]
        Path ruta = Path.of(scanner.nextLine());

        // 2. Comprobar si la ruta existe en el disco[cite: 1, 5]
        if (Files.exists(ruta)){
            System.out.println("La ruta existe.");

            // 3. Comprobar si es una carpeta[cite: 1, 5]
            if (Files.isDirectory(ruta)){
                System.out.println("Es un directorio. Contenido: ");

                // CLAVE: El try(...) cierra automáticamente el flujo al terminar
                try (var contenido = Files.list(ruta)) { //[cite: 5]
                        // Bucle rápido para imprimir cada elemento de la lista
                        contenido.forEach(System.out::println);
                } catch (IOException e){ // Capturar fallo de lectura[cite: 5]
                    System.out.println("Error al leer el directorio.");
                }
            } 
            // 4. Si no es carpeta, comprobar si es un archivo normal[cite: 5]
            else if (Files.isRegularFile(ruta)){
                System.out.println("Es un fichero de texto. Contenido: ");

                try {
                    // CLAVE: Lee todo el archivo de golpe y lo guarda en una lista[cite: 3, 5]
                    List<String> lineas = Files.readAllLines(ruta);
                    
                    // Bucle for-each clásico para imprimir línea a línea
                    for (String linea : lineas) {
                        System.out.println(linea);
                    }
                } catch (IOException e) { // Capturar fallo de lectura[cite: 5]
                    System.out.println("Error al leer el fichero.");
                }
            }
        } else {
            System.out.println("La ruta no es correcta.");
        }
    }
}