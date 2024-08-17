FROM openjdk:21-jdk-slim

WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle.kts gradle.properties settings.gradle.kts ./

RUN chmod a+x gradlew
RUN ./gradlew build || return 0

COPY src src

CMD ["./gradlew", "build"]