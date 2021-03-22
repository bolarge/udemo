## uDemo is a user management demo app.

### This document describes uDemo REST API service by tech stack stack and some features

## Tech Stack: Java 11, SpringBoot 2.2, Spring Data, Spring REST, Swagger 2, RabbitMQ, JavaMail, MySQL8, Tomcat 9, Promethius, Docker, Stackify

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

uDemo run on MySQL
To run locally using persistent database, it is needed to set profile defined in application.properties file.

```
spring.profiles.active=mysql,spring-data-jpa
```
 defined in application.properties file.

Before doing this, would be good to check properties defined in application-mysql.properties file.

```
spring.datasource.url = jdbc:mysql://localhost:3306/udemo?useUnicode=true
spring.datasource.username=udemo
spring.datasource.password=udemo 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database=MYSQL
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=none
```      

### prerequisites
The following items should be installed in your system:
* JDK 11
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git command line tool (https://help.github.com/articles/set-up-git)
