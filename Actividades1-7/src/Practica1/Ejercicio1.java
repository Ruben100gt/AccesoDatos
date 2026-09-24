package Practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca una ruta:");
        Path ruta = Path.of(scanner.nextLine());

        if (Files.exists(ruta)) {
            System.out.println("La ruta existe.");

            if (Files.isDirectory(ruta)) {
                System.out.println("Es un directorio.");
                System.out.println("Contenido:");
                
                try (var contenido = Files.list(ruta)) {
                    contenido.forEach(System.out::println);
                    
                } catch (IOException e) {
                    System.out.println("Error al leer el directorio: " + e.getMessage());
                }

            } else if (Files.isRegularFile(ruta)) {
                System.out.println("Es un fichero.");
            }

        } else {
            System.out.println("La ruta no existe.");
        }
    }
}