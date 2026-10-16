-- Ejercicio 3: Crear tabla

CREATE TABLE alumno (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellidos TEXT,
    email TEXT UNIQUE,
    edad INTEGER CHECK(edad >= 18)
);