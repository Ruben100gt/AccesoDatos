-- Ejercicio 6: Base de datos de biblioteca

-- reamos primero la tabla autor porque no depende de nadie
CREATE TABLE autor (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    email TEXT UNIQUE
);

-- Creamos la tabla libro después que la tabla autor, ya que depende de esta
CREATE TABLE libro (
    id INTEGER PRIMARY KEY,
    titulo TEXT NOT NULL,
    isbn TEXT UNIQUE,
    precio REAL CHECK(precio >= 0),
    stock INTEGER NOT NULL DEFAULT 0,
    autor_id INTEGER,
    FOREIGN KEY (autor_id) REFERENCES autor(id)
);