package Practica1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile("datos/alumnos.dat", "rw")) {
            raf.writeInt(1); raf.writeInt(20); raf.writeDouble(8.5);
            raf.writeInt(2); raf.writeInt(21); raf.writeDouble(7.2);
            raf.writeInt(3); raf.writeInt(19); raf.writeDouble(3.6);
            
            System.out.print("Introduce el número de alumno (1 al 3) que quieres consultar: ");
            int numAlumno = scanner.nextInt();
            
            long posicion = (numAlumno - 1) * 16;
            
            if (posicion >= 0 && posicion < raf.length()) {
                raf.seek(posicion);
                
                int id = raf.readInt();
                int edad = raf.readInt();
                double nota = raf.readDouble();
                
                System.out.println("ID: " + id);
                System.out.println("Edad: " + edad);
                System.out.println("Nota: " + nota);
                
            } else {
                System.out.println("Ese número de alumno no existe en el fichero.");
            }
            
        } catch (IOException e) {
            System.out.println("Error en el acceso al fichero: " + e.getMessage());
        }
    }
}