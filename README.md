<h2>Documentación de la Aplicación de Agencia de Viajes</h2>

<h3>Descripción</h3>

La aplicación de Agencia de Viajes permite gestionar las reservas de hoteles y vuelos realizadas por los usuarios.

<h2>Tecnologías Utilizadas</h2>

<h3>Backend</h3>

<b>IntelliJ IDEA Community Edition:</b> IDE utilizado para desarrollar la aplicación.

<b>JDK 17:</b> Versión de Java utilizada.

Spring Boot 3.4.2: Framework utilizado para desarrollar la aplicación.

JPA (Java Persistence API): Para el mapeo objeto-relacional (ORM) y la interacción con la base de datos.

MySQL: Base de datos relacional utilizada para almacenar la información.

Spring Security: Implementación de seguridad básica con autenticación.

Swagger: Para la documentación de la API.

Frontend

Actualmente, la aplicación se centra en el backend desarrollado con Spring Boot.

Requisitos del Sistema

Java 17 o superior.

MySQL Server con una base de datos configurada.

Servidor de aplicaciones compatible con Java (por ejemplo, Apache Tomcat).

Arquitectura

La aplicación está desarrollada utilizando el modelo de capas, que se divide en:

Controladores (Controllers): Gestionan las solicitudes HTTP y responden con los datos procesados.

Servicios (Services): Contienen la lógica de negocio de la aplicación.

Repositorios (Repositories): Manejan la comunicación con la base de datos mediante JPA.

Modelos (Entities/DTOs): Representan las entidades de la base de datos y los objetos de transferencia de datos.

Configuración Inicial

La aplicación fue generada con Spring Initializr y configurada para trabajar con MySQL. Se recomienda verificar el archivo application.properties o application.yml para la configuración de la base de datos y seguridad.

Para más detalles sobre la API y los endpoints disponibles, consultar la documentación de Swagger generada automáticamente.

