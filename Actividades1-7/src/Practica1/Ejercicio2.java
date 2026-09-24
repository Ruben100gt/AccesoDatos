package Practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ejercicio2 {
    public static void main(String[] args) {
        
        List<Path> rutas = List.of(
            Path.of("DAM"),
            Path.of("DAM", "documentos"),
            Path.of("DAM", "imagenes"),
            Path.of("DAM", "datos"),
            Path.of("DAM", "copias")
        );

        try {
            for (Path ruta : rutas) {
                if (!Files.exists(ruta)) {
                    Files.createDirectory(ruta);
                }
            }
            System.out.println("Estructura creada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al crear los directorios: " + e.getMessage());
        }
    }
}