FROM openjdk:11.0.5-jdk
LABEL maintainer="bolaji.salau@gmail.com"
VOLUME /tmp
EXPOSE 8085
ARG JAR_FILE=target/udemo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} udemo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/udemo.jar"]
