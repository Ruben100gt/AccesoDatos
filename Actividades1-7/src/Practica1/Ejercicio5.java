package Practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio5 {
    public static void main(String[] args) {
        
        Path fichero = Path.of("datos", "alumnos.txt");

        int numAlumnos = 0;
        double sumaNotas = 0.0;
        double notaMaxima = 0.0;
        double notaMinima = 10.0; 
        int aprobados = 0;
        int suspensos = 0;

        try (BufferedReader br = Files.newBufferedReader(fichero)) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                
                double nota = Double.parseDouble(datos[3]);
                
                numAlumnos++;
                sumaNotas += nota;
                
                if (nota > notaMaxima) {
                    notaMaxima = nota;
                }
                if (nota < notaMinima) {
                    notaMinima = nota;
                }
                
                if (nota >= 5.0) {
                    aprobados++;
                } else {
                    suspensos++;
                }
            }
            
            double notaMedia = 0.0;
            if (numAlumnos > 0) {
                notaMedia = sumaNotas / numAlumnos;
            }

            System.out.println("Número de alumnos: " + numAlumnos);
            System.out.printf("Nota media: %.2f\n", notaMedia);
            System.out.println("Nota máxima: " + notaMaxima);
            System.out.println("Nota mínima: " + notaMinima);
            System.out.println("Aprobados: " + aprobados);
            System.out.println("Suspensos: " + suspensos);

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}