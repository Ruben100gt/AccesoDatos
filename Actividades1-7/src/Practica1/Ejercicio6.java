package Practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class Ejercicio6 {
    public static void main(String[] args) {
        
        Path fichero = Path.of("datos", "alumnos.txt");
        Path copias = Path.of("copias");
        
        if (Files.exists(fichero)) {
            try {
                if (!Files.exists(copias)) {
                    Files.createDirectory(copias);
                }
                
                String fecha = LocalDate.now().toString();
                Path ficheroCopia = Path.of("copias", "alumnos_" + fecha + ".txt");
                
                Files.copy(fichero, ficheroCopia, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia creada correctamente.");
                
            } catch (IOException e) {
                System.out.println("Error al copiar: " + e.getMessage());
            }
            
        } else {
            System.out.println("El fichero alumnos.txt no existe.");
        }
    }
}