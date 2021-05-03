## uDemo API Billing Service.

#### This document describes uDemo API Billing service, which demos how API services used by customers can be tracked and logged for the purpose of monitizing them. The service tracks API usage by generating usage events whenever API calls are made, this events are then propagated and handled by other components such as event listener to store the generated api call event. The service uses the captured information to prepare consumption history used to generate the monthly bill. The monthly bill is finally delivered via email to user. 

### Installation

To run locally, With maven command line
```
git clone https://github.com/bolarge/udemmo.git
cd udemo
./mvnw spring-boot:run

### With Docker
```
docker run -p 9966:9966 bolarge/udemo
```

## Database configuration

uDemo runs on PostgreSQL 
To run locally using persistent database, it is needed to set profile defined in application.properties file.

```
spring.profiles.active=postgresql,spring-data-jpa
```
 defined in application.properties file.

Before doing this, would be good to check properties defined in application-postgresql.properties file.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/udemodb
spring.datasource.username=udemo
spring.datasource.password=udemodb2$
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database=POSTGRESQL
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create-drop
```      
If using container to run udemo, you can start a postgres container as stated below:

```
docker run --name postgres-udemo -e POSTGRES_ROOT_PASSWORD=udemo -e POSTGRES_DATABASE=udemo -p 5432: postgress:9.5
```
## RabbitMQ

Ensure to have a running instance of rabbitmq and configured. rabbitmq configuration can be located in application.properties 

```
mq.host=localhost
mq.username=guest
mq.password=guest
mq.port=5672
mq.vhost=/
```

RabbitMQ, you can start a container as follows:

```
$ docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3.8.7
```

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
