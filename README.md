# XPN Base App

La aplicación ha sido desarrollada como un sistema de Gestión de Almacén para la empresa de mueblería AEKI. Este sistema da soporte al proceso de recepción de mercancías.

- El proceso de negocio ha sido modelado y automatizado en la plataforma Bonita Community Edition 2024.3. Realiza peticiones a la API REST expuesta por el  Sistema de Planificación de Recursos (SPR) y al servidor SMTP para enviar notificaciones por correo electrónico.
- El SPR está desarrollado en Java con Spring Boot, utilizando PostgreSQL como sistema gestor de base de datos y expone una API REST documentada mediante Swagger UI.


## Estructura del proyecto

src/

├── main/

│   ├── java/es/udc/fic/xpn/app/

│   │   ├── controller/  -> Controladores REST (ProductController.).

│   │   ├── dao/   -> Interfaces y clases DAO (acceso a datos).

│   │   ├── dto/   -> Clase ProductDTO y RowMappers.

│   │   ├── mapper/  -> Mappers para convertir la entidad Product a DTO y viceversa.

│   │   ├── model/ -> Entidades del dominio (Product, Stock y Almacen).

│   │   ├── service/ -> Interfaces y servicios para interconectar el Controlador con el Modelo.

│   │   └── Application.java   -> Punto de entrada de la aplicación.

│   └── resources/

│       ├── application.yml -> Configuración de Spring Boot.

│       └── create-schema.sql  -> Script para inicializar la base de datos.

├── test/

│   └── java/es/udc/fic/xpn/app/

│       └── dao/ -> Tests de los DAOs.

│       └── ApplicationTests.java -> Test general de la aplicación.


**Otros archivos destacados**

- pom.xml -> Archivo de configuración de Maven con todas las dependencias y plugins.
- logs/app.log -> Archivo de log generado por la aplicación.
- README.md -> Documentación del proyecto.
- ER_XPN_P2.svg -> Diagrama del modelo entidad-relación de la base de datos.
- XPN_2024_25_C2_P2.pdf -> Enunciado de la práctica.
- XPN_P2_GrupoXX.bos -> Proyecto de Bonita.

## Requisitos previos

- Java 17 o superior
- Maven 3.6 o superior
- PostgreSQL en ejecución con la siguiente configuración:
  - Base de datos: `xpn`
  - Usuario: `xpn`
  - Contraseña: `xpn`
  - Puerto: 5432
- Puerto `8080` libre para poder la ejecutar la aplicación.
- Servidor de correo SMTP corriendo en el puerto `2550`.
- Disponer de Bonita Community Edition 2024.3

## Dependencias principales

- PostgreSQL Driver
- Spring Boot Starter Web
- Spring Boot Starter JDBC
- Spring Boot Starter Validation
- SpringDoc OpenAPI (Swagger)
- TestNG (para pruebas)

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

**4. Adicional**

Acceder a la documentación de la API poniendo en un navegador la siguiente URL http://localhost:8080/swagger-ui.html

Revisar los logs en el archivo: logs/app.log


## Itegración con Bonita

La aplicación Spring Boot está diseñada para ser consumida por procesos definidos en Bonita Community Edition 2024.3. Bonita se conecta a los endpoints REST expuestos por esta API para:

- Actualizar el stock de un producto en un almacen, creándolo si no existe.
- Consultar si el stock de un producto en un almacen supera el máximo stock establecido.
- Consultar si el stock de un producto en un almacen está por debajo el mínimo stock establecido.
- Obtener el destino más favorable para transferir un producto.
- Obtener un ID autogenerado para una órden de transferencia.
- Obtener un ID autogenerado para una órden de abastecimiento.
