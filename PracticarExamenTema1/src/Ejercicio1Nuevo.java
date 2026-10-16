import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1Nuevo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dime una ruta: ");
        Path ruta = Path.of(scanner.nextLine());

        if(Files.exists(ruta)) {
            

            if(Files.isDirectory(ruta)){
                System.out.println("La ruta es un directorio. Contenido:");

                try (var contenido = Files.newDirectoryStream(ruta)){
                    
                    contenido.forEach(System.out::println);
                    
                } catch (IOException e) {
                    System.out.println("No se ha podido leer el directorio.");
                }
            } else if (Files.isRegularFile(ruta)) {
                
                try {
                    List<String> lineas = Files.readAllLines(ruta);

                    for (String linea : lineas ){
                        System.out.println(linea);
                    }

                    
                } catch (IOException e) {
                    System.out.println("Error al leer el fichero.");
                }




            }

        } else {
            System.out.println("La ruta no existe.");
        }





        
    }
}
