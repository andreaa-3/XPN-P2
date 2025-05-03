-- Eliminar tablas en orden correcto (Stock depende de Almacen y Product)
DROP TABLE IF EXISTS Stock;
DROP TABLE IF EXISTS Almacen;
DROP TABLE IF EXISTS Product;

-- Crear tabla Product
CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    sku VARCHAR(256),
    name VARCHAR(256),
    tipo VARCHAR(256),
    proveedor VARCHAR(256)
);

-- Crear tabla Almacen
CREATE TABLE Almacen (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(256) NOT NULL
);

-- Crear tabla Stock
CREATE TABLE Stock (
    id SERIAL PRIMARY KEY,
    stock NUMERIC(32),
    maxStock NUMERIC(32),
    minStock NUMERIC(32),
    idAlmacen BIGINT NOT NULL,
    idProducto BIGINT NOT NULL,
    CONSTRAINT fk_stock_almacen FOREIGN KEY (idAlmacen) REFERENCES Almacen(id),
    CONSTRAINT fk_stock_producto FOREIGN KEY (idProducto) REFERENCES Product(id),
    CONSTRAINT uq_stock_producto_almacen UNIQUE (idProducto, idAlmacen)
);

--INSERT INTO Product VALUES (1000, '1234', 'Prueba', 'TipoPrueba', 'AlmacenPrueba', 'ProveedorPrueba', 50, 2, 1);