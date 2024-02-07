FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app/hotelmanagerBE

COPY target/hotelmanager-0.0.1-SNAPSHOT.jar /app/hotelmanagerBE/app.jar

COPY db/generation.sql /app/hotelmanagerBE/

RUN apk add --no-cache postgresql-client 

# RUN psql -h localhost -U postgres -f /app/hotelmanagerBE/generation.sql

# Add the following line to enable debug logging
ENV JAVA_OPTS="-Xmx256m -Xms128m -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -Dlogging.level.root=DEBUG"

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]