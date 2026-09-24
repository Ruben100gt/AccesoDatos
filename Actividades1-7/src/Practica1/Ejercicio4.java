package Practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Introduce nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Introduce edad: ");
        String edad = scanner.nextLine();
        
        System.out.print("Introduce nota: ");
        String nota = scanner.nextLine();
        
        String nuevoAlumno = id + ";" + nombre + ";" + edad + ";" + nota;
        
        Path fichero = Path.of("datos", "alumnos.txt");
        
        try {
            Files.write(
                fichero,
                List.of(nuevoAlumno),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
            
            System.out.println("Alumno añadido correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}