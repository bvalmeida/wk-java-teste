FROM openjdk:17-alpine

RUN apk add --no-cache bash
RUN apk update && apk add maven

WORKDIR /app

COPY . /app

RUN mvn clean install

ENTRYPOINT ["java", "-jar", "/app/target/wkbloodbank-0.0.1-SNAPSHOT.jar"]