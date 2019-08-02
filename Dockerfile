FROM openjdk:11.0.3
ADD target/muzix-app-hibernate-1.0-SNAPSHOT.jar muzix-app-hibernate-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","muzix-app-hibernate-1.0-SNAPSHOT.jar"]
