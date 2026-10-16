package es.dam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Reto {
    public static void main(String[] args) {
        // Ruta de la base de datos
        String url = "jdbc:sqlite:Biblioteca.db";

        // Conexión
        try (Connection conexion = DriverManager.getConnection(url);
             Statement statement = conexion.createStatement()) {

            // Añadimos nuevas columnas
            try {
                statement.executeUpdate("ALTER TABLE libro ADD COLUMN editorial TEXT DEFAULT 'Desconocida';");
                statement.executeUpdate("ALTER TABLE libro ADD COLUMN año_publicacion INTEGER;");
            } catch (SQLException e) {
                System.out.println("Las columnas ya existen o hubo un error al añadirlas");
            }

            // Comprobamos columnas
            statement.executeQuery("SELECT editorial, año_publicacion FROM libro;");
            System.out.println("Comprobación exitosa: Las columnas existen");

        } catch (SQLException e) {
            System.out.println("Error general en la base de datos");
            e.printStackTrace();
        }
    }
}