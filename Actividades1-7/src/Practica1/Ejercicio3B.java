package Practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio3B {
    public static void main(String[] args) {
        
        Path fichero = Path.of("datos", "alumnos.txt");

        try (BufferedReader br = Files.newBufferedReader(fichero)) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                
                System.out.println("ID: " + datos[0]);
                System.out.println("Nombre: " + datos[1]);
                System.out.println("Edad: " + datos[2]);
                System.out.println("Nota: " + datos[3]);
                System.out.println("----------------");
            }
            
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}