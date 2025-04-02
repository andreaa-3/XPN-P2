DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256),
    description TEXT,
    price NUMERIC(2),
    stock INTEGER
);

INSERT INTO Product VALUES (10, 'Teclado', 'Teclado USB estándar', 20, 50);