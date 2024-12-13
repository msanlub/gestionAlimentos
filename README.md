### API Modelo de Negocio: Sistema de Gestión de Alimentos

   Esta API proporciona un sistema completo para la gestión de alimentos, permitiendo a los usuarios mantener un inventario detallado de sus alimentos, controlar existencias en diferentes ubicaciones, y gestionar la información de caducidad y estado de los productos.

## Entidades Principales
### Alimento

   Representa un tipo de alimento en el sistema.

   Atributos: id, nombre, fechaCaducidad, abierto, perecedero

   Relaciones:

   - One-to-Many con Existencias

   - Many-to-One con InventarioUsuario

### Existencias
Representa la cantidad de un alimento específico en una ubicación determinada.

Atributos: id, cantidad_alimento, fechaEntrada

Relaciones:

- Many-to-One con Alimento
- Many-to-One con Ubicacion
### Ubicacion
Representa el lugar donde se almacenan los alimentos.

Atributos: id, descripcion, capacidad, tipoUbicacion (enum)

Relaciones:

- One-to-Many con Existencias
### InventarioUsuario
 Representa el inventario general de un usuario.

Atributos: id

Relaciones:
      - One-to-One con Usuario
      - One-to-Many con Alimento
### Usuario
Representa un usuario del sistema.

Atributos: id, username, password

## Funcionalidades Principales
### Alimento
   Gestiona operaciones CRUD para Alimentos.

   Funcionalidades adicionales:
   - Búsqueda por nombre
   - Filtrado por existencias, perecedero, abierto
   - Ordenación por fecha de caducidad

### Existencias
Maneja las operaciones CRUD para Existencias.

   Funcionalidades adicionales:
   - Búsqueda por ID de Alimento
   - Búsqueda por ID de Ubicación
   - Ordenación por fecha de entrada
### Ubicacion
   Gestiona operaciones CRUD para Ubicaciones.

   Funcionalidades adicionales:
   - Búsqueda por tipo de ubicación
### InventarioUsuario
   Maneja la creación y gestión de inventarios de usuarios.

   Funcionalidades:
   - Creación de inventario para un usuario
   - Obtención de inventario por ID de usuario
### Usuario
   Gestiona operaciones CRUD para Usuarios.

   - Funcionalidades adicionales:
   - Búsqueda por nombre de usuario
   - 
## Flujo de Trabajo 
   Registro de usuario 

   Creación de inventario para el usuario 

   Adición de alimentos al inventario 

   Registro de existencias de alimentos en ubicaciones específicas 

   Gestión y actualización de alimentos y existencias

   Consultas y búsquedas de alimentos y existencias

## Endpoints Principales
### Alimentos
   GET /api/alimentos - Listar todos los alimentos (paginado)

   POST /api/alimentos - Crear un nuevo alimento

   GET /api/alimentos/{id} - Obtener un alimento específico

   PUT /api/alimentos/{id} - Actualizar un alimento

   DELETE /api/alimentos/{id} - Eliminar un alimento

   GET /api/alimentos/search?nombre={nombre} - Buscar alimentos por nombre

   GET /api/alimentos/perecederos?perecedero={true/false} - Filtrar alimentos por perecedero
### Existencias
   GET /api/existencias - Listar todas las existencias (paginado)

   POST /api/existencias - Registrar nuevas existencias

   GET /api/existencias/{id} - Obtener existencias específicas

   PUT /api/existencias/{id} - Actualizar existencias

   DELETE /api/existencias/{id} - Eliminar existencias

   GET /api/existencias/alimento/{alimentoId} - Listar existencias por alimento

### Ubicaciones
   GET /api/ubicaciones - Listar todas las ubicaciones (paginado)

   POST /api/ubicaciones - Crear una nueva ubicación

   GET /api/ubicaciones/{id} - Obtener una ubicación específica

   PUT /api/ubicaciones/{id} - Actualizar una ubicación

   DELETE /api/ubicaciones/{id} - Eliminar una ubicación

   GET /api/ubicaciones/tipo/{tipoUbicacion} - Listar ubicaciones por tipo

### Usuarios e Inventarios
   POST /api/usuarios - Registrar un nuevo usuario

   GET /api/inventarios/usuario/{usuarioId} - Obtener el inventario de un usuario


  
