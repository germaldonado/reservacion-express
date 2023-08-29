# Nombre del Proyecto 📋

"**Reservación Express:** es un sistema de reservas hoteleras que permite a los usuarios reservar habitaciones de hotel de forma rápida y sencilla. El sistema está diseñado para ser fácil de usar, incluso para usuarios que no tienen experiencia en reservas de hotel. 😀

<div style="text-align: center; padding: 10px; display:flex flex-direction:column">
    <h1 style="font-size:25px; text-decoration-line: underline;">Version Escritorio 💻</h1>
    <div style="display:flex; flex-wrap: wrap; gap:5px; justify-content: center;">
    <img src="/src/imagenes/login1.png" width="300px">
    <img src="/src/imagenes/MenuInicio.png" width="300px">
    <img src="/src/imagenes/reservas1.png" width="300px">
    <img src="/src/imagenes/huesped.png" width="300px">
    <img src="/src/imagenes/busqueda1.png" width="300px">
    </div>    
</div>

# Link Proyecto

<div style="display: flex; flex-direction: column; align-items: center;">
    <img src="/src/imagenes/express.png" width="50px">
    <a style="color: blue; font-size: 20px; display: block; text-align: center;" href="https://github.com/germaldonado/reservacion-express" target="_blank">Reservación Express</a>
</div>

## Instalación ⚙️

1. Enfoque del proyecto: Este proyecto está enfocado al **backend**,el frontend se tomó como referencia clonando el siguiente proyecto:

```
    git clone https://github.com/alura-challenges/challenge-one-alura-hotel-latam.git
```

2. Requisitos previos: Un entorno de desarrollo con **Java 8 o superior** instalado, un servidor de base de datos **MySQL**.

La base de datos se compone de las siguientes tablas:

- **Usuarios:** Esta tabla almacena información sobre los usuarios del sistema, como el nombre de usuario, y la contraseña.
- **Reservas:** Esta tabla almacena información sobre las reservas de habitaciones, como la fecha de entrada, la fecha de salida, valor y forma de pago.
- **Huespedes:** Esta tabla almacena información sobre los huéspedes, como el nombre, apellido, fecha de nacimiento, nacionalidad y el número de teléfono.

La base de datos **se crea automáticamente al ejecutar el proyecto**, no es necesario crear la base de datos manualmente. Para ello, el proyecto incluye una carpeta llamada **db** que contiene los archivos de consulta SQL necesarios para crear la base de datos. Cuando se ejecuta el proyecto, estas consultas se ejecutan automáticamente y crean la base de datos.

El esquema de la base de datos se muestra en la siguiente imagen:

<div style="display: flex; flex-direction: column; align-items: center;">
    <img src="/src/imagenes/sql.png" width="400px">
</div>

La imagen muestra las tablas y columnas de la base de datos. Las columnas en amarillo son claves primarias.

**tener en cuenta:** que debe ser veridicta la información de su servidor de base de datos para que no genere errores

3. Clona el proyecto: Para descargar el proyecto, puede utilizar el siguiente comando:

```
    https://github.com/germaldonado/reservacion-express.git

```

4. Importar el proyecto en el IDE de **IntelliJ IDEA**: para importar el proyecto en IntelliJ IDEA, seleccione la opción File > New > Project from Existing Sources.

5. Configura las librerias: en el menú File, seleccione la opción Project Structure,en la pestaña Libraries, haga clic en el botón **Add JARs** or directories, navegue hasta la carpeta donde se encuentran las bibliotecas y seleccione los archivos JAR, haga clic en el botón Open y encontrará una sección que debera incluir las siguientes librerias:

- ![JAR](https://img.shields.io/badge/C3P0-JAR-red) <a href="https://mvnrepository.com/artifact/com.mchange/c3p0">C3P0</a>
- ![JAR](https://img.shields.io/badge/JCalendar-JAR-blue) <a href="https://mvnrepository.com/artifact/com.toedter/jcalendar">JCalendar</a>
- ![JAR](https://img.shields.io/badge/MchangeCommonsJava-JAR-orange) <a href="https://mvnrepository.com/artifact/com.mchange/mchange-commons-java">Mchange Commons Java</a>
- ![JAR](https://img.shields.io/badge/Dotenv-JAR-green) <a href="https://mavenlibs.com/jar/file/io.github.cdimascio/dotenv-java">Dotenv</a>
- ![JAR](https://img.shields.io/badge/MySQLConnectorJ-JAR-white) <a href="https://mvnrepository.com/artifact/com.mysql/mysql-connector-j">MySQL Connector/J</a>
- ![JAR](https://img.shields.io/badge/ProtocolBuffersCore-JAR-violet) <a href="https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java">Protocol Buffers Core</a>

  Esto indica que el proyecto depende de las siguientes librerias: **C3P0, Mchange Commons Java, JCalendar, MySQL Connector/J y Dotenv.** Necesitarás descargar e instalar estas librerias y agregarlas al proyecto en IntelliJ.

6. Crear el archivo **.env** y sobrescribe los valores de las variables de entorno en el archivo, el archivo .env debe estar en la raíz del proyecto, para sobrescribir los valores de las variables de entorno en el archivo .env, abra el archivo y edite los valores de las variables de entorno de acuerdo a su información:

```
    JDBC_URL=jdbc:mysql://localhost:3306/alura_hotel?createDatabaseIfNotExist=true&serverTimezone=UTC
    ROOT=xxx
    PASSWORD=xxx
```

Reemplace los valores de **localhost, 3306, alura_hotel, root y password** con la información de su servidor de base de datos.

7. Ejecutar el proyecto: en el menú **Run**, seleccione la opción Run.

### Requisitos 📄

1. Conocimientos básicos de programación: es probable que necesiten tener un conocimiento básico de programación, incluyendo conceptos como **variables, tipos de datos, condicionales y bucles**.

2. Conocimientos de Java: tenga conocimientos de **Java**, incluyendo la sintaxis básica, las estructuras de datos y el paradigma de **programación orientada a objetos**.

3. Conocimientos de Git: Si alguna persona quiere colaborar con el proyecto, necesitará tener conocimientos de **Git, GitLab ó GitHub** para poder clonar y contribuir al repositorio.

## Uso 💪

Esta aplicación es un sistema de reservas hoteleras básico que puede ser utilizado por una variedad de usuarios. La aplicación es fácil de usar y puede ayudar a los usuarios a reservar habitaciones de hotel de forma rápida y sencilla.

## Construido con 🛠️

<div style="text-align: center; padding: 10px;">
    <img src="/src/imagenes/java.png" width="100px">
    <img src="/src/imagenes/mysql.png" width="100px">
</div>

## Deployment 🚀

El proyecto se enfoca en el **desarrollo del backend** del sistema de reservas. El backend se encarga de las tareas de procesamiento de datos, como la validación de los datos de entrada, la consulta de la base de datos y la generación de la respuesta.

Esta estructura divide el proyecto en capas, cada una de las cuales tiene un propósito específico.

- La capa de controlador (controller) se encarga de procesar las solicitudes del usuario y llamar a las operaciones de la capa de modelo.
- La capa de DAO (data access object) se encarga de acceder a la base de datos y obtener o guardar datos.
- La capa (factory) se encarga de crear instancias de las clases de modelo y DAO.
- La capa de modelo (model) representa los datos del sistema.

## Autores ✒️

- **German Maldonado** - _Edición #3: Challenge ONE Back End Java Sprint 01_ - [germaldonado](https://github.com/germaldonado)

## Expresiones de Gratitud

- Me gustaría expresar mi sincero agradecimiento a **AluraLatam y Oracle** por la oportunidad de participar en el desafío de desarrollo de aplicaciones web. Este desafío me ha permitido aprender y crecer como desarrollador, y estoy muy agradecido por la oportunidad. 🤓.
- También quiero agradecer a los **instructores de AluraLatam** por su dedicación y compromiso. Sus enseñanzas han sido invaluables, y me han ayudado a comprender los conceptos y técnicas de desarrollo web. 📢.
- Por último, quiero agradecer a todos aquellos que me han apoyado en el desarrollo de este proyecto. Su apoyo ha sido fundamental para mi éxito, y estoy muy agradecido por su confianza.

Este proyecto es solo el comienzo de mi viaje como desarrollador. Estoy emocionado de seguir aprendiendo y creciendo, y espero poder continuar desarrollando aplicaciones que sean útiles e innovadoras. 🌟.

---

⌨️ con ❤️ por [German Maldonado](https://github.com/germaldonado) 😊

---
