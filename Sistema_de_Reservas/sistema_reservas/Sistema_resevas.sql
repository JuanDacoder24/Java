DROP DATABASE IF EXISTS sistema_reservas;
CREATE DATABASE sistema_reservas;
USE sistema_reservas;


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE instalaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio_hora DECIMAL(6,2) NOT NULL,
    tipo ENUM('FUTBOL', 'PADEL', 'TENIS') NOT NULL
);

CREATE TABLE pista_futbol (
    id INT PRIMARY KEY,
    jugadores INT NOT NULL,
    FOREIGN KEY (id) REFERENCES instalaciones(id)
);

CREATE TABLE pista_padel (
    id INT PRIMARY KEY,
    cristal BOOLEAN NOT NULL,
    FOREIGN KEY (id) REFERENCES instalaciones(id)
);

CREATE TABLE pista_tenis (
    id INT PRIMARY KEY,
    superficie VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES instalaciones(id)
);

CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    instalacion_id INT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado ENUM('ACTIVA', 'CANCELADA') DEFAULT 'ACTIVA',

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (instalacion_id) REFERENCES instalaciones(id)
);

INSERT INTO usuarios (nombre, email, password) VALUES
('Admin Principal', 'admin@deportes.com', '$2y$10$YourHashedPasswordHere'),
('Juan Pérez', 'juan@email.com', '$2y$10$YourHashedPasswordHere'),
('María García', 'maria@email.com', '$2y$10$YourHashedPasswordHere'),
('Carlos López', 'carlos@email.com', '$2y$10$YourHashedPasswordHere'),
('Ana Martínez', 'ana@email.com', '$2y$10$YourHashedPasswordHere');

INSERT INTO instalaciones (nombre, precio_hora, tipo) VALUES
('Fútbol 11 - Principal', 80.00, 'FUTBOL'),
('Fútbol 7 - Secundaria', 50.00, 'FUTBOL'),
('Pista Padel 1', 25.00, 'PADEL'),
('Pista Padel 2', 25.00, 'PADEL'),
('Pista Tenis Tierra', 30.00, 'TENIS'),
('Pista Tenis Rápida', 30.00, 'TENIS');

INSERT INTO pista_futbol (id, jugadores) VALUES
(1, 22), 
(2, 14);  

INSERT INTO pista_padel (id, cristal) VALUES
(3, true),  
(4, false); 

INSERT INTO pista_tenis (id, superficie) VALUES
(5, 'Tierra batida'),
(6, 'Cemento');

INSERT INTO reservas (usuario_id, instalacion_id, fecha, hora_inicio, hora_fin, estado) VALUES
(2, 3, DATE_ADD(CURDATE(), INTERVAL 1 DAY), '18:00:00', '19:00:00', 'ACTIVA'),
(2, 3, DATE_ADD(CURDATE(), INTERVAL 1 DAY), '19:00:00', '20:00:00', 'ACTIVA'),
(3, 5, DATE_ADD(CURDATE(), INTERVAL 2 DAY), '16:00:00', '17:30:00', 'ACTIVA'),
(4, 1, DATE_ADD(CURDATE(), INTERVAL 3 DAY), '20:00:00', '21:00:00', 'ACTIVA'),
(5, 4, DATE_ADD(CURDATE(), INTERVAL 1 DAY), '17:00:00', '18:00:00', 'CANCELADA'),
(2, 2, DATE_ADD(CURDATE(), INTERVAL 4 DAY), '19:00:00', '20:00:00', 'ACTIVA');

select * from reservas;
select * from usuarios;
select * from instalaciones;