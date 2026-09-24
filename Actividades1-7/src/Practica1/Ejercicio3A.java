package Practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ejercicio3A {
    public static void main(String[] args) {
        
        Path fichero = Path.of("datos", "alumnos.txt");
        
        List<String> alumnos = List.of(
            "1;Ana García;20;8.5",
            "2;Luis Pérez;21;7.2",
            "3;Marta López;19;9.1",
            "4;Carlos Ruiz;22;6.8"
        );

        try {
            Files.write(fichero, alumnos);
            System.out.println("Fichero alumnos.txt generado correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}