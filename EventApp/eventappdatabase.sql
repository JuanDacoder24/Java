-- ============================================
-- BASE DE DATOS: eventos_daw (Sin IDs Artificiales)
-- ============================================

DROP DATABASE IF EXISTS eventos_daw;
CREATE DATABASE eventos_daw;
USE eventos_daw;

-- Tabla tipo (El propio texto es la PK)
CREATE TABLE tipo (
    tipo VARCHAR(50) PRIMARY KEY
);

-- Tabla asistentes (El DNI es la PK)
CREATE TABLE asistente (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100) UNIQUE NOT NULL,
    tipo ENUM('asistente', 'organizador') NOT NULL
);

-- Tabla eventos (El codigo_interno es la PK)
CREATE TABLE evento (
    codigo_interno VARCHAR(10) PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    aforo_maximo INT NOT NULL,
    precio_entrada DECIMAL(10,2) NOT NULL,
    organizador_dni VARCHAR(9) NOT NULL,
    tipo_evento VARCHAR(50) NOT NULL,
    FOREIGN KEY (organizador_dni) REFERENCES asistente(dni) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (tipo_evento) REFERENCES tipo(tipo) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabla entradas (El numero_entrada es la PK)
CREATE TABLE entrada (
    numero_entrada VARCHAR(20) PRIMARY KEY,
    fecha_compra DATETIME NOT NULL,
    asistente_dni VARCHAR(9) NOT NULL,
    evento_codigo VARCHAR(7) NOT NULL,
    FOREIGN KEY (asistente_dni) REFERENCES asistente(dni) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (evento_codigo) REFERENCES evento(codigo_interno) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ============================================
-- INSERTS ADAPTADOS
-- ============================================

-- Insertar tipos de evento
INSERT INTO tipo (tipo) VALUES 
('Concierto'),
('Teatro'),
('Cine'),
('Feria'),
('Taller'),
('Conferencia'),
('Exposición'),
('Deporte'),
('Boda'),
('Gastronomía'),
('Infantil');

-- Insertar asistentes
INSERT INTO asistente (dni, nombre, apellidos, telefono, email, tipo) VALUES
('12345678A', 'Laura', 'García López', '600111222', 'laura.garcia@email.com', 'organizador'),
('87654321B', 'Carlos', 'Martín Ruiz', '600333444', 'carlos.martin@email.com', 'asistente'),
('11223344C', 'Ana', 'Pérez Sánchez', '600555666', 'ana.perez@email.com', 'asistente'),
('44556677D', 'Javier', 'Rodríguez Fernández', '611222333', 'javier.rodriguez@email.com', 'organizador'),
('99887766E', 'Marta', 'López Gómez', '622333444', 'marta.lopez@email.com', 'asistente'),
('55667788F', 'David', 'Sánchez Ruiz', '633444555', 'david.sanchez@email.com', 'asistente'),
('33445566G', 'Elena', 'Díaz Martín', '644555666', 'elena.diaz@email.com', 'organizador'),
('77665544H', 'Pablo', 'Torres Gil', '655666777', 'pablo.torres@email.com', 'asistente');

-- Insertar eventos (Asociando directamente por DNI del organizador y nombre del Tipo)
INSERT INTO evento (codigo_interno, titulo, fecha_hora, aforo_maximo, precio_entrada, organizador_dni, tipo_evento) VALUES
('CULT001', 'Noche de poesía', '2025-06-15 20:00:00', 50, 12.50, '12345678A', 'Teatro'),
('MUSI002', 'Concierto de jazz', '2025-07-20 21:30:00', 200, 25.00, '12345678A', 'Concierto'),
('TEAT003', 'Obra de teatro: La casa de Bernarda Alba', '2025-08-10 19:00:00', 120, 18.00, '44556677D', 'Teatro'),
('CINE004', 'Ciclo de cine independiente', '2025-09-05 18:30:00', 80, 8.50, '44556677D', 'Cine'),
('FERI005', 'Feria del libro antiguo', '2025-10-12 11:00:00', 300, 5.00, '33445566G', 'Feria'),
('DANZ006', 'Espectáculo de danza contemporánea', '2025-11-25 20:00:00', 100, 22.00, '33445566G', 'Teatro'),
('TALL007', 'Taller de escritura creativa', '2025-12-01 17:00:00', 25, 35.00, '12345678A', 'Taller'),
('CONF008', 'Conferencia: Inteligencia artificial y cultura', '2026-01-18 18:00:00', 150, 15.00, '44556677D', 'Conferencia');

-- Insertar entradas (Asociando por DNI de asistente y código del evento)
INSERT INTO entrada (numero_entrada, fecha_compra, asistente_dni, evento_codigo) VALUES
('ENT-0001', '2025-05-20 10:30:00', '87654321B', 'CULT001'),
('ENT-0002', '2025-05-21 15:45:00', '11223344C', 'CULT001'),
('ENT-0003', '2025-06-01 09:15:00', '99887766E', 'CULT001'),
('ENT-0004', '2025-07-01 12:00:00', '87654321B', 'MUSI002'),
('ENT-0005', '2025-07-05 18:20:00', '11223344C', 'MUSI002'),
('ENT-0006', '2025-07-10 20:30:00', '55667788F', 'MUSI002'),
('ENT-0007', '2025-07-12 11:45:00', '77665544H', 'MUSI002'),
('ENT-0008', '2025-07-25 16:00:00', '87654321B', 'TEAT003'),
('ENT-0009', '2025-08-01 14:15:00', '99887766E', 'TEAT003'),
('ENT-0010', '2025-08-05 10:00:00', '11223344C', 'CINE004'),
('ENT-0011', '2025-08-20 19:30:00', '55667788F', 'CINE004'),
('ENT-0012', '2025-09-25 08:45:00', '77665544H', 'FERI005'),
('ENT-0013', '2025-10-01 22:00:00', '87654321B', 'FERI005'),
('ENT-0014', '2025-11-01 13:20:00', '11223344C', 'DANZ006'),
('ENT-0015', '2025-11-15 17:50:00', '99887766E', 'DANZ006'),
('ENT-0016', '2025-11-20 09:10:00', '87654321B', 'TALL007'),
('ENT-0017', '2025-12-28 11:30:00', '55667788F', 'CONF008'),
('ENT-0018', '2026-01-10 15:40:00', '77665544H', 'CONF008');

select * from evento;
select * from entrada;
select * from asistente;