FROM openjdk:11.0.4-jre-slim

EXPOSE 8080

ADD target/forum-demo.jar forum-demo.jar

ENTRYPOINT ["java", "-jar", "forum-demo.jar"]