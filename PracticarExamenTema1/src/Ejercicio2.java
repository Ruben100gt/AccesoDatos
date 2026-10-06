import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio2 {
    public static void main(String[] args){
        // 1. Definir rutas de origen y destino[cite: 1, 5]
        Path origen = Path.of("src/datos.txt");
        Path destino = Path.of("src/copia-datos.txt");

        // 2. Comprobar siempre que el origen existe antes de intentar leerlo[cite: 1, 5]
        if (Files.exists(origen)){
            
            // CLAVE: Abrimos Lector y Escritor en el mismo try(...) separados por PUNTO Y COMA[cite: 4, 5]
            try (BufferedReader br = Files.newBufferedReader(origen) ; 
                 BufferedWriter bw = Files.newBufferedWriter(destino)){
                
                // Variable temporal para guardar el texto de cada pasada
                String linea;

                // CLAVE: Bucle que lee hasta que no quedan más líneas (devuelve null)[cite: 4, 5]
                while((linea = br.readLine()) != null) {
                    bw.write(linea);  // Escribe el texto[cite: 4, 5]
                    bw.newLine();     // IMPORTANTE: Hace el "Intro" para que no se pegue todo[cite: 4, 5]
                }

                System.out.println("Fichero copiado línea a línea correctamente.");

            } catch (IOException e){ // Capturar fallo de lectura/escritura[cite: 5]
                System.out.println("Error al leer o escribir el fichero: " + e.getMessage());
            }
        } else{
            System.out.println("El fichero de origen no existe.");
        }
    }
}