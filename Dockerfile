# Build a JAR File
FROM maven:3.8.1-jdk-11-slim AS stage1

WORKDIR lennoxpros

# Copying src code
COPY src src

# Copying pom file
COPY pom.xml pom.xml

RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests

# Copying reources
COPY lennoxpros.xml lennoxpros.xml
COPY resources resources
COPY results results
COPY checkHub.sh checkHub.sh

# Create an Image
FROM openjdk:13-alpine3.9

RUN apk add curl jq

WORKDIR lennoxpros

# Adding JARS files from target folder of host
COPY --from=stage1 lennoxpros/target/lennoxpros-0.0.1-SNAPSHOT.jar lennoxpros-0.0.1-SNAPSHOT.jar
COPY --from=stage1 lennoxpros/target/lennoxpros-0.0.1-SNAPSHOT-tests.jar lennoxpros-0.0.1-SNAPSHOT-tests.jar
COPY --from=stage1 lennoxpros/target/lennoxpros-0.0.1-SNAPSHOT.jar.original lennoxpros-0.0.1-SNAPSHOT.jar.original
COPY --from=stage1 lennoxpros/target/libs libs

# Copying reources
COPY pom.xml pom.xml
COPY lennoxpros.xml lennoxpros.xml
COPY resources resources
COPY results results
COPY checkHub.sh checkHub.sh

ENTRYPOINT sh checkHub.sh