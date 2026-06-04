DROP DATABASE IF EXISTS VetDaw;
CREATE DATABASE VetDaw;
USE VetDaw;


DROP TABLE IF EXISTS Mascota;
DROP TABLE IF EXISTS Propietario;
DROP TABLE IF EXISTS Tipo;
-- Luego ejecuta los CREATE TABLE

-- Tabla Proprietario
CREATE TABLE Propietario (
    dni VARCHAR(10) PRIMARY KEY,
    Nombre VARCHAR(45) NOT NULL,
    Apellido VARCHAR(45) NOT NULL,
    Telefono VARCHAR(12),
    Direccion VARCHAR(100),
    Email VARCHAR(45)
);

-- Tabla Típo (Tipo de mascota)
CREATE TABLE Tipo (
    idTipo INT AUTO_INCREMENT PRIMARY KEY,
    Tipo VARCHAR(45) NOT NULL
);

-- Tabla Mascota
CREATE TABLE Mascota (
    Pasaporte VARCHAR(9) PRIMARY KEY,
    Nombre VARCHAR(45) NOT NULL,
    Peso DOUBLE,
    FechaNacimiento DATETIME,
    Propietario_dni VARCHAR(10),
    Tipo_idTipo INT,
    FOREIGN KEY (Propietario_dni) REFERENCES Propietario(dni) ON DELETE SET NULL,
    FOREIGN KEY (Tipo_idTipo) REFERENCES Tipo(idTipo) ON DELETE SET NULL
);

-- Tabla Consulta
CREATE TABLE Consulta (
    idConsulta INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME NOT NULL,
    Duracion INT,
    Observaciones TEXT,
    Mascota_Pasaporte VARCHAR(9),
    Mascota_Propietario_dni VARCHAR(10),
    FOREIGN KEY (Mascota_Pasaporte) REFERENCES Mascota(Pasaporte) ON DELETE CASCADE,
    FOREIGN KEY (Mascota_Propietario_dni) REFERENCES Propietario(dni) ON DELETE CASCADE
);

-- =====================================================
-- INSERTS DE EJEMPLO
-- =====================================================

-- Insertar Proprietarios
INSERT INTO Propietario (dni, Nombre, Apellido, Telefono, Direccion, Email) VALUES
('12345678A', 'Juan', 'Pérez', '600123456', 'Calle Mayor 1, Madrid', 'juan.perez@email.com'),
('87654321B', 'María', 'García', '611654321', 'Avenida Paz 2, Barcelona', 'maria.garcia@email.com'),
('11223344C', 'Carlos', 'López', '622987654', 'Plaza Sol 3, Valencia', 'carlos.lopez@email.com'),
('44332211D', 'Ana', 'Martínez', '633123987', 'Calle Luna 4, Sevilla', 'ana.martinez@email.com');

-- Insertar Tipos de mascotas
INSERT INTO Tipo (Tipo) VALUES
('Perro'),
('Gato'),
('Conejo'),
('Hámster'),
('Loro');

-- Insertar Mascotas
INSERT INTO Mascota (Pasaporte, Nombre, Peso, FechaNacimiento, Propietario_dni, Tipo_idTipo) VALUES
('M12345678', 'Max', 8.5, '2020-05-10 00:00:00', '12345678A', 1),
('M87654321', 'Luna', 4.2, '2021-08-22 00:00:00', '87654321B', 2),
('M11223344', 'Rocky', 2.3, '2022-03-15 00:00:00', '11223344C', 3),
('M44332211', 'Bella', 6.7, '2019-11-30 00:00:00', '44332211D', 1),
('M55667788', 'Simba', 3.8, '2020-07-19 00:00:00', '12345678A', 2),
('M99887766', 'Thor', 3.1, '2023-01-25 00:00:00', '87654321B', 4);

-- Insertar Consultas
INSERT INTO Consulta (Fecha, Duracion, Observaciones, Mascota_Pasaporte, Mascota_Propietario_dni) VALUES
('2024-10-15 10:30:00', 30, 'Vacunación anual y revisión general. Mascota en buen estado.', 'M12345678', '12345678A'),
('2024-10-20 11:45:00', 45, 'Problema dental. Se recomienda limpieza.', 'M87654321', '87654321B'),
('2024-11-05 15:00:00', 20, 'Control de peso y corte de uñas.', 'M11223344', '11223344C'),
('2024-11-10 09:15:00', 60, 'Gastroenteritis. Medicación prescrita.', 'M44332211', '44332211D'),
('2024-11-18 16:30:00', 30, 'Revisión post-vacunación sin incidencias.', 'M12345678', '12345678A'),
('2024-12-01 12:00:00', 25, 'Extracción de pelo y limpieza de oídos.', 'M99887766', '87654321B'),
('2024-12-12 08:45:00', 40, 'Herida en pata. Sutura y antibiótico.', 'M55667788', '12345678A');

select * from Propietario;
select * from Mascota;
select * from Consulta;