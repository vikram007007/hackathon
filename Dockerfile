FROM openjdk:11.0
COPY --from=build /usr/src/app/target/SpringBootApplication-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]