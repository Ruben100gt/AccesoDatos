
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio2Nuevo {
    public static void main(String[] args){
        Path origen = Path.of("src/datos.txt");
        Path destino = Path.of("src/resumen-alumnos.txt");

        if(Files.exists(origen)){

            try (BufferedReader br = Files.newBufferedReader(origen) ; BufferedWriter bw = Files.newBufferedWriter(destino)){
                String linea;

                while((linea = br.readLine()) != null){
                    bw.write(linea);
                    bw.newLine();
                }

                System.out.println("Se ha copiado correctamente.");


            } catch (IOException e){
                System.out.println("Error al copiar los ficheros." + e.getMessage());
            }



        } else {
            System.out.println("No existe el archivo a copiar.");
        }



    }
}
