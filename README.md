# XPN Base App

Este proyecto es una aplicación desarrollada con Spring Boot. Emplea PostgreSQL como sistema gestor de base de datos y expone una API REST documentada mediante Swagger UI.

## Estructura del proyecto

src/

├── main/

│   ├── java/es/udc/fic/xpn/app/

│   │   ├── controller/  → Controladores REST (ProductController.).

│   │   ├── dao/   → Interfaces y clases DAO (acceso a datos).

│   │   ├── dto/   → Clase ProductDTO y RowMappers.

│   │   ├── mapper/  → Mappers para convertir la entidad Product a DTO y viceversa.

│   │   ├── model/ → Entidades del dominio (Product, Stock y Almacen).

│   │   ├── service/ → Interfaces y servicios para interconectar el Controlador con el Modelo.

│   │   └── Application.java   → Punto de entrada de la aplicación.

│   └── resources/

│       ├── application.yml → Configuración de Spring Boot.

│       └── create-schema.sql  → Script para inicializar la base de datos.

├── test/

│   └── java/es/udc/fic/xpn/app/

│       └── dao/ → Tests de los DAOs.

│       └── ApplicationTests.java → Test general de la aplicación.


**Otros archivos destacados**

- pom.xml → Archivo de configuración de Maven con todas las dependencias y plugins.
- logs/app.log → Archivo de log generado por la aplicación.
- README.md → Documentación del proyecto.
- ER_XPN_P2.svg → Diagrama del modelo entidad-relación de la base de datos.
- XPN_2024_25_C2_P2.pdf → Enunciado de la práctica.

## Requisitos previos

- Java 17 o superior
- Maven 3.6 o superior
- PostgreSQL en ejecución con la siguiente configuración:

  - Base de datos: `xpn`
  - Usuario: `xpn`
  - Contraseña: `xpn`
  - Puerto: 5432
- Puerto `8080` libre para poder la ejecutar la aplicación.
- El script SQL `create-schema.sql` debe existir en `src/main/resources` y estar correctamente configurado en el plugin Maven SQL.

## Configuración de la aplicación

La configuración se encuentra en el archivo:
`src/main/resources/application.yml`

Parámetros principales:

- URL de base de datos: jdbc:postgresql://localhost:5432/xpn
- Usuario: xpn
- Contraseña: xpn
- Puerto de servidor: 8080
- Ruta de Swagger UI: /swagger-ui.html
- Archivo de log: logs/app.log

## Instalación y ejecución

**1. Inicializar la base de datos**

Ejecutar el script SQL de creación del esquema:

```bash
mvn sql:execute
```

**2. Compilar el proyecto**

```bash
mvn clean install
```

**3. Ejecutar la aplicación**

Levantar la aplicación en http://localhost:8080 :

```bash
mvn spring-boot:run
```

**4. Opcional**

Acceder a la documentación de la API buscando en un navegador: http://localhost:8080/swagger-ui.html

Revisar los logs en el archivo: logs/app.log

## Dependencias principales

- PostgreSQL Driver
- Spring Boot Starter Web
- Spring Boot Starter JDBC
- Spring Boot Starter Validation
- SpringDoc OpenAPI (Swagger)
- TestNG (para pruebas)
