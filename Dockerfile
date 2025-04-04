# Build WAR
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /build
COPY . .

RUN mvn clean package -pl web -am -DskipTests

# Executa WAR no Tomcat
FROM tomcat:10.1-jdk21

# Limpa o ROOT default
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copia WAR como ROOT
COPY --from=builder /build/web/target/web-*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080