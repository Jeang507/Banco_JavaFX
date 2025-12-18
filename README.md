# Banco Virtual – Proyecto JavaFX

Proyecto académico desarrollado en Java con JavaFX que simula el funcionamiento básico de un sistema bancario.  
Permite a los usuarios registrarse, iniciar sesión y realizar operaciones como consulta de saldo, depósitos y retiros.

## Objetivo

Aplicar conceptos de programación orientada a objetos, JavaFX y conexión a bases de datos en una aplicación funcional.

## Funcionalidades

- Registro de usuarios  
- Inicio de sesión con usuario y PIN  
- Consulta de saldo  
- Depósitos  
- Retiros  
- Edición de perfil  
- Cierre de sesión  

## Estructura del proyecto

El código fuente se encuentra en `src/banco` y está organizado por capas:

- `controllers`: controladores JavaFX
- `dao`: acceso a datos mediante JDBC
- `models`: modelos del dominio
- `views`: vistas JavaFX (FXML y vistas en Java)
- `styles`: estilos CSS

El punto de entrada de la aplicación es `Banco.java`.

El proyecto incluye además una carpeta `lib` para dependencias externas y scripts (`run.sh` y `run.bat`) para ejecutar la aplicación sin IDE.

## Tecnologías utilizadas

- Java  
- JavaFX  
- FXML  
- CSS  
- PostgreSQL  
- JDBC  

## Arquitectura

El proyecto sigue el patrón **MVC (Modelo–Vista–Controlador)**:

- **Modelos**: representan las entidades del sistema  
- **Vistas**: interfaz gráfica con JavaFX (FXML y vistas en Java)  
- **Controladores**: gestionan la lógica de la interfaz  
- **DAO**: acceso a datos mediante JDBC  

Esta separación facilita el mantenimiento y la extensión del proyecto.

## Base de datos

La aplicación utiliza PostgreSQL para almacenar la información de usuarios y transacciones.  
La conexión se maneja mediante JDBC.

## Ejecución del proyecto

El repositorio incluye scripts para compilar y ejecutar la aplicación sin necesidad de un IDE.

### Linux / macOS

chmod +x run.sh
./run.sh
run.bat

## Requisitos

- JDK 21

- JavaFX configurado en el sistema

- PostgreSQL

- Driver JDBC de PostgreSQL en la carpeta banco/lib

## Autores

Proyecto desarrollado en colaboración por:

- Jean Guillèn – Líder del proyecto

- Jerameel Morris

- Kaelyn Crosthwaite

- Juan Hoyos
