FROM 233410627014.dkr.ecr.us-east-1.amazonaws.com/zulu-openjdk-alpine-11-jre:latest

RUN mkdir -p /app && mkdir -p /app/config

RUN adduser -h /app -D -s /sbin/nologin app app

ENV HOME=/app
ENV APP_HOME=/app

WORKDIR $APP_HOME

COPY target/*.jar microservice.jar

RUN chown -R app:app $APP_HOME

CMD ["java", "-jar", "/app/microservice.jar"]

USER app