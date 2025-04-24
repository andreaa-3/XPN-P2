DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
                        id SERIAL PRIMARY KEY,
                        sku VARCHAR(256),
                        name VARCHAR(256),
                        tipo VARCHAR(256),
                        almacen VARCHAR(256),
                        proveedor VARCHAR(256),
                        stock NUMERIC(32),
                        maxStock NUMERIC(32),
                        minStock NUMERIC(32)
);

--INSERT INTO Product VALUES (1000, '1234', 'Prueba', 'TipoPrueba', 'AlmacenPrueba', 'ProveedorPrueba', 50, 2, 1);