# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

COPY ../../../target/logistics-0.0.1-SNAPSHOT.jar /tpm

ENTRYPOINT ["java","-jar","/tpm/logistics-0.0.1-SNAPSHOT.jar"]