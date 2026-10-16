package es.dam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio7 {
    public static void main(String[] args) {
        // Ruta de la base de datos
        String url = "jdbc:sqlite:Biblioteca.db"; 

        // Conexión
        try (Connection conexion = DriverManager.getConnection(url);
             Statement statement = conexion.createStatement()) {

            // Claves foráneas
            statement.executeUpdate("PRAGMA foreign_keys = ON;");

            // Tabla autor
            String sqlAutor = """
                CREATE TABLE IF NOT EXISTS autor (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    email TEXT UNIQUE
                )""";
            statement.executeUpdate(sqlAutor);

            // Tabla libro (enlazada a autor)
            String sqlLibro = """
                CREATE TABLE IF NOT EXISTS libro (
                    id INTEGER PRIMARY KEY,
                    titulo TEXT NOT NULL,
                    isbn TEXT UNIQUE,
                    precio REAL CHECK(precio >= 0),
                    stock INTEGER NOT NULL DEFAULT 0,
                    autor_id INTEGER,
                    FOREIGN KEY (autor_id) REFERENCES autor(id)
                )""";
            statement.executeUpdate(sqlLibro);

            // Tabla categoria
            String sqlCategoria = """
                CREATE TABLE IF NOT EXISTS categoria (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL UNIQUE,
                    descripcion TEXT
                )""";
            statement.executeUpdate(sqlCategoria);

        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
            e.printStackTrace();
        }
    }
}