DROP DATABASE IF EXISTS almacen;
CREATE DATABASE almacen;
USE almacen;

CREATE TABLE tipos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    referencia VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    tipo_id INT,
    cantidad INT NOT NULL DEFAULT 0,
    precio DECIMAL(10,2) NOT NULL,
    descuento INT DEFAULT 0,
    iva INT NOT NULL,
    aplicar_dto BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (tipo_id) REFERENCES tipos(id) ON DELETE SET NULL
);

-- INSERTAR TIPOS DE PRODUCTOS
INSERT INTO tipos (nombre) VALUES 
('Electrónicos'),
('Hogar'),
('Ropa'),
('Deportes'),
('Alimentación'),
('Oficina'),
('Juguetes');

-- VERIFICAR TIPOS (opcional)
SELECT * FROM tipos;

-- INSERTAR PRODUCTOS (con referencias únicas y datos variados)
INSERT INTO productos (referencia, nombre, descripcion, tipo_id, cantidad, precio, descuento, iva, aplicar_dto) VALUES 
-- Electrónicos (tipo_id = 1)
('REF001', 'Smartphone Galaxy S25', 'Teléfono móvil 6.8" 128GB RAM 8GB', 1, 25, 899.99, 10, 21, TRUE),
('REF002', 'Auriculares Wireless Pro', 'Auriculares TWS con cancelación de ruido', 1, 45, 129.99, 15, 21, FALSE),
('REF003', 'Tablet 11" Pro', 'Tablet con stylus y teclado incluido', 1, 12, 599.99, 0, 21, TRUE),

-- Hogar (tipo_id = 2)
('REF004', 'Aspiradora Robot X9', 'Aspiradora robótica con mapeo láser', 2, 8, 349.99, 20, 21, TRUE),
('REF005', 'Cafetera Expresso', 'Cafetera 20 bares con molinillo integrado', 2, 18, 199.99, 5, 21, FALSE),
('REF006', 'Lámpara LED Inteligente', 'Bombilla smart compatible Alexa/Google', 2, 150, 24.99, 25, 21, TRUE),

-- Ropa (tipo_id = 3)
('REF007', 'Camiseta Premium Cotton', 'Camiseta 100% algodón orgánico', 3, 75, 29.99, 10, 21, FALSE),
('REF008', 'Pantalón Chino Slim', 'Pantalón elegante para oficina', 3, 42, 49.99, 0, 21, TRUE),
('REF009', 'Zapatillas Running Air', 'Zapatillas deportivas con amortiguación', 3, 33, 89.99, 15, 21, FALSE),

-- Deportes (tipo_id = 4)
('REF010', 'Bicicleta MTB Carbon', 'Bicicleta montaña cuadro carbono 29"', 4, 5, 1299.99, 0, 21, TRUE),
('REF011', 'Mancuernas 15kg', 'Par de mancuernas ajustables', 4, 22, 89.99, 10, 21, FALSE),

-- Alimentación (tipo_id = 5)
('REF012', 'Aceite Oliva Virgen Extra', 'Botella 1L aceite premium', 5, 120, 9.99, 5, 10, FALSE),
('REF013', 'Café Molido Gourmet', 'Paquete 500g café 100% arábica', 5, 80, 7.99, 0, 10, TRUE),

-- Oficina (tipo_id = 6)
('REF014', 'Monitor 27" 4K', 'Monitor IPS 4K 144Hz', 6, 15, 399.99, 10, 21, TRUE),
('REF015', 'Silla Ergonómica Pro', 'Silla gaming/oficina ajustable', 6, 9, 249.99, 15, 21, FALSE),

-- Juguetes (tipo_id = 7)
('REF016', 'Lego City Policía', 'Set Lego 600 piezas estación policía', 7, 28, 79.99, 20, 21, TRUE),
('REF017', 'Dron Cámara 4K', 'Dron plegable con cámara estabilizada', 7, 11, 199.99, 10, 21, FALSE);

-- VERIFICAR DATOS (opcional)
SELECT 'PRODUCTOS' as Tabla, COUNT(*) as Total FROM productos
UNION ALL
SELECT 'TIPOS', COUNT(*) FROM tipos;

-- VER PRODUCTOS CON SUS TIPOS
SELECT p.referencia, p.nombre, t.nombre as tipo, p.cantidad, p.precio, p.descuento 
FROM productos p 
JOIN tipos t ON p.tipo_id = t.id 
ORDER BY p.tipo_id;