-- Insertar Usuarios
INSERT INTO Usuario (username, password) VALUES ('usuario1', 'password1');
INSERT INTO Usuario (username, password) VALUES ('usuario2', 'password2');

-- Insertar InventarioUsuario
INSERT INTO Inventario_Usuario (usuario_id) VALUES (1);
INSERT INTO Inventario_Usuario (usuario_id) VALUES (2);

-- Insertar Alimentos
INSERT INTO Alimento (nombre, fecha_caducidad, abierto, perecedero, inventario_usuario_id) VALUES ('Leche', '2025-01-01', false, true, 1);
INSERT INTO Alimento (nombre, fecha_caducidad, abierto, perecedero, inventario_usuario_id) VALUES ('Pan', '2024-12-20', true, true, 1);
INSERT INTO Alimento (nombre, fecha_caducidad, abierto, perecedero, inventario_usuario_id) VALUES ('Arroz', '2026-06-30', false, false, 2);
INSERT INTO Alimento (nombre, fecha_caducidad, abierto, perecedero, inventario_usuario_id) VALUES ('Manzanas', '2024-12-25', false, true, 2);

-- Insertar Ubicaciones
INSERT INTO Ubicacion (descripcion, capacidad, tipo_ubicacion) VALUES ('Alacena Principal', 200, 'ALACENA');
INSERT INTO Ubicacion (descripcion, capacidad, tipo_ubicacion) VALUES ('Nevera Principal', 100, 'NEVERA');
INSERT INTO Ubicacion (descripcion, capacidad, tipo_ubicacion) VALUES ('Congelador Secundario', 150, 'CONGELADOR');

-- Insertar Existencias
INSERT INTO Existencias (cantidad_alimento, fecha_entrada, alimento_id, ubicacion_id) VALUES (2, '2024-12-10', 1, 2); -- Leche en Nevera
INSERT INTO Existencias (cantidad_alimento, fecha_entrada, alimento_id, ubicacion_id) VALUES (1, '2024-12-09', 2, 1); -- Pan en Alacena
INSERT INTO Existencias (cantidad_alimento, fecha_entrada, alimento_id, ubicacion_id) VALUES (5, '2024-12-08', 3, 1); -- Arroz en Alacena
INSERT INTO Existencias (cantidad_alimento, fecha_entrada, alimento_id, ubicacion_id) VALUES (10, '2024-12-07', 4, 2); -- Manzanas en Nevera