## uDemo API Billing Service.

This document describes uDemo API Billing service, which demos how API services used by customers can be tracked and logged for the purpose of monitizing them. The service tracks API usage by generating usage events whenever API calls are made, this events are then propagated and handled by other components such as event listener to store the generated api call event. The service uses the captured information to prepare consumption history used to generate the monthly bill. The monthly bill is finally delivered via email to user. 

## How to set up and Run

To run locally, with maven command line
```
git clone https://github.com/bolarge/udemmo.git
cd udemo
./mvnw spring-boot:run

```
To run with Docker
```
docker run -p 9966:9966 bolarge/udemo

```
Database configuration (PostgreSQL)
```
To run on local, define settings in application.properties file.

spring.profiles.active=postgresql,spring-data-jpa defined in application.properties file.

Define db connection detail in application-postgresql.properties file.

spring.datasource.url=jdbc:postgresql://localhost:5432/udemodb
spring.datasource.username=udemo
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database=POSTGRESQL
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create-drop

```
If using container, you can start a postgres container as stated below:
```
docker run --name postgres-udemo -e POSTGRES_ROOT_PASSWORD=udemo -e POSTGRES_DATABASE=udemo -p 5432: postgress:9.5
```
RabbitMQ Configuration
```
To run on local, define settings in application.properties file.
mq.host=localhost
mq.username=guest
mq.password=guest
mq.port=5672
mq.vhost=/

```
If using container, you can start a rabbitmq container as follows:
```
$ docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3.8.7
```
### Installation
### Prerequisites
The following items should be installed in your system:
* JDK >= 8
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git command line tool (https://help.github.com/articles/set-up-git)

### Tech Stack: 
* Java 11 
* SpringBoot 2.3 
* Spring Data 
* Spring MVC 
* Swagger 2 
* RabbitMQ 
* JavaMail
* PostgresSQL 
* Tomcat 9
* Promethius
* Grafana
* Docker
* Stackify

## Swagger Documentation
You can run through the deployed application by using the swagger documentation at the link below
* http://{domain-name:port}/swagger-ui.html#/

