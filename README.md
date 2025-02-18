<h2>Documentación de la Aplicación de Agencia de Viajes</h2>

<h3>Descripción</h3>

La aplicación de Agencia de Viajes permite gestionar las reservas de hoteles y vuelos realizadas por los usuarios.

<h2>Tecnologías Utilizadas</h2>

<h3>Backend</h3>

<b>IntelliJ IDEA Community Edition:</b> IDE utilizado para desarrollar la aplicación.

<b>JDK 17:</b> Versión de Java utilizada.

<b>Spring Boot 3.4.2:</b>Framework utilizado para desarrollar la aplicación.

<b>JPA (Java Persistence API):</b> Para el mapeo objeto-relacional (ORM) y la interacción con la base de datos.

<b>MySQL:</b> Base de datos relacional utilizada para almacenar la información.

<b>Spring Security:</b> Implementación de seguridad básica con autenticación.

<b>Swagger:</b> Para la documentación de la API.

<h3>Frontend</h3>

Actualmente, la aplicación se centra en el backend desarrollado con Spring Boot.

Requisitos del Sistema

Java 17 o superior.

MySQL Server con una base de datos configurada.

Servidor de aplicaciones compatible con Java (por ejemplo, Apache Tomcat).

<h3>Arquitectura</h3>h3>

La aplicación está desarrollada utilizando el modelo de capas, que se divide en:

<b>Controladores (Controllers):</b> Gestionan las solicitudes HTTP y responden con los datos procesados.

<b>Servicios (Services): Contienen la lógica de negocio de la aplicación.

<b>Repositorios (Repositories):</b> Manejan la comunicación con la base de datos mediante JPA.

<b>Modelos (Entities/DTOs):</b> Representan las entidades de la base de datos y los objetos de transferencia de datos.

<h3>Configuración Inicial</h3>h3>

La aplicación fue generada con Spring Initializr y configurada para trabajar con MySQL. Se recomienda verificar el archivo application.properties o application.yml para la configuración de la base de datos y seguridad.

Para más detalles sobre la API y los endpoints disponibles, consultar la documentación de Swagger generada automáticamente.

